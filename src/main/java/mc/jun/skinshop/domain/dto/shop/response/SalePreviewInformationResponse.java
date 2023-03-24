package mc.jun.skinshop.domain.dto.shop.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mc.jun.skinshop.domain.entity.shop.Sale;
import mc.jun.skinshop.domain.util.DateUtil;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SalePreviewInformationResponse {

    public SalePreviewInformationResponse (Long saleId, String title, String created, String image_url, Integer price) {
        this.saleId = saleId;
        this.title = title;
        this.created = created;
        this.image_url = image_url;
        this.price = price;
    }

    private Long saleId;
    private String title;
    private String created;
    private String image_url;
    private Integer price;

    public static SalePreviewInformationResponse of (Sale sale) {
        return new SalePreviewInformationResponse(
                sale.getId(),
                sale.getItem().getItemName(),
                DateUtil.calcDate(sale.getCreated()),
                sale.getImages().get(0).getUuid(),
                sale.getItem().getPrice()
        );
    }
}
