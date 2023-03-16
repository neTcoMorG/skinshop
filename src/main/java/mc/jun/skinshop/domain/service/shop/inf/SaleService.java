package mc.jun.skinshop.domain.service.shop.inf;

import mc.jun.skinshop.domain.dto.shop.dto.CreateSaleDto;
import mc.jun.skinshop.domain.entity.shop.Sale;
import mc.jun.skinshop.domain.entity.shop.Shop;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface SaleService {

    Sale create (Long memberId, CreateSaleDto saleDto, List<MultipartFile> images);
    Sale findById (Long saleId);
    List<Sale> findAll ();


    void delete (Long memberId, Long saleId);
}
