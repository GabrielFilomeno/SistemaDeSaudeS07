package com.example.semana7.controllers;

import com.example.semana7.DTOs.EnderecoRequestDTO;
import com.example.semana7.DTOs.EnderecoResponseDTO;
import com.example.semana7.services.EnderecoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService){
        this.enderecoService = enderecoService;
    }

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarEndereco(@RequestBody EnderecoRequestDTO enderecoRequestDTO) {

        enderecoService.cadastrarEndereco(enderecoRequestDTO);
    }

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public List<EnderecoResponseDTO> listarEnderecos() {
        return enderecoService.listarEndereco();
    }

    @GetMapping("/buscar/{enderecoId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<EnderecoResponseDTO> buscarEnderecos(@PathVariable Long enderecoId) {
        return enderecoService.buscarEndereco(enderecoId);
    }

    @PutMapping("/atualizar/{enderecoId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public EnderecoResponseDTO atualizarEndereco(@PathVariable Long enderecoId, @RequestBody EnderecoRequestDTO enderecoRequestDTO) {
        return enderecoService.atualizarEndereco(enderecoId, enderecoRequestDTO);
    }

    @DeleteMapping("/deletar/{enderecoId}")
    @ResponseStatus(HttpStatus.OK)
    public String deletarEndereco(@PathVariable Long enderecoId) {
        if (enderecoService.deletarEndereco(enderecoId)) {
            return "Endereço deletado";
        } else {
            return "Endereço não encontrado";
        }
    }
}
