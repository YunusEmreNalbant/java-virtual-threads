package com.yunusemrenalbant.virtualthreads.example2.service;


import com.yunusemrenalbant.virtualthreads.example2.util.HttpRequestUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

@Service
public class ExternalApiService {

    private final HttpRequestUtil httpRequestUtil;

    public ExternalApiService(HttpRequestUtil httpRequestUtil) {
        this.httpRequestUtil = httpRequestUtil;
    }

    public List<String> fetchExternalDataWithVirtualThreads() {
        List<String> apiEndpoints = IntStream.rangeClosed(1, 100)
                .mapToObj(id -> "https://jsonplaceholder.typicode.com/posts/" + id)
                .toList();

        try (ExecutorService virtualThreadExecutor = Executors.newVirtualThreadPerTaskExecutor()) {
            List<Future<String>> apiResponses = apiEndpoints.stream()
                    .map(endpoint -> virtualThreadExecutor.submit(() -> httpRequestUtil.sendGetRequest(endpoint)))
                    .toList();

            return apiResponses.stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (Exception ex) {
                            throw new RuntimeException("API çağrısı başarısız: " + ex.getMessage());
                        }
                    }).toList();
        }
    }

    public List<String> fetchExternalDataWithPlatformThreads() {
        List<String> apiEndpoints = IntStream.rangeClosed(1, 100)
                .mapToObj(id -> "https://jsonplaceholder.typicode.com/posts/" + id)
                .toList();

        ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(10);

        try {
            List<Future<String>> apiResponses = apiEndpoints.stream()
                    .map(endpoint -> threadPoolExecutor.submit(() -> httpRequestUtil.sendGetRequest(endpoint)))
                    .toList();

            return apiResponses.stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (Exception ex) {
                            throw new RuntimeException("API çağrısı başarısız: " + ex.getMessage());
                        }
                    }).toList();
        } finally {
            threadPoolExecutor.shutdown();
        }
    }
}
