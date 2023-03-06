package mc.jun.skinshop.domain.service;

import lombok.RequiredArgsConstructor;
import mc.jun.skinshop.domain.entity.member.Member;
import mc.jun.skinshop.domain.entity.shop.Sell;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopServiceImplV1 implements ShopService {

    @Override
    public Sell createSell(Member member, Sell sell) {
        return null;
    }

    @Override
    public List<Sell> getAllSellByMember(Member member) {
        return null;
    }

    @Override
    public Sell getSellById(Long id) {
        return null;
    }

    @Override
    public Sell modifySell(Sell old, Sell n) {
        return null;
    }

    @Override
    public void removeSell(Member member, Sell sell) {

    }
}
