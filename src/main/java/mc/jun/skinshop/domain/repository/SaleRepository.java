package mc.jun.skinshop.domain.repository;

import mc.jun.skinshop.domain.entity.shop.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
