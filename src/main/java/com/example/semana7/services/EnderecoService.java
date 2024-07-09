package com.example.semana7.services;

import com.example.semana7.DTOs.EnderecoRequestDTO;
import com.example.semana7.DTOs.EnderecoResponseDTO;
import com.example.semana7.entities.EnderecoEntity;
import com.example.semana7.repositories.EnderecoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        List<EnderecoEntity> enderecos = enderecoRepository.findAll();

        List<EnderecoResponseDTO> enderecoResponseDTOS = new ArrayList<>();

        for (EnderecoEntity entity : enderecos) {
            EnderecoResponseDTO response = new EnderecoResponseDTO();

            response.setEnderecoId(entity.getEnderecoId());
            response.setLogradouro(entity.getLogradouro());
            response.setEstado(entity.getEstado());
            response.setCidade(entity.getCidade());
            response.setNumero(entity.getNumero());
            response.setCep(entity.getCep());
            enderecoResponseDTOS.add(response);
        }

        return enderecoResponseDTOS;
    }

    public Optional<EnderecoResponseDTO> buscarEndereco(Long enderecoId) {
        Optional<EnderecoEntity> entity = enderecoRepository.findById(enderecoId);

        EnderecoResponseDTO response = new EnderecoResponseDTO();

        if (entity.isPresent()) {
            response.setEnderecoId(entity.get().getEnderecoId());
            response.setLogradouro(entity.get().getLogradouro());
            response.setEstado(entity.get().getEstado());
            response.setCidade(entity.get().getCidade());
            response.setNumero(entity.get().getNumero());
            response.setCep(entity.get().getCep());
        }
        return Optional.of(response);
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
