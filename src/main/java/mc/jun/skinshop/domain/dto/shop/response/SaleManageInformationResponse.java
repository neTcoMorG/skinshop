package mc.jun.skinshop.domain.dto.shop.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mc.jun.skinshop.domain.entity.shop.Sale;
import mc.jun.skinshop.domain.entity.shop.SaleStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SaleManageInformationResponse {

    private Long sale_id;
    private String image_url;
    private String item_name;
    private Integer item_price;
    private SaleStatus status;

    public static SaleManageInformationResponse of (Sale sale) {
        return new SaleManageInformationResponse(
                sale.getId(),
                sale.getImages().get(0).getUuid(),
                sale.getItem().getItemName(),
                sale.getItem().getPrice(),
                sale.getStatus()
        );
    }
}
