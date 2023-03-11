package mc.jun.skinshop.domain.service.oauth.naver.json;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Response {
    private String nickname;
    private String email;
    private String profile_image;
}
