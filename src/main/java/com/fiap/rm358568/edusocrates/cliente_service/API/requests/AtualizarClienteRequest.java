package com.fiap.rm358568.edusocrates.cliente_service.API.requests;

import java.time.LocalDate;
import java.util.List;

public record AtualizarClienteRequest(
        String nome,
        String cpf,
        LocalDate dataNascimento,
        List<EnderecoRequest> enderecos
) {}
