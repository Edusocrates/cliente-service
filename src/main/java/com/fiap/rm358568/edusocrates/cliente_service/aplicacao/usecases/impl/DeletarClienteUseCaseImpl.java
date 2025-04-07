package com.fiap.rm358568.edusocrates.cliente_service.aplicacao.usecases.impl;


import com.fiap.rm358568.edusocrates.cliente_service.dominio.gateways.ClienteGateway;
import com.fiap.rm358568.edusocrates.cliente_service.dominio.usecases.DeletarClienteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class DeletarClienteUseCaseImpl implements DeletarClienteUseCase {

    private final ClienteGateway clienteGateway;

    @Override
    public void executar(UUID id) {
        // Verifica se o cliente existe
        clienteGateway.buscarPorId(id).orElseThrow(
                () -> new RuntimeException("Cliente não encontrado!")
        );
        clienteGateway.deletarPorId(id);
    }

}