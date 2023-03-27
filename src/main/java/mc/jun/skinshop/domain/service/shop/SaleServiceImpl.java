package mc.jun.skinshop.domain.service.shop;

import lombok.RequiredArgsConstructor;
import mc.jun.skinshop.domain.dto.shop.ImageSaveInformation;
import mc.jun.skinshop.domain.dto.shop.dto.CreateSaleDto;
import mc.jun.skinshop.domain.entity.member.Member;
import mc.jun.skinshop.domain.entity.shop.Image;
import mc.jun.skinshop.domain.entity.shop.Sale;
import mc.jun.skinshop.domain.exception.MemberNotFoundException;
import mc.jun.skinshop.domain.exception.SaleNotFoundException;
import mc.jun.skinshop.domain.exception.SizeOverflowException;
import mc.jun.skinshop.domain.repository.MemberRepository;
import mc.jun.skinshop.domain.repository.SaleRepository;
import mc.jun.skinshop.domain.service.file.FileService;
import mc.jun.skinshop.domain.service.shop.inf.SaleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
        if (isSizeOverflow(images))
            throw new SizeOverflowException("SaleServiceImpl -> create()");

        Member member = getExistMember(memberId);
        List<ImageSaveInformation> save = fileService.save(images);

        Sale createSale = saleRepository.save(
                new Sale(member.getShop(),
                        saleDto.getItem().toEntity(),
                        saleDto.getText()));

//        save.forEach(s -> createSale.addImage(new Image(createSale, s.getUuid(), s.getFullPath())));
        return createSale;
    }

    @Override
    public Sale findById (Long saleId) {
        return validateSaleId(saleId);
    }

    @Override
    public Page<Sale> findAll(Pageable pageable) {
        return saleRepository.findAll(pageable);
    }

    @Override
    public void delete (Long memberId, Long saleId) {
        Sale sale = validateSaleId(saleId);
        if (sale.getShop().isOwner(memberId)) {

        }
    }

    private boolean isSizeOverflow(List<MultipartFile> images) {
        return images.size() > 10 ? true : false;
    }

    private Sale validateSaleId (Long saleId) {
        Sale sale = saleRepository.findById(saleId).orElseThrow(
                () -> new SaleNotFoundException());
        return sale;
    }

    private Member getExistMember (Long id) {
        return memberRepository.findById(id).orElseThrow(
                () -> new MemberNotFoundException());
    }
}
