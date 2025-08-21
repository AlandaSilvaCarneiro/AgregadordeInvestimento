package tech.proje.agregadoinvestimneto.Dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public record DtosAsssociateAccountStockEntrada(
        @Schema(description = "Id de um stock", example = "MAG4")
        String stockID,
        @Schema(description = "quantidades de stock", example = "4")
        Integer quant) {
}
