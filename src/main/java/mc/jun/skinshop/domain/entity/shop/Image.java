package mc.jun.skinshop.domain.entity.shop;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Image {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
