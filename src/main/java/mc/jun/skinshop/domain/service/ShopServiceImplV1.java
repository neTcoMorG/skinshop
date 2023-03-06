package mc.jun.skinshop.domain.service;

import lombok.RequiredArgsConstructor;
import mc.jun.skinshop.domain.dto.shop.CreateSellDto;
import mc.jun.skinshop.domain.entity.member.Member;
import mc.jun.skinshop.domain.entity.shop.Category;
import mc.jun.skinshop.domain.entity.shop.Item;
import mc.jun.skinshop.domain.entity.shop.Sell;
import mc.jun.skinshop.domain.repository.CategoryRepository;
import mc.jun.skinshop.domain.repository.SellRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopServiceImplV1 implements ShopService {

    private final SellRepository sellRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<Sell> getAll() {
        return sellRepository.findAll();
    }

    @Override
    public Sell createSell(Member member, CreateSellDto createSellDto) {
        Sell createSell = toSellEntity(member, createSellDto);
        Sell save = sellRepository.save(createSell);
        return save;
    }

    private Sell toSellEntity (Member member, CreateSellDto createSellDto) {
        Item item = new Item(getCategory(createSellDto.getItem().getCate_id()),
                createSellDto.getItem().getPrice());

        return new Sell(member, item, createSellDto.getText(), createSellDto.getTitle());
    }

    private Category getCategory (Long cateId) {
        return categoryRepository.findById(cateId).orElseThrow();
    }

    @Override
    public List<Sell> getAllSellByMember(Member member) {
        return member.getSellList();
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
