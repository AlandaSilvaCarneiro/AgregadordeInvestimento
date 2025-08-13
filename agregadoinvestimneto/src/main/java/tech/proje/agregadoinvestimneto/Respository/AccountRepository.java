package tech.proje.agregadoinvestimneto.Respository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.proje.agregadoinvestimneto.Entity.Account;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
}
