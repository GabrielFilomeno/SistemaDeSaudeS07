package com.example.semana7.controllers;

import com.example.semana7.DTOs.ConsultaRequestDTO;
import com.example.semana7.DTOs.ConsultaResponseDTO;
import com.example.semana7.DTOs.NutricionistaRequestDTO;
import com.example.semana7.DTOs.NutricionistaResponseDTO;
import com.example.semana7.services.ConsultaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService){
        this.consultaService = consultaService;
    }

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarConsulta(@RequestBody ConsultaRequestDTO consultaRequestDTO) {

        consultaService.cadastrarConsulta(consultaRequestDTO);
    }

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public List<ConsultaResponseDTO> listarConsultas() {
        return consultaService.listarConsultas();
    }

    @GetMapping("/buscar/{consultaId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ConsultaResponseDTO> buscarConsultas(@PathVariable Long consultaId) {
        return consultaService.buscarConsultas(consultaId);
    }

    @PutMapping("/atualizar/{consultaId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ConsultaResponseDTO atualizarConsulta(@PathVariable Long consultaId, @RequestBody ConsultaRequestDTO consultaRequestDTO) {
        return consultaService.atualizarConsulta(consultaId, consultaRequestDTO);
    }

    @DeleteMapping("/deletar/{consultaId}")
    @ResponseStatus(HttpStatus.OK)
    public String deletarConsulta(@PathVariable Long consultaId) {
        if (consultaService.deletarConsulta(consultaId)) {
            return "Consulta deletada";
        } else {
            return "Consulta não encontrada";
        }
    }
}
