package com.fiap.rm358568.edusocrates.cliente_service.aplicacao.usecases.impl;

import com.fiap.rm358568.edusocrates.cliente_service.API.exceptions.ClienteNaoEncontradoException;
import com.fiap.rm358568.edusocrates.cliente_service.API.responses.ClienteResponse;
import com.fiap.rm358568.edusocrates.cliente_service.dominio.entities.Cliente;
import com.fiap.rm358568.edusocrates.cliente_service.dominio.gateways.ClienteGateway;
import com.fiap.rm358568.edusocrates.cliente_service.dominio.usecases.BuscarClientePorIdUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
/**
 * Implementação do caso de uso para buscar um cliente por ID.
 */
@Slf4j
public class BuscarClientePorIdUseCaseImpl implements BuscarClientePorIdUseCase {

    private final ClienteGateway clienteGateway;

    @Override
    public ClienteResponse executar(UUID id) {
        log.info("Buscando cliente com ID: {}", id);
        Cliente cliente = clienteGateway.buscarPorId(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado"));
        return ClienteResponse.fromDomain(cliente);
    }
}
