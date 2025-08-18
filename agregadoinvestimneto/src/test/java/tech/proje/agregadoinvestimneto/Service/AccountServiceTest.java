package tech.proje.agregadoinvestimneto.Service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import tech.proje.agregadoinvestimneto.Dtos.DtosAsssociateAccountStockEntrada;
import tech.proje.agregadoinvestimneto.Entity.Account;
import tech.proje.agregadoinvestimneto.Entity.AccountStock;
import tech.proje.agregadoinvestimneto.Entity.AccountStockID;
import tech.proje.agregadoinvestimneto.Entity.Stock;
import tech.proje.agregadoinvestimneto.Respository.AccountRepository;
import tech.proje.agregadoinvestimneto.Respository.AccountStockRepository;
import tech.proje.agregadoinvestimneto.Respository.StockRepository;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @Mock
    private StockRepository stockRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountStockRepository accountStockRepository;

    @InjectMocks
    private AccountService accountService;

    @Captor
    private ArgumentCaptor<Account> accountArgumentCaptor;

    @Captor
    private ArgumentCaptor<Stock> stockArgumentCaptor;

    @Captor
    private ArgumentCaptor<AccountStock> accountStockArgumentCaptor;

    @Captor
    private ArgumentCaptor<String> stringArgumentCaptor;

    @Captor
    private ArgumentCaptor<UUID> uuidArgumentCaptor;


    @Nested
    class accountSalveCases{
        @Test
        @DisplayName("Caso de succeso  salvamento account no banco")
        void salveAccountSuccess(){

        }

        @Test
        @DisplayName("Caso de succeso  no disparo da exeção na busca de um id acconut no banco")
        void salveExceptionThrownCase1(){
            var idAccount = UUID.randomUUID();
            var input = new DtosAsssociateAccountStockEntrada(
                    "MAG4",
                    23
            );

            doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND)).when(accountRepository).findById(any());


            assertThrows(ResponseStatusException.class,() -> accountService.createAccountStocksave(idAccount.toString(),input));
            verify(accountRepository,timeout(1)).findById(any());

        }


        @Test
        @DisplayName("Caso de succeso  no disparo da exeção na busca de um id stock no banco")
        void salveExceptionThrownCase2(){
            var idAccount = UUID.randomUUID();
            var input = new DtosAsssociateAccountStockEntrada(
                    "MAG4",
                    23
            );

            Account account = new Account();

            doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND)).when(stockRepository).findById(any());
            doReturn(Optional.of(account)).when(accountRepository).findById(any());
            assertThrows(ResponseStatusException.class,() -> accountService.createAccountStocksave(idAccount.toString(),input));
            verify(accountRepository,timeout(1)).findById(any());
            verify(stockRepository,timeout(1)).findById(any());

        }


        @Test
        @DisplayName("Caso de succeso no salvameto")
        void salveCase1(){
            Integer quant = 23;
            var idStock ="MAG4";
            var stock = new Stock(
                    idStock,
                    "magalu"
            );
            var account = new Account();
            var idAcount =UUID.randomUUID();
            account.setIdAccout(idAcount);
            var input = new DtosAsssociateAccountStockEntrada(
                    idStock,
                    23
            );

            var idAccounStock = new AccountStockID(idAcount,idStock);

            var accountStockEnti = new AccountStock(
                    idAccounStock,
                    stock,
                    account,
                    quant
            );

            doReturn(Optional.of(stock)).when(stockRepository).findById(stringArgumentCaptor.capture());
            doReturn(Optional.of(account)).when(accountRepository).findById(uuidArgumentCaptor.capture());
            doReturn(accountStockEnti).when(accountStockRepository).save(accountStockArgumentCaptor.capture());

            accountService.createAccountStocksave(idAcount.toString(),input);

            assertEquals(stock.getStockId(),stringArgumentCaptor.getValue());
            assertEquals(account.getIdAccout(),uuidArgumentCaptor.getValue());
            assertEquals(idAccounStock,accountStockEnti.getId());
            assertEquals(account,accountStockEnti.getAccount());
            assertEquals(stock,accountStockEnti.getStock());
            assertEquals(quant,accountStockEnti.getQuantity());



        }
    }

}