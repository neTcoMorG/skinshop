package mc.jun.skinshop.web.controller.shop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mc.jun.skinshop.domain.dto.shop.response.ShopInformationResponse;
import mc.jun.skinshop.domain.entity.member.Member;
import mc.jun.skinshop.domain.exception.MemberNotFoundException;
import mc.jun.skinshop.domain.repository.MemberRepository;
import mc.jun.skinshop.domain.service.shop.inf.ShopService;
import mc.jun.skinshop.domain.util.JwtProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/shop")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;
    private final JwtProvider provider;
    private final MemberRepository memberRepository;

    @GetMapping
    public ShopInformationResponse getShop (HttpServletRequest request) throws Exception {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        Member findMember = memberRepository.findById(getMemberIdByToken(token)).orElseThrow(
                MemberNotFoundException::new);

        return getShopById(findMember.getShop().getId());
    }

    @GetMapping("{shopId}")
    public ShopInformationResponse getShopById (@PathVariable Long shopId) {
        return ShopInformationResponse.of(shopService.findById(shopId));
    }

    // TODO 상점 수정
    public void modifyShop () {

    }

    private Long getMemberIdByToken (String token) throws Exception {
        return provider.parseToken(token).getId();
    }
}
