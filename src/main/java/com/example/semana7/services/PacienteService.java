package com.example.semana7.services;

import com.example.semana7.DTOs.PacienteRequestDTO;
import com.example.semana7.DTOs.PacienteResponseDTO;
import com.example.semana7.entities.EnderecoEntity;
import com.example.semana7.entities.PacienteEntity;
import com.example.semana7.repositories.EnderecoRepository;
import com.example.semana7.repositories.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

        var endereco = new EnderecoEntity();
        endereco.setLogradouro(pacienteRequestDTO.getEnderecoEntity().getLogradouro());
        endereco.setEstado(pacienteRequestDTO.getEnderecoEntity().getEstado());
        endereco.setCidade(pacienteRequestDTO.getEnderecoEntity().getCidade());
        endereco.setNumero(pacienteRequestDTO.getEnderecoEntity().getNumero());
        endereco.setCep(pacienteRequestDTO.getEnderecoEntity().getCep());
        enderecoRepository.save(endereco);

        pacienteEntity.setEndereco(endereco);

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
        return pacienteRepository.findAll().stream().map(
                pacienteEntity -> new PacienteResponseDTO(
                        pacienteEntity.getPacienteId(),
                        pacienteEntity.getNome(),
                        pacienteEntity.getDataNascimento(),
                        pacienteEntity.getCpf(),
                        pacienteEntity.getTelefone(),
                        pacienteEntity.getEmail(),
                        pacienteEntity.getEndereco())
        ).collect(Collectors.toList());
    }

    public Optional<PacienteResponseDTO> buscarPacientes(Long pacienteId) {
        return pacienteRepository.findById(pacienteId).stream().map(
                pacienteEntity -> new PacienteResponseDTO(
                        pacienteEntity.getPacienteId(),
                        pacienteEntity.getNome(),
                        pacienteEntity.getDataNascimento(),
                        pacienteEntity.getCpf(),
                        pacienteEntity.getTelefone(),
                        pacienteEntity.getEmail(),
                        pacienteEntity.getEndereco())
        ).findFirst();
    }

    public PacienteResponseDTO atualizarPaciente(Long pacienteId, PacienteRequestDTO pacienteRequestDTO) {
        Optional<PacienteEntity> pacienteOptional = pacienteRepository.findById(pacienteId);

        PacienteEntity pacienteEntity = pacienteOptional.get();
        pacienteEntity.setNome(pacienteRequestDTO.getNome());
        pacienteEntity.setDataNascimento(pacienteRequestDTO.getDataNascimento());
        pacienteEntity.setCpf(pacienteRequestDTO.getCpf());
        pacienteEntity.setTelefone(pacienteRequestDTO.getTelefone());
        pacienteEntity.setEmail(pacienteRequestDTO.getEmail());

        var enderecoOptional = enderecoRepository.findById(pacienteRequestDTO.getEnderecoEntity().getEnderecoId()).get();

        enderecoOptional.setLogradouro(pacienteRequestDTO.getEnderecoEntity().getLogradouro());
        enderecoOptional.setEstado(pacienteRequestDTO.getEnderecoEntity().getEstado());
        enderecoOptional.setCidade(pacienteRequestDTO.getEnderecoEntity().getCidade());
        enderecoOptional.setNumero(pacienteRequestDTO.getEnderecoEntity().getNumero());
        enderecoOptional.setCep(pacienteRequestDTO.getEnderecoEntity().getCep());
        enderecoRepository.save(enderecoOptional);

        pacienteEntity.setEndereco(enderecoOptional);

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
