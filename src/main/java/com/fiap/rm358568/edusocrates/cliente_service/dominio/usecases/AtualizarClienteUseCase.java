package com.fiap.rm358568.edusocrates.cliente_service.dominio.usecases;

import com.fiap.rm358568.edusocrates.cliente_service.API.requests.AtualizarClienteRequest;
import com.fiap.rm358568.edusocrates.cliente_service.API.responses.ClienteResponse;

import java.util.UUID;

public interface AtualizarClienteUseCase {
    ClienteResponse executar(UUID id, AtualizarClienteRequest request);
}
