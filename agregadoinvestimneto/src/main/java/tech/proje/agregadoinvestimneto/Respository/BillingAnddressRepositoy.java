package tech.proje.agregadoinvestimneto.Respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.proje.agregadoinvestimneto.Entity.BillingAnddress;

import java.util.UUID;


@Repository
public interface BillingAnddressRepositoy extends JpaRepository<BillingAnddress, UUID> {
}
