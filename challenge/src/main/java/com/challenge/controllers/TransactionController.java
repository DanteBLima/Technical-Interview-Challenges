package com.challenge.controllers;


import com.challenge.domain.transaction.Transaction;
import com.challenge.dtos.TransactionDto;
import com.challenge.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;


    @PostMapping
    public ResponseEntity<Transaction> createTransaction (@Valid @RequestBody TransactionDto transactionDto) throws Exception {

        Transaction newTransaction = transactionService.createTransaction(transactionDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(newTransaction);

    }
}
