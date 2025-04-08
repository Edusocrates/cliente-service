package com.fiap.rm358568.edusocrates.cliente_service.aplicacao;

import com.fiap.rm358568.edusocrates.cliente_service.API.exceptions.ClienteNaoEncontradoException;
import com.fiap.rm358568.edusocrates.cliente_service.aplicacao.usecases.impl.DeletarClienteUseCaseImpl;
import com.fiap.rm358568.edusocrates.cliente_service.dominio.entities.Cliente;
import com.fiap.rm358568.edusocrates.cliente_service.dominio.gateways.ClienteGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeletarClienteUseCaseImplTest {

    @Mock
    private ClienteGateway clienteGateway;

    @InjectMocks
    private DeletarClienteUseCaseImpl deletarClienteUseCase;

    @Test
    void executar_deveDeletarCliente_quandoClienteExistir() {
        // Arrange
        UUID id = UUID.randomUUID();
        when(clienteGateway.buscarPorId(id)).thenReturn(Optional.of(new Cliente())); // Simula cliente existente

        // Act
        deletarClienteUseCase.executar(id);

        // Assert
        verify(clienteGateway, times(1)).buscarPorId(id);
        verify(clienteGateway, times(1)).deletarPorId(id);
    }

    @Test
    void executar_deveLancarExcecao_quandoClienteNaoExistir() {
        // Arrange
        UUID id = UUID.randomUUID();
        when(clienteGateway.buscarPorId(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ClienteNaoEncontradoException.class, () -> deletarClienteUseCase.executar(id));
        verify(clienteGateway, times(1)).buscarPorId(id);
        verify(clienteGateway, never()).deletarPorId(id);
    }
}