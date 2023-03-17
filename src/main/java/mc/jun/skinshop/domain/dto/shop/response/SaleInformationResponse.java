package mc.jun.skinshop.domain.dto.shop.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mc.jun.skinshop.domain.entity.shop.Item;
import mc.jun.skinshop.domain.entity.shop.Sale;
import mc.jun.skinshop.domain.util.DateUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SaleInformationResponse {

    public SaleInformationResponse(String name, Long saleId, Item item, String text, LocalDateTime created,
                                   List<String> images_uuid) {
        this.name = name;
        this.saleId = saleId;
        this.item = item;
        this.text = text;
        this.created = DateUtil.calcDate(created);
        this.images_uuid = images_uuid;
    }

    private String name;
    private Long saleId;
    private Item item;
    private String text;
    private String created;
    private List<String> images_uuid;

    public static SaleInformationResponse of (Sale sale) {
        List<String> images_uuid = new ArrayList<>();
        sale.getImages().forEach(image -> images_uuid.add(image.getUuid()));

        return new SaleInformationResponse(
                sale.getShop().getMember().getName(),
                sale.getId(), sale.getItem(), sale.getText(),
                sale.getCreated(), images_uuid);
    }

}
