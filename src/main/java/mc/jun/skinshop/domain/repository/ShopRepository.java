package mc.jun.skinshop.domain.repository;

import mc.jun.skinshop.domain.entity.shop.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long> {
}
