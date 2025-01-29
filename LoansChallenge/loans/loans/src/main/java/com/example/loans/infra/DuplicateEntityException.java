package com.example.loans.infra;



public class DuplicateEntityException extends RuntimeException{

    public DuplicateEntityException(String message){
        super(message);
    }
}
