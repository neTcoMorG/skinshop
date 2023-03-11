package mc.jun.skinshop.domain.service.shop;

import lombok.RequiredArgsConstructor;
import mc.jun.skinshop.domain.dto.shop.dto.CreateSaleDto;
import mc.jun.skinshop.domain.dto.shop.response.ShopInformationResponse;
import mc.jun.skinshop.domain.entity.shop.Sale;
import mc.jun.skinshop.domain.entity.shop.Shop;
import mc.jun.skinshop.domain.exception.ShopNotFoundException;
import mc.jun.skinshop.domain.repository.ShopRepository;
import mc.jun.skinshop.domain.service.shop.inf.SaleService;
import mc.jun.skinshop.domain.service.shop.inf.ShopService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    private final SaleService saleService;

    @Override
    public ShopInformationResponse findById (Long id) {
        Shop findShop = shopRepository.findById(id).orElseThrow(
                ShopNotFoundException::new);

        return ShopInformationResponse.of(findShop);
    }

    @Override
    public Sale createSale(Long shopId, CreateSaleDto dto) {
        Shop findShop = shopRepository.findById(shopId).orElseThrow(
                () -> new ShopNotFoundException());
        return saleService.create(findShop, dto);
    }
}
