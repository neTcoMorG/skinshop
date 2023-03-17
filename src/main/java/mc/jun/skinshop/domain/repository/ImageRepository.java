package mc.jun.skinshop.domain.repository;

import mc.jun.skinshop.domain.entity.shop.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {

    Optional<Image> findByUuid (String uuid);
}
