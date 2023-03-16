package mc.jun.skinshop.domain.service.file;

import java.io.File;

public interface FileService {
    void save (File file);
    File get (String fileName);
    void delete (String fileName);
}
