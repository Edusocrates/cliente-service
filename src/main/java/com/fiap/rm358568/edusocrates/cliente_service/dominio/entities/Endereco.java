package com.fiap.rm358568.edusocrates.cliente_service.dominio.entities;

import lombok.Getter;

import jakarta.persistence.*;

import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String rua;
    private String numero;
    private String cep;
    private String cidade;
    private String estado;
    private String complemento;


    public Endereco(String rua, String numero, String cep, String cidade, String estado, String complemento) {
        validarCep(cep);
        this.rua = rua;
        this.numero = numero;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
        this.complemento = complemento;
    }


    private void validarCep(String cep) {
        if (cep == null || cep.length() != 8) {
            throw new IllegalArgumentException("CEP inválido. Deve conter 8 dígitos.");
        }
    }
}

