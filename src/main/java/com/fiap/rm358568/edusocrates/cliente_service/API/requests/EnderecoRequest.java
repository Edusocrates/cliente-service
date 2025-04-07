package com.fiap.rm358568.edusocrates.cliente_service.API.requests;


import com.fiap.rm358568.edusocrates.cliente_service.dominio.entities.Cliente;
import com.fiap.rm358568.edusocrates.cliente_service.dominio.entities.Endereco;
import jakarta.validation.constraints.NotBlank;

public record EnderecoRequest(

        @NotBlank(message = "Rua é obrigatória")
        String rua,

        @NotBlank(message = "Número é obrigatório")
        String numero,

        @NotBlank(message = "CEP é obrigatório")
        String cep,

        @NotBlank(message = "Cidade é obrigatória")
        String cidade,

        @NotBlank(message = "Estado é obrigatório")
        String estado,

        String complemento

) {
    public Endereco toDomain(Cliente cliente) {
        return new Endereco(
                rua,
                numero,
                cep,
                cidade,
                estado,
                complemento
        );
    }
}

