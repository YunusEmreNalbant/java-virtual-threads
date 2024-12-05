package com.yunusemrenalbant.virtualthreads.example2.controller;

import com.yunusemrenalbant.virtualthreads.example2.service.ExternalApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExternalApiController {

    private final ExternalApiService externalApiService;

    public ExternalApiController(ExternalApiService externalApiService) {
        this.externalApiService = externalApiService;
    }

    @GetMapping("/api/external-data-platform")
    public List<String> getExternalDataWithPlatformThreads() {
        long startTime = System.currentTimeMillis();
        List<String> response = externalApiService.fetchExternalDataWithPlatformThreads();
        long endTime = System.currentTimeMillis();

        System.out.println("Platform Threads İşlem Süresi: " + (endTime - startTime) + " ms");

        return response;
    }

    @GetMapping("/api/external-data-virtual")
    public List<String> getExternalDataWithVirtualThreads() {
        long startTime = System.currentTimeMillis();
        List<String> response = externalApiService.fetchExternalDataWithVirtualThreads();
        long endTime = System.currentTimeMillis();

        System.out.println("Virtual Threads İşlem Süresi: " + (endTime - startTime) + " ms");

        return response;
    }
}
