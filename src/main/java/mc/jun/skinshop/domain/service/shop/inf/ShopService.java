package mc.jun.skinshop.domain.service.shop.inf;

import mc.jun.skinshop.domain.dto.shop.dto.CreateSaleDto;
import mc.jun.skinshop.domain.dto.shop.response.ShopInformationResponse;
import mc.jun.skinshop.domain.entity.shop.Sale;
import mc.jun.skinshop.domain.entity.shop.Shop;


public interface ShopService {

    ShopInformationResponse findById (Long id);
    Sale createSale (Long shopId, CreateSaleDto dto);
}
