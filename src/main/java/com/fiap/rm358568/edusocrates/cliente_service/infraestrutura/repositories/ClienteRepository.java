package com.fiap.rm358568.edusocrates.cliente_service.infraestrutura.repositories;

import com.fiap.rm358568.edusocrates.cliente_service.dominio.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    Optional<Cliente> findByCpf(String cpf);
}
