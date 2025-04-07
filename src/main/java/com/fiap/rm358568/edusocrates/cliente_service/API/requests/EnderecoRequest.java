package com.fiap.rm358568.edusocrates.cliente_service.API.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EnderecoRequest(
        @NotBlank String rua,
        @NotBlank String numero,

        @NotBlank
        @Pattern(regexp = "\\d{8}", message = "CEP deve conter 8 dígitos numéricos")
        String cep,

        @NotBlank String cidade,
        @NotBlank String estado,
        String complemento
) {}
