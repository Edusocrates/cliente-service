package com.fiap.rm358568.edusocrates.cliente_service.aplicacao.usecases.impl;

import com.fiap.rm358568.edusocrates.cliente_service.API.requests.CadastrarClienteRequest;
import com.fiap.rm358568.edusocrates.cliente_service.API.requests.EnderecoRequest;
import com.fiap.rm358568.edusocrates.cliente_service.API.responses.ClienteResponse;
import com.fiap.rm358568.edusocrates.cliente_service.dominio.entities.Cliente;
import com.fiap.rm358568.edusocrates.cliente_service.dominio.entities.Endereco;
import com.fiap.rm358568.edusocrates.cliente_service.dominio.gateways.ClienteGateway;
import com.fiap.rm358568.edusocrates.cliente_service.dominio.usecases.CadastrarClienteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CadastrarClienteUseCaseImpl implements CadastrarClienteUseCase {

    private final ClienteGateway clienteGateway;

    @Override
    public ClienteResponse executar(CadastrarClienteRequest request) {
        Cliente novoCliente = new Cliente(
                UUID.randomUUID(),
                request.nome(),
                request.cpf(),
                request.dataNascimento(),
                new ArrayList<>()
        );

        List<Endereco> enderecosDomain = request.enderecos()
                .stream()
                .map(req -> req.toDomain(novoCliente))
                .toList();

        novoCliente.adicionarEndereco(enderecosDomain);

        Cliente clienteSalvo = clienteGateway.salvar(novoCliente);

        return ClienteResponse.fromDomain(clienteSalvo);
    }
}

