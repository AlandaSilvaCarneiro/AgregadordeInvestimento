package tech.proje.agregadoinvestimneto.Dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public record DtosAsssociateAccountStockSaida(
        @Schema(description = "Id de um stock", example = "MAGL4")
        String stockId,
        @Schema(description = "quantidade dessse Stocks", example = "4")
        Integer quat,
        @Schema(description = "endereço do usuario da conta", example = "rua junho de março")
        Double tota) {
}
