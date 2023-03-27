package mc.jun.skinshop.web.controller.shop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mc.jun.skinshop.domain.dto.shop.response.SaleManageInformationResponse;
import mc.jun.skinshop.domain.service.shop.inf.ShopService;
import mc.jun.skinshop.domain.util.JwtProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/shop/manage")
@RequiredArgsConstructor
public class SaleManageController {

    private final JwtProvider jwtProvider;
    private final ShopService shopService;

    @GetMapping
    public List<SaleManageInformationResponse> getManageInformationList (HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        Long memberId = Long.parseLong(jwtProvider.parseToken(token).getSubject().toString());
        return shopService.getManageInformationResponse(memberId);
    }
}
