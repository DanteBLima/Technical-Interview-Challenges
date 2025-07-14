package com.dante.magalu.model;


import com.dante.magalu.enums.StatusAgendamento;
import com.dante.magalu.enums.TipoComunicacao;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String destinatario;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String mensagem;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoComunicacao tipoComunicacao;

    @Column(nullable = false)
    private LocalDateTime dataHoraEnvio;

    @Enumerated(EnumType.STRING)
    private StatusAgendamento status;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
