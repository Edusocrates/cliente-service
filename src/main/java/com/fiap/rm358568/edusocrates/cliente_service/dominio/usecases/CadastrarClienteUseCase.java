package com.fiap.rm358568.edusocrates.cliente_service.dominio.usecases;

import com.fiap.rm358568.edusocrates.cliente_service.API.requests.CadastrarClienteRequest;
import com.fiap.rm358568.edusocrates.cliente_service.API.responses.ClienteResponse;

public interface CadastrarClienteUseCase {
    ClienteResponse executar(CadastrarClienteRequest request);
}