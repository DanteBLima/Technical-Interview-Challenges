package com.example.loans.dtos;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;


public record CustomerDto(@NotNull int age, @NotNull String cpf, @NotNull String name, @NotNull String location, @NotNull
                          BigDecimal income) {
}
