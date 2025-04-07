package com.fiap.rm358568.edusocrates.cliente_service.API.responses;

public record EnderecoResponse(
        String rua,
        String numero,
        String cep,
        String cidade,
        String estado,
        String complemento
) {}
