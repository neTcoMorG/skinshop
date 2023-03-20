package mc.jun.skinshop.domain.service.file;

import jakarta.annotation.PostConstruct;
import lombok.*;
import mc.jun.skinshop.domain.dto.ImageSaveInformation;
import mc.jun.skinshop.domain.entity.shop.Image;
import mc.jun.skinshop.domain.exception.FileSaveException;
import mc.jun.skinshop.domain.exception.ImageNotFoundException;
import mc.jun.skinshop.domain.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileSystemService implements FileService {

    private final ImageRepository imageRepository;

    @Value("${file.path}") private String savePath;

    @PostConstruct
    private void init () {
        System.out.println("[value]" + this.savePath);
        File dir = new File(this.savePath);
        if (!dir.exists())
            dir.mkdirs();
    }

    @Override
    public List<ImageSaveInformation> save (List<MultipartFile> files) {
        List<ImageSaveInformation> result = new ArrayList<>();

        files.forEach(file -> {
            ImageSaveInformation imageSaveInformation = createImageInformation(file.getOriginalFilename());

            try {
                file.transferTo(new File(imageSaveInformation.getFullPath()));
                result.add(imageSaveInformation);
            } catch (IOException e) {
                throw new FileSaveException();
            }
        });

        return result;
    }

    @Override
    public Image getByUuid (String gid) {
        Image findImage = imageRepository.findByUuid(gid).orElseThrow(
                () -> new ImageNotFoundException("CAUSE BY > FileSystemService.getByUuid()"));
        return findImage;
    }

    @Override
    public void delete(String fileName) {

    }

    private ImageSaveInformation createImageInformation (String fileName) {
        String uuid = UUID.randomUUID().toString();
        return new ImageSaveInformation(uuid,
                savePath + uuid + "." + getExt(fileName));
    }

    private String getExt (String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

}
