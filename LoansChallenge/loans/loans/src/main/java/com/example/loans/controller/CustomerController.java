package com.example.loans.controller;


import com.example.loans.dtos.CustomerDto;

import com.example.loans.dtos.LoanResponseDto;
import com.example.loans.services.CustomerService;
import com.example.loans.users.Customer;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @PostMapping("/customer-loans")
    public ResponseEntity<LoanResponseDto> createCustomer (@RequestBody @Valid CustomerDto data) throws Exception {

        LoanResponseDto response = customerService.createCostumer(data);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers(){
        List<Customer> existingCustomers = customerService.getAllCustomers();
        return ResponseEntity.status(HttpStatus.OK).body(existingCustomers);
    }

}
