package mc.jun.skinshop.domain.repository;

import mc.jun.skinshop.domain.entity.shop.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByCateName(String name);
}
