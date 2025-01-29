package com.example.loans.infra;

import com.example.loans.dtos.ExceptionDto;
import jakarta.persistence.EntityExistsException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(DuplicateEntityException.class)
    private ResponseEntity<ExceptionDto> threatDuplicateEntry (DuplicateEntityException exception){
        ExceptionDto exceptionDto = new ExceptionDto(exception.getMessage(), "400");
        return ResponseEntity.badRequest().body(exceptionDto);
    }
}
