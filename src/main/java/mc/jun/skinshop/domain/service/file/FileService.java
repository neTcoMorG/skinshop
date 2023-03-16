package mc.jun.skinshop.domain.service.file;

import mc.jun.skinshop.domain.dto.ImageSaveInformation;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface FileService {
    List<ImageSaveInformation> save (List<MultipartFile> files);
    ImageSaveInformation save (MultipartFile file);
    File get (String fileName);
    void delete (String fileName);
}
