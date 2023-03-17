package mc.jun.skinshop.domain.dto.shop.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mc.jun.skinshop.domain.entity.shop.SaleStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SaleManageInformationResponse {

    private Long id;
    private String image_uuid;
    private SaleStatus status;
    private String item_name;
    private Integer price;
    private Integer like;


}
