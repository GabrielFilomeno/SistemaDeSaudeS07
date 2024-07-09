package com.example.semana7.controllers;

import com.example.semana7.DTOs.PacienteRequestDTO;
import com.example.semana7.DTOs.PacienteResponseDTO;
import com.example.semana7.services.PacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService){
        this.pacienteService = pacienteService;
    }

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarPaciente(@RequestBody PacienteRequestDTO pacienteRequestDTO) {

        pacienteService.cadastrarPaciente(pacienteRequestDTO);
    }

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public List<PacienteResponseDTO> listarPacientes() {
        return pacienteService.listarPacientes();
    }

    @GetMapping("/buscar/{pacienteId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<PacienteResponseDTO> buscarNutricionista(@PathVariable Long pacienteId) {
        return pacienteService.buscarPacientes(pacienteId);
    }

    @PutMapping("/atualizar/{pacienteId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PacienteResponseDTO atualizarPaciente(@PathVariable Long pacienteId, @RequestBody PacienteRequestDTO pacienteRequestDTO) {
        return pacienteService.atualizarPaciente(pacienteId, pacienteRequestDTO);
    }

    @DeleteMapping("/deletar/{pacienteId}")
    @ResponseStatus(HttpStatus.OK)
    public String deletarPaciente(@PathVariable Long pacienteId) {
        if (pacienteService.deletarPaciente(pacienteId)) {
            return "Paciente deletado";
        } else {
            return "Paciente não encontrado";
        }
    }
}
