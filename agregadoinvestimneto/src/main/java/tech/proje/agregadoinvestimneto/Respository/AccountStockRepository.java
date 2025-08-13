package tech.proje.agregadoinvestimneto.Respository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.proje.agregadoinvestimneto.Entity.AccountStock;

import java.util.UUID;

public interface AccountStockRepository extends JpaRepository<AccountStock,UUID> {
}
