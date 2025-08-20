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
import tech.proje.agregadoinvestimneto.Dtos.DtosStokEntrada;
import tech.proje.agregadoinvestimneto.Entity.Account;
import tech.proje.agregadoinvestimneto.Entity.Stock;
import tech.proje.agregadoinvestimneto.Entity.Usuario;
import tech.proje.agregadoinvestimneto.Respository.StockRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class StockServiceTest {
    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockService stockService;


    @Captor
    private ArgumentCaptor<Account> accountArgumentCaptor;

    @Captor
    private ArgumentCaptor<Stock> stockArgumentCaptor;



    @Nested

    class createStock{
        @Test
        @DisplayName("teste de criação de stock caso de sucesso")
        void createSalveSuccessStock() {
            var dtoStock = new DtosStokEntrada(
                    "MGALU89",
                    "achoes da empresa magalu"
            );

            var stockEntity = new Stock(
                    dtoStock.idstok(),
                    dtoStock.descipt()
            );
            doReturn(stockEntity).when(stockRepository).save(stockArgumentCaptor.capture());
            stockService.createStok(dtoStock);
            var stockOutput = stockArgumentCaptor.getValue();
            assertEquals(stockEntity.getDescription(),stockOutput.getDescription());
            assertEquals(stockEntity.getStockId(),stockOutput.getStockId());
            verify(stockRepository,timeout(1)).save(stockArgumentCaptor.capture());








        }


    }


}