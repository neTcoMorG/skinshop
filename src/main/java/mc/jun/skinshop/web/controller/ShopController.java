package mc.jun.skinshop.web.controller;

import lombok.RequiredArgsConstructor;
import mc.jun.skinshop.domain.dto.shop.CreateSellDto;
import mc.jun.skinshop.domain.entity.member.Member;
import mc.jun.skinshop.domain.entity.shop.Sell;
import mc.jun.skinshop.domain.repository.MemberRepository;
import mc.jun.skinshop.domain.service.ShopService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;
    private final MemberRepository memberRepository;

    @GetMapping
    public List<Sell> getAll() {
        return shopService.getAll();
    }

    @PostMapping
    public HttpEntity<?> createSell (@RequestBody CreateSellDto dto) {
        Member member = memberRepository.findByNickname("조영준").orElseThrow();
        shopService.createSell(member, dto);
        return ResponseEntity.ok().build();
    }
}
