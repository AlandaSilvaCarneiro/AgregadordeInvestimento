package tech.proje.agregadoinvestimneto.Controller;


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
public class ControllerStock {
    @Autowired
    private StockService stockService;

    @PostMapping
    public ResponseEntity<Void> stocksalve(@RequestBody DtosStokEntrada dtosStock){
        stockService.createStok(dtosStock);
        return ResponseEntity.ok().build();
    }

    }

