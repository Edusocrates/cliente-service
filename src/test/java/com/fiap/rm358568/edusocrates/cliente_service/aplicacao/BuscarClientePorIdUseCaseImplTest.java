package com.fiap.rm358568.edusocrates.cliente_service.aplicacao;

import com.fiap.rm358568.edusocrates.cliente_service.API.exceptions.ClienteNaoEncontradoException;
import com.fiap.rm358568.edusocrates.cliente_service.API.responses.ClienteResponse;
import com.fiap.rm358568.edusocrates.cliente_service.aplicacao.usecases.impl.BuscarClientePorIdUseCaseImpl;
import com.fiap.rm358568.edusocrates.cliente_service.dominio.entities.Cliente;
import com.fiap.rm358568.edusocrates.cliente_service.dominio.gateways.ClienteGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuscarClientePorIdUseCaseImplTest {

    @Mock
    private ClienteGateway clienteGateway;

    @InjectMocks
    private BuscarClientePorIdUseCaseImpl buscarClientePorIdUseCase;

    @Test
    void executar_deveRetornarClienteResponse_quandoClienteExistir() {
        UUID id = UUID.randomUUID();
        Cliente clienteMock = new Cliente( "João Silva", "12345678900", LocalDate.of(1990, 5, 15), null);
        ClienteResponse clienteResponseMock = ClienteResponse.fromDomain(clienteMock);

        when(clienteGateway.buscarPorId(id)).thenReturn(Optional.of(clienteMock));

        ClienteResponse response = buscarClientePorIdUseCase.executar(id);

        assertNotNull(response);
        assertEquals(clienteResponseMock, response);
        verify(clienteGateway, times(1)).buscarPorId(id);
    }

    @Test
    void executar_deveLancarExcecao_quandoClienteNaoExistir() {
        UUID id = UUID.randomUUID();

        when(clienteGateway.buscarPorId(id)).thenReturn(Optional.empty());

        ClienteNaoEncontradoException exception = assertThrows(ClienteNaoEncontradoException.class, () ->
                buscarClientePorIdUseCase.executar(id)
        );

        assertEquals("Cliente não encontrado", exception.getMessage());
        verify(clienteGateway, times(1)).buscarPorId(id);
    }
}