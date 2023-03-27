package mc.jun.skinshop.web.controller.shop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import mc.jun.skinshop.domain.dto.shop.dto.CreateSaleDto;
import mc.jun.skinshop.domain.dto.shop.response.SaleInformationResponse;
import mc.jun.skinshop.domain.dto.shop.response.SalePreviewInformationResponse;
import mc.jun.skinshop.domain.entity.shop.Sale;
import mc.jun.skinshop.domain.service.shop.inf.SaleService;
import mc.jun.skinshop.domain.util.JwtProvider;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sale")
public class SaleController {

    private final SaleService saleService;
    private final JwtProvider jwtProvider;

    @PostMapping
    public HttpEntity<?> createSale (@RequestPart("sellObject") CreateSaleDto createSaleDto,
                                     @RequestPart("images") List<MultipartFile> images,
                                     HttpServletRequest request) {

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        Long memberId = Long.parseLong(jwtProvider.parseToken(token).getSubject().toString());
        Sale createSale = saleService.create(memberId, createSaleDto, images);
        return ResponseEntity.ok(createSale.getId());
    }

    @GetMapping
    public List<SalePreviewInformationResponse> getPreview (Pageable pageable) {

        return getSalePreviewInformationResponseList(pageable);
    }

    @GetMapping("{saleId}")
    public SaleInformationResponse getSale (@PathVariable Long saleId) {
        Sale findSale = saleService.findById(saleId);
        return SaleInformationResponse.of(findSale);
    }

    private List<SalePreviewInformationResponse> getSalePreviewInformationResponseList (Pageable pageable) {
        List<SalePreviewInformationResponse> saleInformationResponses = new ArrayList<>();

        saleService.findAll(pageable).forEach(sale ->
            saleInformationResponses.add(SalePreviewInformationResponse.of(sale)));

        return saleInformationResponses;
    }
}