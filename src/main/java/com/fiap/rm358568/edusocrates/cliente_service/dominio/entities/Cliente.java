package com.fiap.rm358568.edusocrates.cliente_service.dominio.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
@Table(name = "clientes")
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    private String nome;
    private String cpf;
    private LocalDate dataNascimento;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cliente_id")
    private List<Endereco> enderecos = new ArrayList<>();


    public Cliente( String nome, String cpf, LocalDate dataNascimento, List<Endereco> enderecos) {
        validarCpf(cpf);
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        if (enderecos != null) {
            this.enderecos = enderecos;
        }
    }

    public void adicionarEndereco(List<Endereco> enderecos) {
        if (enderecos != null) {
            this.enderecos.addAll(enderecos);
        }
    }

    private void validarCpf(String cpf) {
        if (cpf == null || cpf.length() != 11) {
            throw new IllegalArgumentException("CPF inválido.");
        }
    }

    public void atualizarDados(String nome, String cpf, LocalDate dataNascimento, List<Endereco> enderecos) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.enderecos = enderecos;
    }
}
