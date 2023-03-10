package mc.jun.skinshop.domain.dto.shop.dto;


import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateSellDto {

    public CreateSellDto(String title, String text, ItemDto item) {
        this.title = title;
        this.text = text;
        this.item = item;
    }

    @NotNull private String title;
    @Nullable private String text;
    @NotNull ItemDto item;

}
