package com.fiap.rm358568.edusocrates.cliente_service.aplicacao.usecases.impl;


import com.fiap.rm358568.edusocrates.cliente_service.API.exceptions.ClienteNaoEncontradoException;
import com.fiap.rm358568.edusocrates.cliente_service.dominio.gateways.ClienteGateway;
import com.fiap.rm358568.edusocrates.cliente_service.dominio.usecases.DeletarClienteUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
/**
 * Implementação do caso de uso para deletar um cliente.
 */
@Slf4j
public class DeletarClienteUseCaseImpl implements DeletarClienteUseCase {

    private final ClienteGateway clienteGateway;

    @Override
    public void executar(UUID id) {
        log.info("Iniciando o processo de deleção do cliente com ID: {}", id);
        clienteGateway.buscarPorId(id).orElseThrow(
                () -> new ClienteNaoEncontradoException("Cliente não encontrado!")
        );
        log.info("Cliente encontrado, iniciando a deleção do cliente com ID: {}", id);
        clienteGateway.deletarPorId(id);
    }

}