package tech.proje.agregadoinvestimneto.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tech.proje.agregadoinvestimneto.Dtos.DtosAccountSaida;
import tech.proje.agregadoinvestimneto.Dtos.DtosAsssociateAccountStockEntrada;
import tech.proje.agregadoinvestimneto.Dtos.DtosAsssociateAccountStockSaida;
import tech.proje.agregadoinvestimneto.Entity.AccountStock;
import tech.proje.agregadoinvestimneto.Entity.AccountStockID;
import tech.proje.agregadoinvestimneto.Respository.AccountRepository;
import tech.proje.agregadoinvestimneto.Respository.AccountStockRepository;
import tech.proje.agregadoinvestimneto.Respository.StockRepository;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService {
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountStockRepository accountStockRepository;

    public void createAccountStocksave(String accountId, DtosAsssociateAccountStockEntrada dtosStockAccount) {
            var account = accountRepository.findById(UUID.fromString(accountId))
                    .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));

            var stock = stockRepository.findById(dtosStockAccount.stockID())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));


            var id = new AccountStockID(account.getIdAccout(),stock.getStockId());

            var accoutStock = new AccountStock(
                    id,
                    stock,
                    account,
                    dtosStockAccount.quant()
            );
            accountStockRepository.save(accoutStock);

    }

    public List<DtosAsssociateAccountStockSaida> listaAccountStock(String accountId) {
        var account=  accountRepository.findById(UUID.fromString(accountId))
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));

        var listaAccountStocks = account.getAccountStocks().stream()
                .map(as -> new DtosAsssociateAccountStockSaida(as.getStock().getStockId(),as.getQuantity(),0.0)).toList();

        return  listaAccountStocks;
    }
}
