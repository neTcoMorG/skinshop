package mc.jun.skinshop.domain.entity.shop;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

    public Item(Category category, Integer price) {
        this.category = category;
        this.price = price;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @NotNull
    private Integer price;
}
