package com.fiap.rm358568.edusocrates.cliente_service.API.responses;

import com.fiap.rm358568.edusocrates.cliente_service.dominio.entities.Endereco;

public record EnderecoResponse(
        String rua,
        String numero,
        String cep,
        String cidade,
        String estado,
        String complemento
) {
    public static EnderecoResponse fromDomain(Endereco endereco) {
        return new EnderecoResponse(
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getCep(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getComplemento()
        );
    }
}