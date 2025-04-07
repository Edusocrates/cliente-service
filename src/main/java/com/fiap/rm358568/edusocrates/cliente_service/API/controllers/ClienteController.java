package com.fiap.rm358568.edusocrates.cliente_service.API.controllers;

import com.fiap.rm358568.edusocrates.cliente_service.API.requests.AtualizarClienteRequest;
import com.fiap.rm358568.edusocrates.cliente_service.API.requests.CadastrarClienteRequest;
import com.fiap.rm358568.edusocrates.cliente_service.API.requests.ClienteRequest;
import com.fiap.rm358568.edusocrates.cliente_service.API.responses.ClienteResponse;
import com.fiap.rm358568.edusocrates.cliente_service.dominio.entities.Cliente;
import com.fiap.rm358568.edusocrates.cliente_service.dominio.usecases.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final CadastrarClienteUseCase cadastrarClienteUseCase;
    private final BuscarClientePorIdUseCase buscarClientePorIdUseCase;
    private final ListarClientesUseCase listarClientesUseCase;
    private final AtualizarClienteUseCase atualizarClienteUseCase;
    private final DeletarClienteUseCase deletarClienteUseCase;

    @PostMapping
    public ResponseEntity<ClienteResponse> cadastrar(@RequestBody @Valid CadastrarClienteRequest request) {
        ClienteResponse cliente = cadastrarClienteUseCase.executar(request);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(buscarClientePorIdUseCase.executar(id));
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> listarTodos() {
        return ResponseEntity.ok(listarClientesUseCase.executar());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> atualizar(@PathVariable UUID id, @RequestBody @Valid AtualizarClienteRequest request) {
        ClienteResponse clienteAtualizado = atualizarClienteUseCase.executar(id, request);
        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        deletarClienteUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }
}