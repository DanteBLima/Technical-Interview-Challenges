package com.challenge.service;


import com.challenge.domain.transaction.Transaction;
import com.challenge.domain.user.User;
import com.challenge.dtos.TransactionDto;
import com.challenge.repositories.TransactionRepository;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotifyService notifyService;

    public Transaction createTransaction(TransactionDto transactionDto) throws Exception {
        User sender = this.userService.findUserById(transactionDto.senderId());

        User receiver = this.userService.findUserById(transactionDto.receiverId());

        userService.validateTransaction(sender, transactionDto.value());


        boolean isAuthorized = this.authorizeTransaction(sender, transactionDto.value());
        if (!isAuthorized){
            throw new Exception("Transacao nao autorizada");
        }

        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDto.value());
        transaction.setReceiver(receiver);
        transaction.setSender(sender);
        transaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transactionDto.value()));
        receiver.setBalance(receiver.getBalance().add(transactionDto.value()));

        this.transactionRepository.save(transaction);


        notifyService.sendNotification(sender, "Transaction sucessfully completed");

        notifyService.sendNotification(receiver, "Transaction received");

        return transaction;

    }

    public boolean authorizeTransaction(User sender, BigDecimal value) throws URISyntaxException {

        String url = "https://util.devi.tools/api/v2/authorize";


       URI uri = new URI(url);

        ResponseEntity<JsonNode> authorizationResponse = restTemplate.getForEntity( uri, JsonNode.class);

        if (authorizationResponse.getStatusCode() == HttpStatus.OK){
            JsonNode authorizationNode = authorizationResponse.getBody().path("data").path("authorization");
            return authorizationNode.asBoolean(false);
        }

        return false;

    }
}
