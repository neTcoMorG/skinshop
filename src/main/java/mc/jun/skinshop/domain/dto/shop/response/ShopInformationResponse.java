package mc.jun.skinshop.domain.dto.shop.response;

import lombok.*;
import mc.jun.skinshop.domain.entity.member.Member;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ShopInformationResponse {

    private Long shop_id;
    private Member member;
    private String hello;

    public static ShopInformationResponse of (Member member) {
        return null;
    }
}
