package mc.jun.skinshop.domain.dto.shop.response;

import lombok.*;
import mc.jun.skinshop.domain.dto.member.response.MemberResponse;
import mc.jun.skinshop.domain.entity.shop.Shop;
import mc.jun.skinshop.domain.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ShopInformationResponse {

    private Long shop_id;
    private MemberResponse member;
    private String hello;
    private List<SaleInformationResponse> sales;
    private String created;

    public static ShopInformationResponse of (Shop shop) {
        MemberResponse memberResponse = new MemberResponse(shop.getMember().getName(),
                shop.getMember().getProfileImgUrl());

        List<SaleInformationResponse> saleResponse = new ArrayList<>();
        shop.getSales().forEach(sale ->
            saleResponse.add(SaleInformationResponse.of(sale)));

        return new ShopInformationResponse(shop.getId(), memberResponse,
                shop.getHello(), saleResponse, DateUtil.calcDate(shop.getCreated()));
    }
}
