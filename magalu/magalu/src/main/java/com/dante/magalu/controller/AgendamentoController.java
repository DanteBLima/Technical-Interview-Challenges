package com.dante.magalu.controller;


import com.dante.magalu.dto.AgendamentoRequestDto;
import com.dante.magalu.dto.AgendamentoResponseDto;
import com.dante.magalu.model.Agendamento;
import com.dante.magalu.service.AgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agendamentos")
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @PostMapping
    public ResponseEntity<AgendamentoResponseDto> agendar(@RequestBody AgendamentoRequestDto agendamentoRequestDto){

        AgendamentoResponseDto responseDto = agendamentoService.agendar(agendamentoRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoResponseDto> getById(@PathVariable Long id){
        AgendamentoResponseDto responseDto = agendamentoService.buscarPorId(id);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteById(@PathVariable Long id){
        agendamentoService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
