package mc.jun.skinshop.domain.service.shop.inf;

import mc.jun.skinshop.domain.dto.shop.dto.CreateSaleDto;
import mc.jun.skinshop.domain.entity.shop.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SaleService {

    Sale create (Long memberId, CreateSaleDto saleDto, List<MultipartFile> images);
    Sale findById (Long saleId);
    Page<Sale> findAll (Pageable pageable);
    void delete (Long memberId, Long saleId);

    void count (Long memberId, Long saleId);
}
