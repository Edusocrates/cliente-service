package com.fiap.rm358568.edusocrates.cliente_service.aplicacao.usecases.impl;

import com.fiap.rm358568.edusocrates.cliente_service.API.exceptions.ClienteNaoEncontradoException;
import com.fiap.rm358568.edusocrates.cliente_service.API.requests.AtualizarClienteRequest;
import com.fiap.rm358568.edusocrates.cliente_service.API.requests.EnderecoRequest;
import com.fiap.rm358568.edusocrates.cliente_service.API.responses.ClienteResponse;
import com.fiap.rm358568.edusocrates.cliente_service.dominio.entities.Cliente;
import com.fiap.rm358568.edusocrates.cliente_service.dominio.entities.Endereco;
import com.fiap.rm358568.edusocrates.cliente_service.dominio.gateways.ClienteGateway;
import com.fiap.rm358568.edusocrates.cliente_service.dominio.usecases.AtualizarClienteUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class AtualizarClienteUseCaseImpl implements AtualizarClienteUseCase {

    private final ClienteGateway clienteGateway;

    @Override
    public ClienteResponse executar(UUID id, AtualizarClienteRequest request) {
        log.info("Verificando se o Cliente Existe! cliente com ID: {}", id);
        Cliente clienteExistente = clienteGateway.buscarPorId(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado"));

        List<Endereco> enderecosDomain = request.enderecos()
                .stream()
                .map(req -> req.toDomain(clienteExistente))
                .toList();

        clienteExistente.atualizarDados(
                request.nome(),
                request.cpf(),
                request.dataNascimento(),
                enderecosDomain
        );

        log.info("Enviado atualização do Cliente com ID: {}", clienteExistente.getId());
        Cliente atualizado = clienteGateway.atualizar(clienteExistente);

        return ClienteResponse.fromDomain(atualizado);
    }
}


