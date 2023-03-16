package mc.jun.skinshop.domain.service.file;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileSystemServiceTest {

    FileService fileService = new FileSystemService();
    String filePath = "C:\\skinshop\\sale\\img\\";


    @Test
    @DisplayName("경로에 자식 폴더가 없는 경우 자동으로 생성해주는지 테스트")
    void create_child_dir () {
        String dir = "C:\\test\\child\\";

        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }
    }
    
    @Test
    @DisplayName("파일 저장이 성공적으로 되는지 테스트")
    void save_file_test () throws IOException {
        File file = new File("./test");
        if (!file.exists()) file.createNewFile();
        fileService.save(file);
    }
}