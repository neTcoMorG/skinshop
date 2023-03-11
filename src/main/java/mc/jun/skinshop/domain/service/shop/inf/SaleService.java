package mc.jun.skinshop.domain.service.shop.inf;

import mc.jun.skinshop.domain.dto.shop.dto.CreateSaleDto;
import mc.jun.skinshop.domain.entity.shop.Sale;
import mc.jun.skinshop.domain.entity.shop.Shop;

import java.util.List;

public interface SaleService {

    Sale create (Shop shopId, CreateSaleDto saleDto);
    Sale findById (Long saleId);
    List<Sale> findAll ();


    void delete (Long memberId, Long saleId);
}
