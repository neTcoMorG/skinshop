package mc.jun.skinshop.web.controller;

import lombok.RequiredArgsConstructor;
import mc.jun.skinshop.domain.entity.shop.Image;
import mc.jun.skinshop.domain.service.file.FileService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageSendController {

    private final FileService fileService;

    @GetMapping("{gid}")
    public ResponseEntity<Resource> getImage (@PathVariable String gid) throws IOException {
        Image entity = fileService.getByUuid(gid);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(entity.getPath()));

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename" + resource.getFilename())
                .body(resource);
    }
}
