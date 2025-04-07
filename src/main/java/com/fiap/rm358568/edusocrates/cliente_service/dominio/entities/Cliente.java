package com.fiap.rm358568.edusocrates.cliente_service.dominio.entities;


import lombok.Getter;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class Cliente {

    private UUID id;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private List<Endereco> enderecos = new ArrayList<>();

    public Cliente(UUID id, String nome, String cpf, LocalDate dataNascimento, List<Endereco> enderecos) {
        validarCpf(cpf);
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        if (enderecos != null) {
            this.enderecos = enderecos;
        }
    }

    public void adicionarEndereco(Endereco endereco) {
        this.enderecos.add(endereco);
    }

    private void validarCpf(String cpf) {
        if (cpf == null || cpf.length() != 11) {
            throw new IllegalArgumentException("CPF inválido.");
        }
    }
}
