package com.fiap.rm358568.edusocrates.cliente_service.API.controller;

import com.fiap.rm358568.edusocrates.cliente_service.API.controllers.ClienteController;
import com.fiap.rm358568.edusocrates.cliente_service.API.requests.AtualizarClienteRequest;
import com.fiap.rm358568.edusocrates.cliente_service.API.requests.CadastrarClienteRequest;
import com.fiap.rm358568.edusocrates.cliente_service.API.responses.ClienteResponse;
import com.fiap.rm358568.edusocrates.cliente_service.dominio.usecases.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteControllerTest {

    @Mock
    private CadastrarClienteUseCase cadastrarClienteUseCase;

    @Mock
    private BuscarClientePorIdUseCase buscarClientePorIdUseCase;

    @Mock
    private ListarClientesUseCase listarClientesUseCase;

    @Mock
    private AtualizarClienteUseCase atualizarClienteUseCase;

    @Mock
    private DeletarClienteUseCase deletarClienteUseCase;

    @InjectMocks
    private ClienteController clienteController;

    @Test
    void cadastrar_deveRetornarClienteResponse_quandoClienteForCadastrado() {
        CadastrarClienteRequest request = new CadastrarClienteRequest("João Silva", "123.456.789-00", LocalDate.of(1990, 5, 15), new ArrayList<>());
        ClienteResponse responseMock = new ClienteResponse(UUID.randomUUID(), "João Silva", "123.456.789-00", LocalDate.of(1990, 5, 15), new ArrayList<>());

        when(cadastrarClienteUseCase.executar(request)).thenReturn(responseMock);

        ResponseEntity<ClienteResponse> response = clienteController.cadastrar(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseMock, response.getBody());
    }

    @Test
    void buscarPorId_deveRetornarClienteResponse_quandoClienteExistir() {
        UUID id = UUID.randomUUID();
        ClienteResponse responseMock = new ClienteResponse(id, "João Silva", "123.456.789-00", LocalDate.of(1990, 5, 15), new ArrayList<>());

        when(buscarClientePorIdUseCase.executar(id)).thenReturn(responseMock);

        ResponseEntity<ClienteResponse> response = clienteController.buscarPorId(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseMock, response.getBody());
    }

    @Test
    void listarTodos_deveRetornarListaDeClientes_quandoClientesExistirem() {
        ClienteResponse responseMock = new ClienteResponse(UUID.randomUUID(), "João Silva", "123.456.789-00", LocalDate.of(1990, 5, 15), new ArrayList<>());

        when(listarClientesUseCase.executar()).thenReturn(List.of(responseMock));

        ResponseEntity<List<ClienteResponse>> response = clienteController.listarTodos();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(responseMock, response.getBody().get(0));
    }

    @Test
    void atualizar_deveRetornarClienteResponse_quandoClienteForAtualizado() {
        UUID id = UUID.randomUUID();
        AtualizarClienteRequest request = new AtualizarClienteRequest("João Silva", "123.456.789-00", LocalDate.of(1990, 5, 15), new ArrayList<>());
        ClienteResponse responseMock = new ClienteResponse(id, "João Silva", "123.456.789-00", LocalDate.of(1990, 5, 15), new ArrayList<>());

        when(atualizarClienteUseCase.executar(id, request)).thenReturn(responseMock);

        ResponseEntity<ClienteResponse> response = clienteController.atualizar(id, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseMock, response.getBody());
    }

    @Test
    void deletar_deveRetornarNoContent_quandoClienteForDeletado() {
        UUID id = UUID.randomUUID();

        doNothing().when(deletarClienteUseCase).executar(id);

        ResponseEntity<Void> response = clienteController.deletar(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(deletarClienteUseCase, times(1)).executar(id);
    }
}