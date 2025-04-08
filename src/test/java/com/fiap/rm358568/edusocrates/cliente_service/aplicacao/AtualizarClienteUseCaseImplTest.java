package com.fiap.rm358568.edusocrates.cliente_service.aplicacao;

import com.fiap.rm358568.edusocrates.cliente_service.API.exceptions.ClienteNaoEncontradoException;
import com.fiap.rm358568.edusocrates.cliente_service.API.requests.AtualizarClienteRequest;
import com.fiap.rm358568.edusocrates.cliente_service.API.requests.EnderecoRequest;
import com.fiap.rm358568.edusocrates.cliente_service.API.responses.ClienteResponse;
import com.fiap.rm358568.edusocrates.cliente_service.aplicacao.usecases.impl.AtualizarClienteUseCaseImpl;
import com.fiap.rm358568.edusocrates.cliente_service.dominio.entities.Cliente;
import com.fiap.rm358568.edusocrates.cliente_service.dominio.entities.Endereco;
import com.fiap.rm358568.edusocrates.cliente_service.dominio.gateways.ClienteGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AtualizarClienteUseCaseImplTest {

    @Mock
    private ClienteGateway clienteGateway;

    @InjectMocks
    private AtualizarClienteUseCaseImpl atualizarClienteUseCase;

    @Test
    void executar_deveAtualizarCliente_quandoClienteExistir() {
        UUID id = UUID.randomUUID();
        Cliente clienteExistente = new Cliente( "João Silva", "40345136898", LocalDate.of(1990, 5, 15), List.of());
        EnderecoRequest enderecoRequest = new EnderecoRequest("Rua A", "123", "04844080", "Cidade", "Estado", null);
        AtualizarClienteRequest request = new AtualizarClienteRequest("João Atualizado", "987.654.321-00", LocalDate.of(1991, 6, 20), List.of(enderecoRequest));
        Endereco enderecoAtualizado = new Endereco( "Rua A", "123", "04844080", "Estado", "SP", "clienteExistente");
        Cliente clienteAtualizado = new Cliente("João Atualizado", "40345136898", LocalDate.of(1991, 6, 20), List.of(enderecoAtualizado));

        when(clienteGateway.buscarPorId(id)).thenReturn(Optional.of(clienteExistente));
        when(clienteGateway.atualizar(clienteExistente)).thenReturn(clienteAtualizado);

        ClienteResponse response = atualizarClienteUseCase.executar(id, request);

        assertNotNull(response);
        assertEquals("João Atualizado", response.nome());
        assertEquals("40345136898", response.cpf());
        assertEquals(LocalDate.of(1991, 6, 20), response.dataNascimento());
        verify(clienteGateway, times(1)).buscarPorId(id);
        verify(clienteGateway, times(1)).atualizar(clienteExistente);
    }

    @Test
    void executar_deveLancarExcecao_quandoClienteNaoExistir() {
        UUID id = UUID.randomUUID();
        AtualizarClienteRequest request = new AtualizarClienteRequest("João Atualizado", "987.654.321-00", LocalDate.of(1991, 6, 20), List.of());

        when(clienteGateway.buscarPorId(id)).thenReturn(Optional.empty());

        ClienteNaoEncontradoException exception = assertThrows(ClienteNaoEncontradoException.class, () ->
                atualizarClienteUseCase.executar(id, request)
        );

        assertEquals("Cliente não encontrado", exception.getMessage());
        verify(clienteGateway, times(1)).buscarPorId(id);
        verify(clienteGateway, never()).atualizar(any());
    }
}