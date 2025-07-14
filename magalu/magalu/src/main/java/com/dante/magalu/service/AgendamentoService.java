package com.dante.magalu.service;

import com.dante.magalu.dto.AgendamentoRequestDto;
import com.dante.magalu.dto.AgendamentoResponseDto;
import com.dante.magalu.enums.StatusAgendamento;
import com.dante.magalu.model.Agendamento;
import com.dante.magalu.repository.AgendamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    @Autowired
    private final AgendamentoRepository agendamentoRepository;

    public AgendamentoResponseDto agendar(AgendamentoRequestDto agendamentoRequestDto){

        Agendamento agendamento = Agendamento.builder().
                destinatario(agendamentoRequestDto.destinatario()).
                mensagem(agendamentoRequestDto.mensagem()).
                tipoComunicacao(agendamentoRequestDto.tipoComunicacao()).
                dataHoraEnvio(agendamentoRequestDto.dataHoraEnvio()).
                status(StatusAgendamento.AGENDADO).
                createdAt(LocalDateTime.now()).build();

        return toResponse(agendamentoRepository.save(agendamento));

    }

    public AgendamentoResponseDto toResponse(Agendamento agendamento){

        return new AgendamentoResponseDto(
                agendamento.getId(),
                agendamento.getDestinatario(),
                agendamento.getMensagem(),
                agendamento.getTipoComunicacao(),
                agendamento.getDataHoraEnvio(),
                agendamento.getStatus(),
                agendamento.getCreatedAt()
        );
    }

    public AgendamentoResponseDto buscarPorId(Long id){
        Agendamento agendamento = agendamentoRepository.findById(id).orElseThrow();

        return toResponse(agendamento);
    }

    public void remover(Long id){
        if(!agendamentoRepository.existsById(id)){
            throw new EntityNotFoundException("Agendamento não encontrado");
        }
        agendamentoRepository.deleteById(id);
    }
}
