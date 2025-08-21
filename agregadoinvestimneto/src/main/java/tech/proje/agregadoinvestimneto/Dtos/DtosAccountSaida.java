package tech.proje.agregadoinvestimneto.Dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public record DtosAccountSaida(
        @Schema(description = "UUID de uma acount")
        String accountid,
        @Schema(description = "Descrição de um account", example = "conta de de alguma coisa ")
        String descrip) {
}
