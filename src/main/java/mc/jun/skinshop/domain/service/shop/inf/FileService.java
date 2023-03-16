package mc.jun.skinshop.domain.service.shop.inf;

import java.io.File;
import java.util.Optional;

public interface FileService {


    String save (File file);
    Optional<File> read (String fileUUID);
    void delete (String fileUUID);
}
