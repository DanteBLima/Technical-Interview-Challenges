package com.challenge.dtos;

import com.challenge.domain.user.UserType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;



public record UserDto(@NotBlank String firstName, @NotBlank String lastName, @NotBlank String document, BigDecimal balance, @NotBlank  String email, @NotBlank String password, @NotNull
                      UserType userType) {
}
