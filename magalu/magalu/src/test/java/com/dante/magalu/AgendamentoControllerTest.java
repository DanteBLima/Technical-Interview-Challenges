package com.dante.magalu;


import com.dante.magalu.controller.AgendamentoController;
import com.dante.magalu.dto.AgendamentoRequestDto;
import com.dante.magalu.dto.AgendamentoResponseDto;
import com.dante.magalu.enums.StatusAgendamento;
import com.dante.magalu.enums.TipoComunicacao;
import com.dante.magalu.service.AgendamentoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.time.LocalDateTime;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AgendamentoController.class)
public class AgendamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private AgendamentoService agendamentoService;


    @Test
    void deveAgendarComunicação() throws Exception{

        AgendamentoRequestDto agendamentoRequestDto = new AgendamentoRequestDto(
                "cliente@gmail.com",
                "Mensagem de teste",
                TipoComunicacao.EMAIL,
                LocalDateTime.now()
        );

        AgendamentoResponseDto agendamentoResponseDto = new AgendamentoResponseDto(
                1L,
                agendamentoRequestDto.destinatario(),
                agendamentoRequestDto.mensagem(),
                agendamentoRequestDto.tipoComunicacao(),
                agendamentoRequestDto.dataHoraEnvio(),
                StatusAgendamento.AGENDADO,
                LocalDateTime.now()
        );

        when(agendamentoService.agendar(any())).thenReturn(agendamentoResponseDto);

        mockMvc.perform(post("/agendamentos").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(agendamentoRequestDto))).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.destinatario").value("cliente@gmail.com"));
    }


    @Test
    void deveBuscarPorId() throws Exception{

        Long id = 1L;

        AgendamentoResponseDto agendamentoResponseDto = new AgendamentoResponseDto(
                1L,
                "cliente@gmail.com",
                "Mensagem de teste",
                TipoComunicacao.SMS,
                LocalDateTime.now().plusMinutes(2),
                StatusAgendamento.AGENDADO,
                LocalDateTime.now()
        );

        when(agendamentoService.buscarPorId(id)).thenReturn(agendamentoResponseDto);

        mockMvc.perform(get("/agendamentos/{id}", id)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.tipoComunicacao").value("SMS"));
    }

    @Test
    void remover() throws Exception{

        Long id = 1L;

        doNothing().when(agendamentoService).remover(id);

        mockMvc.perform(delete("/agendamentos/{id}", id)).andExpect(status().isNoContent());
    }
}


