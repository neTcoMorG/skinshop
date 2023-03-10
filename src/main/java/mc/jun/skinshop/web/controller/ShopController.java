package mc.jun.skinshop.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mc.jun.skinshop.domain.entity.shop.Sale;
import mc.jun.skinshop.domain.service.ShopService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/shop")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

//    @GetMapping
//    public List<SellResponse> getAll() {
//        return shopService.getAll();
//    }

//    @PostMapping
//    public HttpEntity<?> createSell (@RequestBody CreateSellDto dto) {
//        Member member = memberRepository.findByNickname("조영준").orElseThrow();
//        shopService.createSell(member, dto);
//        return ResponseEntity.ok().build();
//    }

    @GetMapping("{memberName}")
    public List<Sale> getCellByMemberName (@PathVariable(name = "memberName") String name) {
        return new ArrayList<>();
    }
}
