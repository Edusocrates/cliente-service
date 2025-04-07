package com.fiap.rm358568.edusocrates.cliente_service.infraestrutura.repositories;

import com.fiap.rm358568.edusocrates.cliente_service.dominio.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    Optional<Cliente> findByCpf(String cpf);
}
