package mc.jun.skinshop.domain.dto.shop.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mc.jun.skinshop.domain.entity.shop.Item;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemDto {

    public ItemDto (Integer price, Long cate_id, String name) {
        this.price = price;
        this.cate_id = cate_id;
        this.name = name;
    }

    @NotNull private Integer price;
    @NotNull private Long cate_id;
    @Nullable private String name;

    public Item toEntity () {
        return new Item(this.name, this.price);
    }
}
