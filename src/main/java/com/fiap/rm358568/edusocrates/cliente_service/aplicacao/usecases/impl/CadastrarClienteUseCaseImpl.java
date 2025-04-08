package com.fiap.rm358568.edusocrates.cliente_service.aplicacao.usecases.impl;

import com.fiap.rm358568.edusocrates.cliente_service.API.requests.CadastrarClienteRequest;
import com.fiap.rm358568.edusocrates.cliente_service.API.requests.EnderecoRequest;
import com.fiap.rm358568.edusocrates.cliente_service.API.responses.ClienteResponse;
import com.fiap.rm358568.edusocrates.cliente_service.dominio.entities.Cliente;
import com.fiap.rm358568.edusocrates.cliente_service.dominio.entities.Endereco;
import com.fiap.rm358568.edusocrates.cliente_service.dominio.gateways.ClienteGateway;
import com.fiap.rm358568.edusocrates.cliente_service.dominio.usecases.CadastrarClienteUseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional
public class CadastrarClienteUseCaseImpl implements CadastrarClienteUseCase {

    private final ClienteGateway clienteGateway;

    @Override
    public ClienteResponse executar(CadastrarClienteRequest request) {
        log.info("Iniciando o cadastro do cliente: {}", request.nome());
        Cliente novoCliente = new Cliente(
                request.nome(),
                request.cpf(),
                request.dataNascimento(),
                new ArrayList<>()
        );

        List<Endereco> enderecosDomain = request.enderecos()
                .stream()
                .map(req -> req.toDomain(novoCliente))
                .toList();

        log.info("Adicionando endereços ao cliente: {}", novoCliente.getNome());
        novoCliente.adicionarEndereco(enderecosDomain);


        log.info("Enviando cliente para o gateway: {}", novoCliente.getNome());
        Cliente clienteSalvo = clienteGateway.salvar(novoCliente);

        return ClienteResponse.fromDomain(clienteSalvo);
    }
}

