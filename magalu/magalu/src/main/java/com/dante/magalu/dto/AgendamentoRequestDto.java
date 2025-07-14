package com.dante.magalu.dto;

import com.dante.magalu.enums.TipoComunicacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AgendamentoRequestDto(
        @NotBlank(message = "O destinatário é obrigatório")
        String destinatario,
        @NotBlank(message = "A mensagem é obrigatória")
        String mensagem,
        @NotBlank(message = "O tipo de comunicação é obrigatório")
        TipoComunicacao tipoComunicacao,
        @NotNull
        LocalDateTime dataHoraEnvio
) {
}
