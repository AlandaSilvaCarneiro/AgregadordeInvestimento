package tech.proje.agregadoinvestimneto.Respository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.proje.agregadoinvestimneto.Entity.BillingAnddress;

import java.util.UUID;

public interface BillingAnddressRepositoy extends JpaRepository<BillingAnddress, UUID> {
}
