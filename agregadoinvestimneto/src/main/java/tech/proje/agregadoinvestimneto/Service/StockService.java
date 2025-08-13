package tech.proje.agregadoinvestimneto.Service;


import org.springframework.stereotype.Service;
import tech.proje.agregadoinvestimneto.Dtos.DtosStokEntrada;
import tech.proje.agregadoinvestimneto.Entity.Stock;
import tech.proje.agregadoinvestimneto.Respository.StockRepository;

@Service
public class StockService {
    private StockRepository stockRepository;


    public void createStok(DtosStokEntrada dtoStock) {
        var stockEntity = new Stock(
                dtoStock.idstok(),
                dtoStock.descipt()
        );

        stockRepository.save(stockEntity);


    }
}
