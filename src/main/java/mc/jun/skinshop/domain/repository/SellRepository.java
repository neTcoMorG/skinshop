package mc.jun.skinshop.domain.repository;

import mc.jun.skinshop.domain.entity.shop.Sell;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellRepository extends JpaRepository<Sell, Long> {
}
