package mc.jun.skinshop.domain.dto.shop;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemDto {

    @NotNull private Integer price;
    @NotNull private Long cate_id;
    @Nullable private String name;
}
