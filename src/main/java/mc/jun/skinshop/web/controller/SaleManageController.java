package mc.jun.skinshop.web.controller;

import lombok.RequiredArgsConstructor;
import mc.jun.skinshop.domain.dto.shop.response.SaleManageInformationResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/sell/manage")
@RequiredArgsConstructor
public class SaleManageController {

    @GetMapping
    public List<SaleManageInformationResponse> getManageInfromationList () {
        return new ArrayList<>();
    }
}
