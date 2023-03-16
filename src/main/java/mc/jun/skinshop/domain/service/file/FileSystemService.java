package mc.jun.skinshop.domain.service.file;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import mc.jun.skinshop.domain.exception.FileSaveException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileSystemService implements FileService {

    @Value("${file.path}") private String savePath;

    @PostConstruct
    private void init () {
        System.out.println("[value]" + this.savePath);
        File dir = new File(this.savePath);
        if (!dir.exists())
            dir.mkdirs();
    }

    @Override
    public void save(MultipartFile file) throws FileSaveException {
        try {
            file.transferTo(new File(getFullPath(file.getOriginalFilename())));
        } catch (IOException e) {
            throw new FileSaveException();
        }
    }

    @Override
    public void save (List<MultipartFile> files) {
        files.forEach(file -> {
            try {
                file.transferTo(new File(getFullPath(file.getOriginalFilename())));
            } catch (IOException e) {
                throw new FileSaveException();
            }
        });
    }

    @Override
    public File get(String fileName) {
        return null;
    }

    @Override
    public void delete(String fileName) {

    }

    private String getFullPath (String fileName) {
        return savePath + UUID.randomUUID() + "." + getExt(fileName);
    }

    private String getExt (String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
