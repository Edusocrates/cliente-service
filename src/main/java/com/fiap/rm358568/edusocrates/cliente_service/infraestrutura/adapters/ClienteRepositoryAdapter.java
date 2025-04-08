package com.fiap.rm358568.edusocrates.cliente_service.infraestrutura.adapters;

import com.fiap.rm358568.edusocrates.cliente_service.dominio.entities.Cliente;
import com.fiap.rm358568.edusocrates.cliente_service.dominio.gateways.ClienteGateway;
import com.fiap.rm358568.edusocrates.cliente_service.infraestrutura.repositories.ClienteRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
/**
 * Adapter para o repositório de clientes.
 * Implementa a interface ClienteGateway e utiliza o ClienteRepository para realizar operações de persistência.
 */
@Slf4j
public class ClienteRepositoryAdapter implements ClienteGateway {

    private final ClienteRepository repository;

    public ClienteRepositoryAdapter(ClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Cliente salvar(Cliente cliente) {
        log.info("Salvando cliente na base! cliente: {}", cliente);
        if (cliente.getId() != null && repository.existsById(cliente.getId())) {
            log.info("Cliente já existe, atualizando...");
            return repository.save(cliente);
        }
        return repository.save(cliente);
    }

    @Override
    public Optional<Cliente> buscarPorId(UUID id) {
        log.info("Buscando cliente por id na base! id: {}", id);
        return repository.buscarPorId(id);
    }


    @Override
    public Optional<Cliente> buscarPorCpf(String cpf) {
        log.info("Buscando cliente por cpf na base! cpf: {}", cpf);
        return repository.findByCpf(cpf);
    }

    @Override
    public List<Cliente> listarTodos() {
        log.info("Listando todos os clientes na base!");
        return repository.findAll();
    }

    @Override
    public void deletarPorId(UUID id) {
        log.info("Deletando cliente por id na base! id: {}", id);
        repository.deleteById(id);
    }

    @Override
    public Cliente atualizar(Cliente cliente) {
        log.info("Atualizando cliente na base! cliente: {}", cliente);
        return repository.save(cliente);
    }

}
