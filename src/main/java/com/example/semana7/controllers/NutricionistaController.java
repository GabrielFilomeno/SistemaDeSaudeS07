package com.example.semana7.controllers;

import com.example.semana7.DTOs.NutricionistaRequestDTO;
import com.example.semana7.DTOs.NutricionistaResponseDTO;
import com.example.semana7.services.NutricionistaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/nutricionista")
public class NutricionistaController {

    private final NutricionistaService nutricionistaService;

    public NutricionistaController(NutricionistaService nutricionistaService) {
        this.nutricionistaService = nutricionistaService;
    }

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarNutricionista(@RequestBody NutricionistaRequestDTO nutricionistaRequestDTO) {

        nutricionistaService.cadastrarNutricionista(nutricionistaRequestDTO);
    }

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public List<NutricionistaResponseDTO> listarNutricionistas() {
        return nutricionistaService.listarNutricionistas();
    }

    @GetMapping("/buscar/{nutricionistaId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<NutricionistaResponseDTO> buscarNutricionista(@PathVariable Long nutricionistaId) {
        return nutricionistaService.buscarNutricionistas(nutricionistaId);
    }

    @PutMapping("/atualizar/{nutricionistaId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public NutricionistaResponseDTO atualizarNutricionista(@PathVariable Long nutricionistaId, @RequestBody NutricionistaRequestDTO nutricionistaRequestDTO) {
        return nutricionistaService.atualizarNutricionista(nutricionistaId, nutricionistaRequestDTO);
    }

    @DeleteMapping("/deletar/{nutricionistaId}")
    @ResponseStatus(HttpStatus.OK)
    public String deletarNutricionista(@PathVariable Long nutricionistaId) {
        if (nutricionistaService.deletarNutricionista(nutricionistaId)) {
            return "Nutricionista deletado";
        } else {
            return "Nutricionista não encontrado";
        }
    }

    @PutMapping("/atualizarTempoExperiencia/{nutricionistaId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String atualizarTempoExperiencia(@PathVariable Long nutricionistaId) {
        if (nutricionistaService.adicionarTempoExperiencia(nutricionistaId)) {
            return "Tempo de experiência atualizado";
        } else {
            return "Nutricionista não encontrado";
        }
    }

    @PutMapping("/adicionarCertificacao/{nutricionistaId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String adicionarCertificacao(@PathVariable Long nutricionistaId, @RequestParam String certificacao) {
        if (nutricionistaService.adicionarCertificacao(nutricionistaId, certificacao)) {
            return "Certificação adicionada";
        } else {
            return "Nutricionista não encontrado";
        }
    }
}
