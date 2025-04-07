package com.fiap.rm358568.edusocrates.cliente_service.dominio.gateways;

import com.fiap.rm358568.edusocrates.cliente_service.dominio.entities.Cliente;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

public interface ClienteGateway {
    Cliente salvar(Cliente cliente);
    Optional<Cliente> buscarPorId(UUID id);
    Optional<Cliente> buscarPorCpf(String cpf);
    List<Cliente> listarTodos();
    void deletarPorId(UUID id);
}
