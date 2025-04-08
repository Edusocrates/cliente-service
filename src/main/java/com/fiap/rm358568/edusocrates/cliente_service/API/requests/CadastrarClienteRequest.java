package com.fiap.rm358568.edusocrates.cliente_service.API.requests;

import com.fiap.rm358568.edusocrates.cliente_service.dominio.entities.Cliente;
import com.fiap.rm358568.edusocrates.cliente_service.dominio.entities.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public record CadastrarClienteRequest(

        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotBlank(message = "CPF é obrigatório")
        String cpf,

        @NotNull(message = "Data de nascimento é obrigatória")
        @Past(message = "Data de nascimento deve ser no passado")
        LocalDate dataNascimento,

        @NotNull(message = "Endereços não podem ser nulos")
        List<EnderecoRequest> enderecos

) {
    public Cliente toDomain() {
        Cliente cliente = new Cliente( nome, cpf, dataNascimento, new ArrayList<>());

        List<Endereco> enderecosDomain = enderecos
                .stream()
                .map(req -> req.toDomain(cliente))
                .toList();

        cliente.adicionarEndereco(enderecosDomain);
        return cliente;
    }
}
