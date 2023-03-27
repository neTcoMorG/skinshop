package mc.jun.skinshop.domain.service.shop;

import lombok.RequiredArgsConstructor;
import mc.jun.skinshop.domain.dto.shop.dto.CreateSaleDto;
import mc.jun.skinshop.domain.dto.shop.response.SaleManageInformationResponse;
import mc.jun.skinshop.domain.dto.shop.response.ShopInformationResponse;
import mc.jun.skinshop.domain.entity.member.Member;
import mc.jun.skinshop.domain.entity.shop.Sale;
import mc.jun.skinshop.domain.entity.shop.Shop;
import mc.jun.skinshop.domain.exception.MemberNotFoundException;
import mc.jun.skinshop.domain.exception.ShopNotFoundException;
import mc.jun.skinshop.domain.repository.MemberRepository;
import mc.jun.skinshop.domain.repository.ShopRepository;
import mc.jun.skinshop.domain.service.shop.inf.SaleService;
import mc.jun.skinshop.domain.service.shop.inf.ShopService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    private final MemberRepository memberRepository;

    @Override
    public List<SaleManageInformationResponse> getManageInformationResponse (Long memberId) {
        Shop findShop = findByMemberId(memberId);
        List<SaleManageInformationResponse> result = new ArrayList<>();

        findShop.getSales().forEach(sale -> {
            result.add(SaleManageInformationResponse.of(sale));
        });

        return result;
    }

    @Override
    public Shop findByMemberId(Long memberId) {
        Member findMember = memberRepository.findById(memberId).orElseThrow(
                () -> new MemberNotFoundException());

        return findMember.getShop();
    }

    @Override
    public Shop findById (Long id) {
        Shop findShop = shopRepository.findById(id).orElseThrow(
                ShopNotFoundException::new);

        return findShop;
    }

    /**
     * @deprecated saleService 사용바람
     * @param shopId
     * @param dto
     * @return
     */
    @Override
    public Sale createSale(Long shopId, CreateSaleDto dto) {
        return null;
    }
}
