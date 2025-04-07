package com.fiap.rm358568.edusocrates.cliente_service.dominio.usecases;

import com.fiap.rm358568.edusocrates.cliente_service.API.responses.ClienteResponse;

import java.util.List;

public interface ListarClientesUseCase {
    List<ClienteResponse> executar();
}
