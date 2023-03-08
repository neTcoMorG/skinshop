package mc.jun.skinshop.domain.dto.shop.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class SellResponseDto {

    private String nickname;
    private String avtar;
    private String title;
    private Integer price;
    private LocalDateTime time;
}
