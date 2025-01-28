package com.example.loans.dtos;

import com.example.loans.users.LoanType;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record LoanResponseDto(@NotNull String customer, @NotNull List<LoanDto> loans) {
}
