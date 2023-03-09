package mc.jun.skinshop.domain.service;

import mc.jun.skinshop.domain.dto.shop.response.SellResponseDto;
import mc.jun.skinshop.domain.entity.member.Member;
import mc.jun.skinshop.domain.entity.shop.Item;
import mc.jun.skinshop.domain.entity.shop.Sell;
import mc.jun.skinshop.domain.repository.CategoryRepository;
import mc.jun.skinshop.domain.repository.SellRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith({MockitoExtension.class})
class ShopServiceImplV1Test {

    @Mock
    SellRepository sellRepository;

    @Mock
    CategoryRepository categoryRepository;


    @Test
    @DisplayName("Member -> SellResponseDto 매핑 테스트")
    void getAll() {
        //given
        ShopServiceImplV1 shopServiceImplV1 = new ShopServiceImplV1(sellRepository, categoryRepository);

        Item item = new Item(null, 1000);
        Member member = new Member("Test");

        List<Sell> sellList = new ArrayList<>();
        sellList.add(new Sell(member, item, "", ""));

        given(sellRepository.findAll()).willReturn(sellList);

        //when
        List<SellResponseDto> result = shopServiceImplV1.getAll();

        //then
        assertEquals(sellList.size(), result.size());
        assertEquals(sellList.get(0).getMember().getNickname(), result.get(0).getNickname());
    }
}