package com.challenge.infra;


import com.challenge.dtos.ExceptionDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    ResponseEntity threatDuplicateEntry(DataIntegrityViolationException exception){

        ExceptionDto exceptionDto = new ExceptionDto("User already exists", "400");

        return ResponseEntity.badRequest().body(exceptionDto);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    ResponseEntity threat404(EntityNotFoundException exception){

        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity threatGeneralException(Exception exception){
        ExceptionDto exceptionDto = new ExceptionDto(exception.getMessage(), "500");

        return ResponseEntity.internalServerError().body(exceptionDto);

    }

    }

