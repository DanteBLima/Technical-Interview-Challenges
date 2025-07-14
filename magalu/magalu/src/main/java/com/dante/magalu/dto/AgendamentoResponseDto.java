package com.dante.magalu.dto;

import com.dante.magalu.enums.StatusAgendamento;
import com.dante.magalu.enums.TipoComunicacao;

import java.time.LocalDateTime;

public record AgendamentoResponseDto(
        Long id,
        String destinatario,
        String mensagem,
        TipoComunicacao tipoComunicacao,
        LocalDateTime dataHoraEnvio,
        StatusAgendamento status,
        LocalDateTime createdAt
) {
}
