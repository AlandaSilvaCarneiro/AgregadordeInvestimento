package tech.proje.agregadoinvestimneto.Respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.proje.agregadoinvestimneto.Entity.AccountStock;

import java.util.UUID;


@Repository
public interface AccountStockRepository extends JpaRepository<AccountStock,UUID> {
}
