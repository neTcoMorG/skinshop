package mc.jun.skinshop.domain.service.shop;

import lombok.RequiredArgsConstructor;
import mc.jun.skinshop.domain.dto.ImageSaveInformation;
import mc.jun.skinshop.domain.dto.shop.dto.CreateSaleDto;
import mc.jun.skinshop.domain.entity.member.Member;
import mc.jun.skinshop.domain.entity.shop.Image;
import mc.jun.skinshop.domain.entity.shop.Item;
import mc.jun.skinshop.domain.entity.shop.Sale;
import mc.jun.skinshop.domain.exception.MemberNotFoundException;
import mc.jun.skinshop.domain.exception.SaleNotFoundException;
import mc.jun.skinshop.domain.repository.MemberRepository;
import mc.jun.skinshop.domain.repository.SaleRepository;
import mc.jun.skinshop.domain.service.file.FileService;
import mc.jun.skinshop.domain.service.shop.inf.SaleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final MemberRepository memberRepository;
    private final FileService fileService;

    @Override
    @Transactional
    public Sale create (Long memberId, CreateSaleDto saleDto, List<MultipartFile> images) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new MemberNotFoundException());

        List<ImageSaveInformation> save = fileService.save(images);

        Sale createSale = saleRepository.save(
                new Sale(member.getShop(),
                        saleDto.getItem().toEntity(),
                        saleDto.getText()));

        save.forEach(s -> createSale.addImage(new Image(createSale, s.getUuid(), s.getFullPath())));

        return createSale;
    }

    @Override
    public Sale findById (Long saleId) {
        return validateSaleId(saleId);
    }

    @Override
    public List<Sale> findAll() {
        return saleRepository.findAll();
    }

    @Override
    public void delete (Long memberId, Long saleId) {
        Sale sale = validateSaleId(saleId);

        if (sale.getShop().isOwner(memberId)) {

        }
    }

    private Sale validateSaleId (Long saleId) {
        Sale sale = saleRepository.findById(saleId).orElseThrow(
                () -> new SaleNotFoundException());
        return sale;
    }
}
