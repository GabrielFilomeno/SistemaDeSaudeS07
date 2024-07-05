package com.example.semana7.services;

import com.example.semana7.DTOs.NutricionistaRequestDTO;
import com.example.semana7.DTOs.NutricionistaResponseDTO;
import com.example.semana7.entities.NutricionistaEntity;
import com.example.semana7.repositories.NutricionistaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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

        var validaNome = nutricionistaRepository.findByNome(nutricionistaRequestDTO.getNome());
        var nome = nutricionistaRequestDTO.getNome();
        if (Objects.equals(validaNome.getNome(), nome)) {
            return null;
        } else {
            nutricionistaEntity.setNome(nutricionistaRequestDTO.getNome());
            nutricionistaEntity.setCrn(nutricionistaRequestDTO.getCrn());
            nutricionistaEntity.setTempoExperiencia(nutricionistaRequestDTO.getTempoExperiencia());
            nutricionistaEntity.setEspecialidade(nutricionistaRequestDTO.getEspecialidade());
            nutricionistaEntity.setCertificacoes(nutricionistaRequestDTO.getCertificacoes());

            NutricionistaEntity cadastrarNutricionista = nutricionistaRepository.save(nutricionistaEntity);

            return new NutricionistaResponseDTO(
                    cadastrarNutricionista.getNutricionistaId(),
                    cadastrarNutricionista.getNome(),
                    cadastrarNutricionista.getCrn(),
                    cadastrarNutricionista.getTempoExperiencia(),
                    cadastrarNutricionista.getEspecialidade(),
                    cadastrarNutricionista.getCertificacoes());
        }
    };

    public List<NutricionistaResponseDTO> listarNutricionistas() {
        return  nutricionistaRepository.findAll().stream().map(
                nutricionistaEntity -> new NutricionistaResponseDTO(
                        nutricionistaEntity.getNutricionistaId(),
                        nutricionistaEntity.getNome(),
                        nutricionistaEntity.getCrn(),
                        nutricionistaEntity.getTempoExperiencia(),
                        nutricionistaEntity.getEspecialidade(),
                        nutricionistaEntity.getCertificacoes())
        ).collect(Collectors.toList());
    };

    public Optional<NutricionistaResponseDTO> buscarNutricionistas(Long nutricionistaId) {
        return  nutricionistaRepository.findById(nutricionistaId).stream().map(
                nutricionistaEntity -> new NutricionistaResponseDTO(
                        nutricionistaEntity.getNutricionistaId(),
                        nutricionistaEntity.getNome(),
                        nutricionistaEntity.getCrn(),
                        nutricionistaEntity.getTempoExperiencia(),
                        nutricionistaEntity.getEspecialidade(),
                        nutricionistaEntity.getCertificacoes())
        ).findAny();
    };

    public NutricionistaResponseDTO atualizarNutricionista(Long nutricionistaId, NutricionistaRequestDTO nutricionistaRequestDTO) {
        Optional<NutricionistaEntity> nutricionistaOptional = nutricionistaRepository.findById(nutricionistaId);

            NutricionistaEntity nutricionistaEntity = nutricionistaOptional.get();

            var validaNome = nutricionistaRepository.findByNome(nutricionistaRequestDTO.getNome());
            var nome = nutricionistaRequestDTO.getNome();
            if (Objects.equals(validaNome.getNome(), nome)) {
                return null;
            } else {
            nutricionistaEntity.setNome(nutricionistaRequestDTO.getNome());
            nutricionistaEntity.setCrn(nutricionistaRequestDTO.getCrn());
            nutricionistaEntity.setTempoExperiencia(nutricionistaRequestDTO.getTempoExperiencia());
            nutricionistaEntity.setEspecialidade(nutricionistaRequestDTO.getEspecialidade());
            nutricionistaEntity.setCertificacoes(nutricionistaRequestDTO.getCertificacoes());

            NutricionistaEntity nutricionistaAtualizado = nutricionistaRepository.save(nutricionistaEntity);

            return new NutricionistaResponseDTO(
                    nutricionistaAtualizado.getNutricionistaId(),
                    nutricionistaAtualizado.getNome(),
                    nutricionistaAtualizado.getCrn(),
                    nutricionistaAtualizado.getTempoExperiencia(),
                    nutricionistaAtualizado.getEspecialidade(),
                    nutricionistaAtualizado.getCertificacoes());
            }
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

    public boolean adicionarTempoExperiencia (Long nutricionistaId) {
        var nutricionista = nutricionistaRepository.findById(nutricionistaId);

        if (nutricionista.isPresent()) {
            var adicionarTempoExperiencia = nutricionista.get().getTempoExperiencia() + 1;
            nutricionista.get().setTempoExperiencia(adicionarTempoExperiencia);
            nutricionistaRepository.save(nutricionista.get());

            return true;
        } else {
            return false;
        }
    }

    public boolean adicionarCertificacao(Long nutricionistaId, String certificacao) {
        var nutricionista = nutricionistaRepository.findById(nutricionistaId);

        if (nutricionista.isPresent()) {
            nutricionista.get().getCertificacoes().add(certificacao);
            nutricionistaRepository.save(nutricionista.get());

            return true;
        } else {
            return false;
        }
    }
}
