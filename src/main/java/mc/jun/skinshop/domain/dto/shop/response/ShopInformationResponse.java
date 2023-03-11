package mc.jun.skinshop.domain.dto.shop.response;

import lombok.*;
import mc.jun.skinshop.domain.entity.member.Member;
import mc.jun.skinshop.domain.entity.shop.Sale;
import mc.jun.skinshop.domain.entity.shop.Shop;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ShopInformationResponse {

    private Long shop_id;
    private Member member;
    private String hello;
    private List<Sale> sales;

    public static ShopInformationResponse of (Shop shop) {
        return new ShopInformationResponse(shop.getId(), shop.getMember(),
                shop.getHello(), shop.getSales());
    }
}
