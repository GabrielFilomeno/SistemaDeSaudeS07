package com.example.semana7.DTOs;

import com.example.semana7.entities.EnderecoEntity;

import java.time.LocalDate;

public class PacienteResponseDTO {

    private Long pacienteId;
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    private String telefone;
    private String email;
    private EnderecoEntity enderecoEntity;

    public PacienteResponseDTO(Long pacienteId, String nome, LocalDate dataNascimento, String cpf, String telefone, String email, EnderecoEntity endereco) {
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EnderecoEntity getEnderecoEntity() {
        return enderecoEntity;
    }

    public void setEnderecoEntity(EnderecoEntity enderecoEntity) {
        this.enderecoEntity = enderecoEntity;
    }
}
