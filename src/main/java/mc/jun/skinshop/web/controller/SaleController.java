package mc.jun.skinshop.web.controller;

import lombok.RequiredArgsConstructor;
import mc.jun.skinshop.domain.dto.shop.dto.CreateSaleDto;
import mc.jun.skinshop.domain.dto.shop.dto.Token;
import mc.jun.skinshop.domain.dto.shop.response.SaleInformationResponse;
import mc.jun.skinshop.domain.entity.shop.Sale;
import mc.jun.skinshop.domain.service.shop.inf.SaleService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sale")
public class SaleController {

    private final SaleService saleService;

    @PostMapping
    public HttpEntity<?> createSale (@RequestBody CreateSaleDto createSaleDto, Token token) {
        Sale createSale = saleService.create(token.getMemberId(), createSaleDto);
        return ResponseEntity.ok(createSale.getId());
    }

    @GetMapping
    public List<SaleInformationResponse> getAll () {
        return createSaleInformationResponseList();
    }

    private List<SaleInformationResponse> createSaleInformationResponseList () {
        List<SaleInformationResponse> saleInformationResponses = new ArrayList<>();

        saleService.findAll().forEach(sale -> {
            saleInformationResponses.add(SaleInformationResponse.of(sale));
        });

        return saleInformationResponses;
    }

    @GetMapping("{saleId}")
    public SaleInformationResponse getSale (@PathVariable Long saleId) {
        Sale findSale = saleService.findById(saleId);
        return SaleInformationResponse.of(findSale);
    }
}
