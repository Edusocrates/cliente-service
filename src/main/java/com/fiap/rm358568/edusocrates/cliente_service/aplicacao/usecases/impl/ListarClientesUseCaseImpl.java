package com.fiap.rm358568.edusocrates.cliente_service.aplicacao.usecases.impl;

import com.fiap.rm358568.edusocrates.cliente_service.API.responses.ClienteResponse;
import com.fiap.rm358568.edusocrates.cliente_service.dominio.gateways.ClienteGateway;
import com.fiap.rm358568.edusocrates.cliente_service.dominio.usecases.ListarClientesUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ListarClientesUseCaseImpl implements ListarClientesUseCase {

    private final ClienteGateway clienteGateway;

    @Override
    public List<ClienteResponse> executar() {
        return clienteGateway.listarTodos()
                .stream()
                .map(ClienteResponse::fromDomain)
                .toList();
    }
}
