package com.yunusemrenalbant.virtualthreads.example1.controller;

import com.yunusemrenalbant.virtualthreads.example1.service.MoneyTransferService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/money-transfers")
public class MoneyTransferController {

    private final MoneyTransferService moneyTransferService;

    public MoneyTransferController(MoneyTransferService moneyTransferService) {
        this.moneyTransferService = moneyTransferService;
    }

    @PostMapping
    public String transfer(
            @RequestParam String senderAccount,
            @RequestParam String receiverAccount,
            @RequestParam BigDecimal amount) {
        return moneyTransferService.processTransfer(senderAccount, receiverAccount, amount);
    }
}
