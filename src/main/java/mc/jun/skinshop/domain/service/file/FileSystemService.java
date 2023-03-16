package mc.jun.skinshop.domain.service.file;

import jakarta.annotation.PostConstruct;
import mc.jun.skinshop.domain.exception.FileDuplicateException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileSystemService implements FileService {

    @Value("${file.path}") private String savePath;

    @PostConstruct
    private void init () {
        File dir = new File(this.savePath);
        if (!dir.exists())
            dir.mkdirs();
    }

    @Override
    public void save (File file) throws FileDuplicateException {
        String ext = getExt(file.getName());
        String fileUUID = UUID.randomUUID().toString();
        File dst = new File(savePath + fileUUID + ext);

        file.renameTo(dst);
        file.delete();
    }

    @Override
    public File get(String fileName) {
        return null;
    }

    @Override
    public void delete(String fileName) {

    }

    private String createPath (String fileName) {
        return "";
    }

    private String getExt (String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
