package mc.jun.skinshop.domain.dto.shop.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mc.jun.skinshop.domain.entity.shop.Item;
import mc.jun.skinshop.domain.entity.shop.Sale;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SaleInformationResponse {

    private String name;
    private Long saleId;
    private Item item;
    private String text;
    private LocalDateTime created;

    public static SaleInformationResponse of (Sale sale) {
        return new SaleInformationResponse(
                sale.getShop().getMember().getName(),
                sale.getId(), sale.getItem(), sale.getText(),
                sale.getCreated());
    }

}
