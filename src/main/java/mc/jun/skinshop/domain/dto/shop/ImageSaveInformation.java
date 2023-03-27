package mc.jun.skinshop.domain.dto.shop;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageSaveInformation {
    private String uuid;
    private String fullPath;
}
