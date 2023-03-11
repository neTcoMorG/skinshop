package mc.jun.skinshop.web.controller;

import lombok.RequiredArgsConstructor;
import mc.jun.skinshop.domain.dto.shop.dto.CreateSaleDto;
import mc.jun.skinshop.domain.dto.shop.dto.Token;
import mc.jun.skinshop.domain.entity.shop.Sale;
import mc.jun.skinshop.domain.exception.SaleNotFoundException;
import mc.jun.skinshop.domain.repository.SaleRepository;
import mc.jun.skinshop.domain.service.shop.inf.SaleService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sale")
public class SaleController {

    private final SaleService saleService;
    private final SaleRepository saleRepository;

    @PostMapping
    public HttpEntity<?> createSale (@RequestBody CreateSaleDto createSaleDto, Token token) {
        saleService.create(token.getMemberId(), createSaleDto);
        return ResponseEntity.ok().build();
    }


    @GetMapping("{saleId}")
    public Sale getSale (@PathVariable Long saleId) {
        return saleRepository.findById(saleId).orElseThrow(() ->
                new SaleNotFoundException());
    }
}
