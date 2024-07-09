package com.example.semana7.services;

import com.example.semana7.DTOs.ConsultaRequestDTO;
import com.example.semana7.DTOs.ConsultaResponseDTO;
import com.example.semana7.entities.ConsultaEntity;
import com.example.semana7.repositories.ConsultaRepository;
import com.example.semana7.repositories.NutricionistaRepository;
import com.example.semana7.repositories.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final NutricionistaRepository nutricionistaRepository;
    private final PacienteRepository pacienteRepository;

    public ConsultaService(ConsultaRepository consultaRepository, NutricionistaRepository nutricionistaRepository, PacienteRepository pacienteRepository) {
        this.consultaRepository = consultaRepository;
        this.nutricionistaRepository = nutricionistaRepository;
        this.pacienteRepository = pacienteRepository;
    }

    public ConsultaResponseDTO cadastrarConsulta(ConsultaRequestDTO consultaRequestDTO) {
        ConsultaEntity consultaEntity = new ConsultaEntity();
        consultaEntity.setDataConsulta(consultaRequestDTO.getDataConsulta());
        consultaEntity.setObservacoes(consultaRequestDTO.getObservacoes());

        var nutricionista = nutricionistaRepository.findById(consultaRequestDTO.getNutricionistaId());
        nutricionista.ifPresent(consultaEntity::setNutricionista);

        var paciente = pacienteRepository.findById(consultaRequestDTO.getPacienteId());
        paciente.ifPresent(consultaEntity::setPaciente);

        ConsultaEntity cadastrarConsulta = consultaRepository.save(consultaEntity);

        return new ConsultaResponseDTO(
                cadastrarConsulta.getConsultaId(),
                cadastrarConsulta.getDataConsulta(),
                cadastrarConsulta.getObservacoes(),
                cadastrarConsulta.getNutricionista(),
                cadastrarConsulta.getPaciente()
        );
    }

    public List<ConsultaResponseDTO> listarConsultas() {
        List<ConsultaEntity> consultas = consultaRepository.findAll();

        List<ConsultaResponseDTO> consultaResponseDTO = new ArrayList<>();

        for (ConsultaEntity entity : consultas) {
            ConsultaResponseDTO response = new ConsultaResponseDTO();

            response.setConsultaId(entity.getConsultaId());
            response.setDataConsulta(entity.getDataConsulta());
            response.setObservacoes(entity.getObservacoes());
            response.setNutricionistaEntity(entity.getNutricionista());
            response.setPacienteEntity(entity.getPaciente());
            consultaResponseDTO.add(response);
        }

        return consultaResponseDTO;
    }

    public Optional<ConsultaResponseDTO> buscarConsultas(Long consultaId) {
        Optional<ConsultaEntity> entity = consultaRepository.findById(consultaId);

        ConsultaResponseDTO response = new ConsultaResponseDTO();

        if (entity.isPresent()) {
            response.setDataConsulta(entity.get().getDataConsulta());
            response.setObservacoes(entity.get().getObservacoes());
            response.setNutricionistaEntity(entity.get().getNutricionista());
            response.setPacienteEntity(entity.get().getPaciente());
        }
        return Optional.of(response);
    }

    public ConsultaResponseDTO atualizarConsulta(Long consultaId, ConsultaRequestDTO consultaRequestDTO) {
        Optional<ConsultaEntity> consultaOptional = consultaRepository.findById(consultaId);

            ConsultaEntity consultaEntity = consultaOptional.get();
            consultaEntity.setDataConsulta(consultaRequestDTO.getDataConsulta());
            consultaEntity.setObservacoes(consultaRequestDTO.getObservacoes());

            var nutricionista = nutricionistaRepository.findById(consultaRequestDTO.getNutricionistaId());
            nutricionista.ifPresent(consultaEntity::setNutricionista);

            var paciente = pacienteRepository.findById(consultaRequestDTO.getPacienteId());
            paciente.ifPresent(consultaEntity::setPaciente);

            ConsultaEntity consultaAtualizada = consultaRepository.save(consultaEntity);

            return new ConsultaResponseDTO(
                    consultaAtualizada.getConsultaId(),
                    consultaAtualizada.getDataConsulta(),
                    consultaAtualizada.getObservacoes(),
                    consultaAtualizada.getNutricionista(),
                    consultaAtualizada.getPaciente());
    }

    public boolean deletarConsulta(Long consultaId) {
        var deletarConsulta = consultaRepository.findById(consultaId);

        if (deletarConsulta.isPresent()) {

            consultaRepository.deleteById(consultaId);
            return true;

        } else {
            return false;
        }
    }
}
