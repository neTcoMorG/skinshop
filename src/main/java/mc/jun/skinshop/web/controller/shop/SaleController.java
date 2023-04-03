package mc.jun.skinshop.web.controller.shop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import mc.jun.skinshop.domain.dto.shop.dto.CreateSaleDto;
import mc.jun.skinshop.domain.dto.shop.response.SaleInformationResponse;
import mc.jun.skinshop.domain.dto.shop.response.SalePreviewInformationResponse;
import mc.jun.skinshop.domain.entity.shop.Sale;
import mc.jun.skinshop.domain.service.shop.inf.SaleService;
import mc.jun.skinshop.domain.util.JwtProvider;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
                                     HttpServletRequest request) throws Exception {

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        Long memberId = getMemberIdByToken(token);

        Sale createSale = saleService.create(memberId, createSaleDto, images);
        return ResponseEntity.ok(createSale.getId());
    }

    @GetMapping("{saleId}")
    public SaleInformationResponse getSale (@PathVariable Long saleId) {
        Sale findSale = saleService.findById(saleId);
        return SaleInformationResponse.of(findSale);
    }

    @GetMapping
    public List<SalePreviewInformationResponse> getPreview (@RequestParam Integer page) {
        return getSalePreviewInformationResponseList(page);
    }

    private Long getMemberIdByToken (String token) throws Exception {
        return jwtProvider.parseToken(token).getId();
    }

    private List<SalePreviewInformationResponse> getSalePreviewInformationResponseList (int page) {
        List<SalePreviewInformationResponse> saleInformationResponses = new ArrayList<>();

        saleService.findAll(PageRequest.of(page, 5, Sort.by("created").descending())).forEach(sale ->
            saleInformationResponses.add(SalePreviewInformationResponse.of(sale)));

        return saleInformationResponses;
    }
}
