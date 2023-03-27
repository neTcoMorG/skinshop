package mc.jun.skinshop.domain.service.shop.inf;

import mc.jun.skinshop.domain.dto.shop.dto.CreateSaleDto;
import mc.jun.skinshop.domain.dto.shop.response.SaleManageInformationResponse;
import mc.jun.skinshop.domain.dto.shop.response.ShopInformationResponse;
import mc.jun.skinshop.domain.entity.shop.Sale;
import mc.jun.skinshop.domain.entity.shop.Shop;

import java.util.List;


public interface ShopService {

    Shop findById (Long id);
    Shop findByMemberId (Long memberId);
    Sale createSale (Long shopId, CreateSaleDto dto);

    List<SaleManageInformationResponse> getManageInformationResponse(Long memberId);
}
