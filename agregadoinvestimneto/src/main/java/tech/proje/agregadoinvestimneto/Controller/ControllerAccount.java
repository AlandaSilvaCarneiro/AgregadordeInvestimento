package tech.proje.agregadoinvestimneto.Controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.proje.agregadoinvestimneto.Dtos.DtosAsssociateAccountStockEntrada;
import tech.proje.agregadoinvestimneto.Dtos.DtosAsssociateAccountStockSaida;
import tech.proje.agregadoinvestimneto.Service.AccountService;

import java.util.List;

@RestController
@RequestMapping("v1/account")
@Tag(name = "Contrller responsavel pela criação e reotono dos accountsStock no banco ")
public class ControllerAccount {

    @Autowired
    private AccountService accountService;

    @PostMapping("{id}/stock")
    @Operation(summary = "Criação de um AccountStock", description = "não retorna nada so o HTTP",
            responses = {
                    @ApiResponse(responseCode = "200",description = "criação com sucesso de uma accountStock no banco"),
                    @ApiResponse(responseCode = "400",description = "requisição inválida (erro de sintaxe, dados incorretos)")
            }

    )
    public ResponseEntity<Void> createAccount(@PathVariable("id")String accountId, DtosAsssociateAccountStockEntrada dtosStockAccount){
        accountService.createAccountStocksave(accountId,dtosStockAccount);
        return ResponseEntity.ok().build();
    }




    @GetMapping("{id}")
    @Operation(summary = "Retorno de uma lista de accountStock", description = "não retorna uam lista de accountStoks",
            responses = {
                    @ApiResponse(responseCode = "200",description = "Retorno  com sucesso de uma lista de accountStock do banco"),
                    @ApiResponse(responseCode = "400",description = "requisição inválida (erro de sintaxe, dados incorretos)")
            }

    )
    public ResponseEntity<List<DtosAsssociateAccountStockSaida>> listaAccount(@PathVariable("id")String accountId){
       var lista = accountService.listaAccountStock(accountId);
        return ResponseEntity.ok(lista);
    }
}
