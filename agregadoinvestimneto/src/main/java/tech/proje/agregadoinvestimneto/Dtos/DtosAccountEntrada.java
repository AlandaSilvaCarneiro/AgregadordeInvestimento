package tech.proje.agregadoinvestimneto.Dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public record DtosAccountEntrada(
        @Schema(description = "descrição da acount ", example = "conta do alan ")
        String descrip,

        @Schema(description = "numero da conta", example = "900")
        String number,

        @Schema(description = "endereço do usuario da conta", example = "rua junho de março")
        String street) {
}
