package mc.jun.skinshop.domain.service.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface FileService {
    void save (List<MultipartFile> files);
    void save (MultipartFile file);
    File get (String fileName);
    void delete (String fileName);
}
