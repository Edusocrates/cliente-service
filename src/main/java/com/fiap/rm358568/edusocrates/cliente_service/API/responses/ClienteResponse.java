package com.fiap.rm358568.edusocrates.cliente_service.API.responses;

import java.time.LocalDate;

import com.fiap.rm358568.edusocrates.cliente_service.dominio.entities.Cliente;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record ClienteResponse(
        UUID id,
        String nome,
        String cpf,
        LocalDate dataNascimento,
        List<EnderecoResponse> enderecos
) {
    public static ClienteResponse fromDomain(Cliente cliente) {
        List<EnderecoResponse> enderecosResponse = cliente.getEnderecos()
                .stream()
                .map(EnderecoResponse::fromDomain)
                .collect(Collectors.toList());

        return new ClienteResponse(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getDataNascimento(),
                enderecosResponse
        );
    }
}
