package com.example.semana7.services;


import com.example.semana7.DTOs.EnderecoRequestDTO;
import com.example.semana7.DTOs.EnderecoResponseDTO;
import com.example.semana7.entities.EnderecoEntity;
import com.example.semana7.repositories.EnderecoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnderecoService {

    private EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public EnderecoResponseDTO cadastrarEndereco(EnderecoRequestDTO enderecoRequestDTO) {
        EnderecoEntity enderecoEntity = new EnderecoEntity();

        enderecoEntity.setLogradouro(enderecoRequestDTO.getLogradouro());
        enderecoEntity.setEstado(enderecoRequestDTO.getEstado());
        enderecoEntity.setCidade(enderecoRequestDTO.getCidade());
        enderecoEntity.setNumero(enderecoRequestDTO.getNumero());
        enderecoEntity.setCep(enderecoRequestDTO.getCep());

        EnderecoEntity cadastrarEndereco = enderecoRepository.save(enderecoEntity);

        return new EnderecoResponseDTO(
                cadastrarEndereco.getEnderecoId(),
                cadastrarEndereco.getLogradouro(),
                cadastrarEndereco.getEstado(),
                cadastrarEndereco.getCidade(),
                cadastrarEndereco.getNumero(),
                cadastrarEndereco.getCep()
        );
    }

    public List<EnderecoResponseDTO> listarEndereco() {
        return enderecoRepository.findAll().stream().map(
                enderecoEntity -> new EnderecoResponseDTO(
                        enderecoEntity.getEnderecoId(),
                        enderecoEntity.getLogradouro(),
                        enderecoEntity.getEstado(),
                        enderecoEntity.getCidade(),
                        enderecoEntity.getNumero(),
                        enderecoEntity.getCep())
        ).collect(Collectors.toList());
    }

    public Optional<EnderecoResponseDTO> buscarEndereco(Long enderecoId) {
        return enderecoRepository.findById(enderecoId).stream().map(
                enderecoEntity -> new EnderecoResponseDTO(
                        enderecoEntity.getEnderecoId(),
                        enderecoEntity.getLogradouro(),
                        enderecoEntity.getEstado(),
                        enderecoEntity.getCidade(),
                        enderecoEntity.getNumero(),
                        enderecoEntity.getCep())
        ).findFirst();
    }

    public EnderecoResponseDTO atualizarEndereco(Long enderecoId, EnderecoRequestDTO enderecoRequestDTO) {
        Optional<EnderecoEntity> enderecoOptional = enderecoRepository.findById(enderecoId);

        EnderecoEntity enderecoEntity = enderecoOptional.get();

        enderecoEntity.setLogradouro(enderecoRequestDTO.getLogradouro());
        enderecoEntity.setEstado(enderecoRequestDTO.getEstado());
        enderecoEntity.setCidade(enderecoRequestDTO.getCidade());
        enderecoEntity.setNumero(enderecoRequestDTO.getNumero());
        enderecoEntity.setCep(enderecoRequestDTO.getCep());

        EnderecoEntity enderecoAtualizado = enderecoRepository.save(enderecoEntity);

        return new EnderecoResponseDTO(
                enderecoAtualizado.getEnderecoId(),
                enderecoAtualizado.getLogradouro(),
                enderecoAtualizado.getEstado(),
                enderecoAtualizado.getCidade(),
                enderecoAtualizado.getNumero(),
                enderecoAtualizado.getCep());
    }

    public boolean deletarEndereco(Long enderecoId) {
        var deletarEndereco = enderecoRepository.findById(enderecoId);

        if(deletarEndereco.isPresent()) {
            enderecoRepository.deleteById(enderecoId);
            return true;

        }else {
            return false;
        }
    }
}
