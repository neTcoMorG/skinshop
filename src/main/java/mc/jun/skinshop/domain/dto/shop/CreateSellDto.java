package mc.jun.skinshop.domain.dto.shop;


import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateSellDto {

    @NotNull private String title;
    @Nullable private String text;
    @NotNull ItemDto item;
}
