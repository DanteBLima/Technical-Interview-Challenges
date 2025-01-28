package com.example.loans.dtos;

import com.example.loans.users.LoanType;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record LoanDto(@NotNull String type, @NotNull BigDecimal interestRate) {
}
