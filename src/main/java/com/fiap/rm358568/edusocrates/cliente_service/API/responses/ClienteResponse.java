package com.fiap.rm358568.edusocrates.cliente_service.API.responses;

import java.time.LocalDate;

public record ClienteResponse(
        String id,
        String nome,
        String cpf,
        LocalDate dataNascimento,
        EnderecoResponse endereco
) {}
