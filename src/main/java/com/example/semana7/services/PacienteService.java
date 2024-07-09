package com.example.semana7.services;

import com.example.semana7.DTOs.PacienteRequestDTO;
import com.example.semana7.DTOs.PacienteResponseDTO;
import com.example.semana7.entities.PacienteEntity;
import com.example.semana7.repositories.EnderecoRepository;
import com.example.semana7.repositories.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final EnderecoRepository enderecoRepository;

    public PacienteService(PacienteRepository pacienteRepository, EnderecoRepository enderecoRepository) {
        this.pacienteRepository = pacienteRepository;
        this.enderecoRepository = enderecoRepository;
    }

    public PacienteResponseDTO cadastrarPaciente(PacienteRequestDTO pacienteRequestDTO) {
        PacienteEntity pacienteEntity = new PacienteEntity();

        pacienteEntity.setNome(pacienteRequestDTO.getNome());
        pacienteEntity.setDataNascimento(pacienteRequestDTO.getDataNascimento());
        pacienteEntity.setCpf(pacienteRequestDTO.getCpf());
        pacienteEntity.setTelefone(pacienteRequestDTO.getTelefone());
        pacienteEntity.setEmail(pacienteRequestDTO.getEmail());
        var endereco = enderecoRepository.findById(pacienteRequestDTO.getEnderecoId());
        endereco.ifPresent(pacienteEntity::setEndereco);

        PacienteEntity cadastrarPaciente = pacienteRepository.save(pacienteEntity);

        return new PacienteResponseDTO(
                cadastrarPaciente.getPacienteId(),
                cadastrarPaciente.getNome(),
                cadastrarPaciente.getDataNascimento(),
                cadastrarPaciente.getCpf(),
                cadastrarPaciente.getTelefone(),
                cadastrarPaciente.getEmail(),
                cadastrarPaciente.getEndereco());
    }

    public List<PacienteResponseDTO> listarPacientes() {
        List<PacienteEntity> pacientes = pacienteRepository.findAll();

        List<PacienteResponseDTO> pacienteResponseDTO = new ArrayList<>();

        for (PacienteEntity entity : pacientes) {
            PacienteResponseDTO response = new PacienteResponseDTO();

            response.setPacienteId(entity.getPacienteId());
            response.setNome(entity.getNome());
            response.setDataNascimento(entity.getDataNascimento());
            response.setCpf(entity.getCpf());
            response.setTelefone(entity.getTelefone());
            response.setEmail(entity.getEmail());
            response.setEnderecoEntity(entity.getEndereco());
            pacienteResponseDTO.add(response);
        }

        return pacienteResponseDTO;
    }

    public Optional<PacienteResponseDTO> buscarPacientes(Long pacienteId) {
        Optional<PacienteEntity> entity = pacienteRepository.findById(pacienteId);

        PacienteResponseDTO response = new PacienteResponseDTO();

        if (entity.isPresent()) {
            response.setPacienteId(entity.get().getPacienteId());
            response.setNome(entity.get().getNome());
            response.setDataNascimento(entity.get().getDataNascimento());
            response.setCpf(entity.get().getCpf());
            response.setTelefone(entity.get().getTelefone());
            response.setEmail(entity.get().getEmail());
            response.setEnderecoEntity(entity.get().getEndereco());
        }
        return Optional.of(response);
    }

    public PacienteResponseDTO atualizarPaciente(Long pacienteId, PacienteRequestDTO pacienteRequestDTO) {
        Optional<PacienteEntity> pacienteOptional = pacienteRepository.findById(pacienteId);

        PacienteEntity pacienteEntity = pacienteOptional.get();
        pacienteEntity.setNome(pacienteRequestDTO.getNome());
        pacienteEntity.setDataNascimento(pacienteRequestDTO.getDataNascimento());
        pacienteEntity.setCpf(pacienteRequestDTO.getCpf());
        pacienteEntity.setTelefone(pacienteRequestDTO.getTelefone());
        pacienteEntity.setEmail(pacienteRequestDTO.getEmail());
        var endereco = enderecoRepository.findById(pacienteRequestDTO.getEnderecoId());
        endereco.ifPresent(pacienteEntity::setEndereco);

        PacienteEntity pacienteAtualizado = pacienteRepository.save(pacienteEntity);

        return new PacienteResponseDTO(
                pacienteAtualizado.getPacienteId(),
                pacienteAtualizado.getNome(),
                pacienteAtualizado.getDataNascimento(),
                pacienteAtualizado.getCpf(),
                pacienteAtualizado.getTelefone(),
                pacienteAtualizado.getEmail(),
                pacienteAtualizado.getEndereco());
    }

    public boolean deletarPaciente(Long pacienteId) {
        var deletarPaciente = pacienteRepository.findById(pacienteId);

        if (deletarPaciente.isPresent()) {
            pacienteRepository.deleteById(pacienteId);
            return true;

        } else {
            return false;
        }
    }
}
