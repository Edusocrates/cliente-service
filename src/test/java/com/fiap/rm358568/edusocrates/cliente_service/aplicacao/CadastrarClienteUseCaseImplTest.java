package com.fiap.rm358568.edusocrates.cliente_service.aplicacao;

import com.fiap.rm358568.edusocrates.cliente_service.API.requests.CadastrarClienteRequest;
import com.fiap.rm358568.edusocrates.cliente_service.API.requests.EnderecoRequest;
import com.fiap.rm358568.edusocrates.cliente_service.API.responses.ClienteResponse;
import com.fiap.rm358568.edusocrates.cliente_service.aplicacao.usecases.impl.CadastrarClienteUseCaseImpl;
import com.fiap.rm358568.edusocrates.cliente_service.dominio.entities.Cliente;
import com.fiap.rm358568.edusocrates.cliente_service.dominio.entities.Endereco;
import com.fiap.rm358568.edusocrates.cliente_service.dominio.gateways.ClienteGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CadastrarClienteUseCaseImplTest {

    @Mock
    private ClienteGateway clienteGateway;

    @InjectMocks
    private CadastrarClienteUseCaseImpl cadastrarClienteUseCase;

    @Test
    void executar_deveCadastrarCliente_quandoDadosForemValidos() {
        // Arrange
        EnderecoRequest enderecoRequest = new EnderecoRequest("Rua A", "123", "04844080", "Cidade", "Estado", null);
        CadastrarClienteRequest request = new CadastrarClienteRequest("João Silva", "12345678900", LocalDate.of(1990, 5, 15), List.of(enderecoRequest));

        Cliente clienteMock = new Cliente("João Silva", "12345678900", LocalDate.of(1990, 5, 15), new ArrayList<>()); // Use uma lista mutável
        Endereco enderecoAtualizado = new Endereco("Rua A", "123", "04844080", "Estado", "SP", "clienteExistente");
        clienteMock.adicionarEndereco(new ArrayList<>(List.of(enderecoAtualizado))); // Converta para lista mutável

        Cliente clienteSalvoMock = new Cliente("João Silva", "12345678900", LocalDate.of(1990, 5, 15), List.of(enderecoAtualizado));
        ClienteResponse clienteResponseMock = ClienteResponse.fromDomain(clienteSalvoMock);

        when(clienteGateway.salvar(any(Cliente.class))).thenReturn(clienteSalvoMock);

        // Act
        ClienteResponse response = cadastrarClienteUseCase.executar(request);

        // Assert
        assertNotNull(response);
        assertEquals(clienteResponseMock, response);
        verify(clienteGateway, times(1)).salvar(any(Cliente.class));
    }
}