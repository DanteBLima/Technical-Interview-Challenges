package com.challenge.dtos;

import jakarta.validation.constraints.NotBlank;

public record NotiDto(@NotBlank String email, @NotBlank String message) {
}
