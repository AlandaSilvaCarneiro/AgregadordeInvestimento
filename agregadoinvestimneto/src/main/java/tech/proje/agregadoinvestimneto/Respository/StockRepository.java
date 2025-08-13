package tech.proje.agregadoinvestimneto.Respository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.proje.agregadoinvestimneto.Entity.Stock;

public interface StockRepository extends JpaRepository<Stock,String> {
}
