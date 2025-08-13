package tech.proje.agregadoinvestimneto.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.proje.agregadoinvestimneto.Dtos.DtosAsssociateAccountStockEntrada;
import tech.proje.agregadoinvestimneto.Dtos.DtosAsssociateAccountStockSaida;
import tech.proje.agregadoinvestimneto.Service.AccountService;

import java.util.List;

@RestController
@RequestMapping("v1/account")
public class ControllerAccount {

    @Autowired
    private AccountService accountService;

    @PostMapping("{id}/stock")
    public ResponseEntity<Void> createAccount(@PathVariable("id")String accountId, DtosAsssociateAccountStockEntrada dtosStockAccount){
        accountService.createAccountStocksave(accountId,dtosStockAccount);
        return ResponseEntity.ok().build();
    }



    @GetMapping("{id}")
    public ResponseEntity<List<DtosAsssociateAccountStockSaida>> createAccount(@PathVariable("id")String accountId){
       var lista = accountService.listaAccountStock(accountId);
        return ResponseEntity.ok(lista);
    }
}
