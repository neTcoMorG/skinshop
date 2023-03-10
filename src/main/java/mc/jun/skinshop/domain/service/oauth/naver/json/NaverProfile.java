package mc.jun.skinshop.domain.service.oauth.naver.json;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NaverProfile {
    private Response response;
    private String resultCode;
    private String message;
}
