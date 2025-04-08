package com.fiap.rm358568.edusocrates.cliente_service.API.controllers;

import com.fiap.rm358568.edusocrates.cliente_service.API.requests.AtualizarClienteRequest;
import com.fiap.rm358568.edusocrates.cliente_service.API.requests.CadastrarClienteRequest;
import com.fiap.rm358568.edusocrates.cliente_service.API.responses.ClienteResponse;
import com.fiap.rm358568.edusocrates.cliente_service.dominio.usecases.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
@Tag(name = "Configurações cliente", description = "Operações relacionadas as configurações e funcionamento de cliente")
@Slf4j
public class ClienteController {

    private final CadastrarClienteUseCase cadastrarClienteUseCase;
    private final BuscarClientePorIdUseCase buscarClientePorIdUseCase;
    private final ListarClientesUseCase listarClientesUseCase;
    private final AtualizarClienteUseCase atualizarClienteUseCase;
    private final DeletarClienteUseCase deletarClienteUseCase;



    @PostMapping
    @Tag(name = "Cadastrar cliente", description = "Cadastrar um novo cliente")
    @Operation(summary = "Cadastrar cliente", description = "Cadastrar um novo cliente")
    public ResponseEntity<ClienteResponse> cadastrar(@RequestBody @Valid CadastrarClienteRequest request) {
        log.info("Cadastrando cliente: {}", request);
        ClienteResponse cliente = cadastrarClienteUseCase.executar(request);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/{id}")
    @Tag(name = "Buscar cliente por ID", description = "Buscar um cliente por ID")
    @Operation(summary = "Buscar cliente por ID", description = "Buscar um cliente por ID")
    public ResponseEntity<ClienteResponse> buscarPorId(@PathVariable UUID id) {
        log.info("Buscando cliente por ID: {}", id);
        return ResponseEntity.ok(buscarClientePorIdUseCase.executar(id));
    }

    @GetMapping
    @Tag(name = "Listar clientes", description = "Listar todos os clientes cadastrados")
    @Operation(summary = "Listar clientes", description = "Listar todos os clientes cadastrados")
    public ResponseEntity<List<ClienteResponse>> listarTodos() {
        log.info("Listando todos os clientes!");
        return ResponseEntity.ok(listarClientesUseCase.executar());
    }

    @PutMapping("/{id}")
    @Tag(name = "Atualizar cliente", description = "Atualizar um cliente existente")
    @Operation(summary = "Atualizar cliente", description = "Atualizar um cliente existente")
    public ResponseEntity<ClienteResponse> atualizar(@PathVariable UUID id, @RequestBody @Valid AtualizarClienteRequest request) {
        log.info("Atualizando cliente com ID: {}", id);
        ClienteResponse clienteAtualizado = atualizarClienteUseCase.executar(id, request);
        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping("/{id}")
    @Tag(name = "Deletar cliente", description = "Deletar um cliente existente")
    @Operation(summary = "Deletar cliente", description = "Deletar um cliente existente")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        log.info("Deletando cliente com ID: {}", id);
        deletarClienteUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }
}