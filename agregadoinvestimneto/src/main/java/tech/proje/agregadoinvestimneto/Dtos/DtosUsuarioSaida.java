package tech.proje.agregadoinvestimneto.Dtos;


import io.swagger.v3.oas.annotations.media.Schema;

public record DtosUsuarioSaida(
        @Schema(description = "nome do usuario", example = "Alan Perreire")
        String nome,
        @Schema(description = "senha de um usuario", example = "senha1234ps")
        String senha,
        @Schema(description = "email de um usuario", example = "alyno@gmail.com")
        String email) {
}

