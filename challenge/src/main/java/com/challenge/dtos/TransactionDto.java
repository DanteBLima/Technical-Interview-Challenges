package com.challenge.dtos;

import com.challenge.domain.user.User;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransactionDto(@NotNull BigDecimal value, @NotNull Long senderId,@NotNull  Long receiverId) {
}
