package mc.jun.skinshop.domain.service.shop;

import lombok.RequiredArgsConstructor;
import mc.jun.skinshop.domain.dto.shop.dto.CreateSaleDto;
import mc.jun.skinshop.domain.entity.member.Member;
import mc.jun.skinshop.domain.entity.shop.Item;
import mc.jun.skinshop.domain.entity.shop.Sale;
import mc.jun.skinshop.domain.entity.shop.Shop;
import mc.jun.skinshop.domain.exception.MemberNotFoundException;
import mc.jun.skinshop.domain.exception.SaleNotFoundException;
import mc.jun.skinshop.domain.repository.MemberRepository;
import mc.jun.skinshop.domain.repository.SaleRepository;
import mc.jun.skinshop.domain.service.shop.inf.SaleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final MemberRepository memberRepository;

    @Override
    public Sale create (Long memberId, CreateSaleDto saleDto) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new MemberNotFoundException());

        Sale save = saleRepository.save(new Sale(member.getShop(),
                new Item(saleDto.getItem().getName(),
                        saleDto.getItem().getPrice()),
                saleDto.getText()));
        return save;
    }

    @Override
    public Sale findById (Long saleId) {
        Sale findSale = saleRepository.findById(saleId).orElseThrow(
                () -> new SaleNotFoundException());
        return findSale;
    }

    @Override
    public List<Sale> findAll() {
        return null;
    }

    @Override
    public void delete (Long memberId, Long saleId) {
        
    }
}
