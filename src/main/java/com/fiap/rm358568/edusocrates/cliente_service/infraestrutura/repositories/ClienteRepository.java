package com.fiap.rm358568.edusocrates.cliente_service.infraestrutura.repositories;

import com.fiap.rm358568.edusocrates.cliente_service.dominio.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    Optional<Cliente> findByCpf(String cpf);

    @Query("SELECT c FROM Cliente c LEFT JOIN FETCH c.enderecos WHERE c.id = :id")
    Optional<Cliente> buscarPorId(@Param("id") UUID id);

    @Query("SELECT c FROM Cliente c LEFT JOIN FETCH c.enderecos")
    List<Cliente> findAllComEnderecos();
}
