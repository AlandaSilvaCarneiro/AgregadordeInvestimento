package tech.proje.agregadoinvestimneto.Controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.proje.agregadoinvestimneto.Dtos.DtosStokEntrada;
import tech.proje.agregadoinvestimneto.Entity.Stock;
import tech.proje.agregadoinvestimneto.Service.StockService;

@RestController
@RequestMapping("v1/stock")
@Tag(name = "contoller responsavel pela crialão de Stock")
public class ControllerStock {
    @Autowired
    private StockService stockService;

    @PostMapping
    @Operation(summary = "Criação de um entidade Stock", description = "não retorna nada so o HTTP",
            responses = {
                    @ApiResponse(responseCode = "200",description = "criação com sucesso de um stock  no banco"),
                    @ApiResponse(responseCode = "400",description = "requisição inválida (erro de sintaxe, dados incorretos)")
            }

    )
    public ResponseEntity<Void> stocksalve(@RequestBody DtosStokEntrada dtosStock){
        stockService.createStok(dtosStock);
        return ResponseEntity.ok().build();
    }

    }

