package mc.jun.skinshop.web.controller;

import mc.jun.skinshop.domain.service.shop.inf.SaleService;
import mc.jun.skinshop.web.controller.shop.SaleController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@ExtendWith({MockitoExtension.class})
@WebMvcTest({SaleController.class})
class SaleControllerTest {

    @Mock
    SaleService saleService;

    @Test
    @DisplayName("form-data 잘 받는지 테스트")
    void createSale() {

    }
}