package tech.proje.agregadoinvestimneto.Respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.proje.agregadoinvestimneto.Entity.Stock;



@Repository
public interface StockRepository extends JpaRepository<Stock,String> {
}
