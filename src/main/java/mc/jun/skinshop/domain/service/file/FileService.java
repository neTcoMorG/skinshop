package mc.jun.skinshop.domain.service.file;

import mc.jun.skinshop.domain.dto.shop.ImageSaveInformation;
import mc.jun.skinshop.domain.entity.shop.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    List<ImageSaveInformation> save (List<MultipartFile> files);
    Image getByUuid (String gid);
    void delete (String fileName);
}
