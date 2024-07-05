package com.example.semana7.services;

import com.example.semana7.DTOs.NutricionistaRequestDTO;
import com.example.semana7.DTOs.NutricionistaResponseDTO;
import com.example.semana7.entities.NutricionistaEntity;
import com.example.semana7.repositories.NutricionistaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class NutricionistaService {

    private final NutricionistaRepository nutricionistaRepository;

    public NutricionistaService(NutricionistaRepository nutricionistaRepository) {
        this.nutricionistaRepository = nutricionistaRepository;
    }

    public NutricionistaResponseDTO cadastrarNutricionista(NutricionistaRequestDTO nutricionistaRequestDTO) {

        NutricionistaEntity nutricionistaEntity = new NutricionistaEntity();
        nutricionistaEntity.setNome(nutricionistaRequestDTO.getNome());
        nutricionistaEntity.setCrn(nutricionistaRequestDTO.getCrn());
        nutricionistaEntity.setTempoExperiencia(nutricionistaRequestDTO.getTempoExperiencia());
        nutricionistaEntity.setEspecialidade(nutricionistaRequestDTO.getEspecialidade());

        NutricionistaEntity cadastrarNutricionista = nutricionistaRepository.save(nutricionistaEntity);

        return new NutricionistaResponseDTO(
                cadastrarNutricionista.getNutricionistaId(),
                cadastrarNutricionista.getNome(),
                cadastrarNutricionista.getCrn(),
                cadastrarNutricionista.getTempoExperiencia(),
                cadastrarNutricionista.getEspecialidade());
    };

    public List<NutricionistaResponseDTO> listarNutricionistas() {
        return  nutricionistaRepository.findAll().stream().map(
                nutricionistaEntity -> new NutricionistaResponseDTO(
                        nutricionistaEntity.getNutricionistaId(),
                        nutricionistaEntity.getNome(),
                        nutricionistaEntity.getCrn(),
                        nutricionistaEntity.getTempoExperiencia(),
                        nutricionistaEntity.getEspecialidade())
        ).collect(Collectors.toList());
    };

    public Optional<NutricionistaResponseDTO> buscarNutricionistas(Long nutricionistaId) {
        return  nutricionistaRepository.findById(nutricionistaId).stream().map(
                nutricionistaEntity -> new NutricionistaResponseDTO(
                        nutricionistaEntity.getNutricionistaId(),
                        nutricionistaEntity.getNome(),
                        nutricionistaEntity.getCrn(),
                        nutricionistaEntity.getTempoExperiencia(),
                        nutricionistaEntity.getEspecialidade())
        ).findAny();
    };

    public NutricionistaResponseDTO atualizarNutricionista(Long nutricionistaId, NutricionistaRequestDTO nutricionistaRequestDTO) {
        Optional<NutricionistaEntity> nutricionistaOptional = nutricionistaRepository.findById(nutricionistaId);

            NutricionistaEntity nutricionistaEntity = nutricionistaOptional.get();
            nutricionistaEntity.setNome(nutricionistaRequestDTO.getNome());
            nutricionistaEntity.setCrn(nutricionistaRequestDTO.getCrn());
            nutricionistaEntity.setTempoExperiencia(nutricionistaRequestDTO.getTempoExperiencia());
            nutricionistaEntity.setEspecialidade(nutricionistaRequestDTO.getEspecialidade());

            NutricionistaEntity nutricionistaAtualizado = nutricionistaRepository.save(nutricionistaEntity);

            return new NutricionistaResponseDTO(
                    nutricionistaAtualizado.getNutricionistaId(),
                    nutricionistaAtualizado.getNome(),
                    nutricionistaAtualizado.getCrn(),
                    nutricionistaAtualizado.getTempoExperiencia(),
                    nutricionistaAtualizado.getEspecialidade());
    }

    public boolean deletarNutricionista(Long nutricionistaId) {
        var deletarNutricionista = nutricionistaRepository.findById(nutricionistaId);

        if (deletarNutricionista.isPresent()) {

            nutricionistaRepository.deleteById(nutricionistaId);
            return true;

        } else {
            return false;
        }
    }
}
