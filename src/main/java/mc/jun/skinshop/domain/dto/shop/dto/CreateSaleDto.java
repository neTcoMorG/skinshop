package mc.jun.skinshop.domain.dto.shop.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateSaleDto {

    public CreateSaleDto(String title, String text, ItemDto item) {
        this.title = title;
        this.text = text;
        this.item = item;
    }

    @NotNull private String title;
    @Nullable private String text;
    @NotNull ItemDto item;

}
