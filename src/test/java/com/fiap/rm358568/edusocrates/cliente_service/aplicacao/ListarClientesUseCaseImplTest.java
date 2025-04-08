package com.fiap.rm358568.edusocrates.cliente_service.aplicacao.usecases.impl;

import com.fiap.rm358568.edusocrates.cliente_service.API.responses.ClienteResponse;
import com.fiap.rm358568.edusocrates.cliente_service.dominio.entities.Cliente;
import com.fiap.rm358568.edusocrates.cliente_service.dominio.gateways.ClienteGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ListarClientesUseCaseImplTest {

    @Mock
    private ClienteGateway clienteGateway;

    @InjectMocks
    private ListarClientesUseCaseImpl listarClientesUseCase;

    @Test
    void executar_deveRetornarListaDeClientesResponse_quandoClientesExistirem() {
        // Arrange
        Cliente cliente1 = new Cliente( "João Silva", "12345678900", LocalDate.of(1990, 5, 15), List.of());
        Cliente cliente2 = new Cliente( "Maria Oliveira", "98765432100", LocalDate.of(1985, 8, 20), List.of());
        List<Cliente> clientesMock = List.of(cliente1, cliente2);

        when(clienteGateway.listarTodos()).thenReturn(clientesMock);

        // Act
        List<ClienteResponse> response = listarClientesUseCase.executar();

        // Assert
        assertEquals(2, response.size());
        assertEquals(ClienteResponse.fromDomain(cliente1), response.get(0));
        assertEquals(ClienteResponse.fromDomain(cliente2), response.get(1));
        verify(clienteGateway, times(1)).listarTodos();
    }

    @Test
    void executar_deveRetornarListaVazia_quandoNaoExistiremClientes() {
        // Arrange
        when(clienteGateway.listarTodos()).thenReturn(List.of());

        // Act
        List<ClienteResponse> response = listarClientesUseCase.executar();

        // Assert
        assertEquals(0, response.size());
        verify(clienteGateway, times(1)).listarTodos();
    }
}