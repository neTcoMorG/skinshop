package mc.jun.skinshop.domain.service;

import mc.jun.skinshop.domain.dto.shop.response.ShopInformationResponse;
import mc.jun.skinshop.domain.entity.member.Member;
import mc.jun.skinshop.domain.entity.shop.Shop;
import mc.jun.skinshop.domain.repository.ShopRepository;
import mc.jun.skinshop.domain.service.shop.inf.ShopService;
import mc.jun.skinshop.domain.service.shop.ShopServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith({MockitoExtension.class})
class ShopServiceImplTest {

    @Mock
    ShopRepository shopRepository;
    ShopService service;

    @BeforeEach
    @Disabled
    void setup () {
        Member member = new Member("사용자", "a.com", "sdasd");
        Shop mockShop = new Shop(member, "사용자의 상점입니다");
//        service = new ShopServiceImpl(shopRepository);

        given(this.shopRepository.findById(1L))
                .willReturn(Optional.of(mockShop));
    }

    @Test
    @DisplayName("상점 Response 응답 생성 테스트")
    void 상점_응답_테스트() {
        Shop findShop = shopRepository.findById(1L).orElseThrow();
        ShopInformationResponse info = service.findById(1L);

        assertNotNull(info);
        assertEquals(info.getShop_id(), findShop.getId());
        assertEquals(info.getMember().getName(), findShop.getMember().getName());
    }

    @Test
    @DisplayName("없는 상점 조회시 예외발생 테스트")
    void 상점_응답_예외_테스트 () {
        assertThrows(RuntimeException.class,
                () -> service.findById(2L));
    }
}