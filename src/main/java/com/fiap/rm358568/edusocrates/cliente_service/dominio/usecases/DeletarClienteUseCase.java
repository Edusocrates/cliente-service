package com.fiap.rm358568.edusocrates.cliente_service.dominio.usecases;

import java.util.UUID;

public interface DeletarClienteUseCase {
    void executar(UUID id);
}