package com.yunusemrenalbant.virtualthreads.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.math.BigDecimal;

@Service
public class MoneyTransferService {

    public String processTransfer(String senderAccount, String receiverAccount, BigDecimal amount) {
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            executor.submit(() -> {
                try {
                    handleTransfer(senderAccount, receiverAccount, amount);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Transfer error!", e);
                }
            });
        }

        return "Transfer of ₺" + amount + " from " + senderAccount + " to " + receiverAccount + " is being processed.";
    }

    private void handleTransfer(String senderAccount, String receiverAccount, BigDecimal amount) throws InterruptedException {
        System.out.println("starting transfer from " + senderAccount + " to " + receiverAccount + " for ₺" + amount);
        Thread.sleep(2000);
        System.out.println("Transfer completed: ₺" + amount + " from " + senderAccount + " to " + receiverAccount);
    }
}
