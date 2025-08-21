package tech.proje.agregadoinvestimneto.Dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public record DtosStokEntrada(
        @Schema(description = "Id de Stock", example = "PET3")
        String idstok,
        @Schema(description = "descrição desse stock", example = "ações da petrobres")
        String descipt) {
}
