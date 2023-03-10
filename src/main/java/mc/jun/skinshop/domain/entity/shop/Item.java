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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Item (String itemName, Integer price) {
        if (price < 0)
            throw new IllegalStateException("0보다 작은 값");

        this.itemName = itemName;
        this.price = price;
    }

    private String itemName;
    private Integer price;
}
