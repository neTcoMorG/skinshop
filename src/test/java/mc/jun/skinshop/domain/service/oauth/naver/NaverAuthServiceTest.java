package mc.jun.skinshop.domain.service.oauth.naver;

import mc.jun.skinshop.domain.service.oauth.naver.json.NaverAuth;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NaverAuthServiceTest {


    @Autowired
    NaverAuthService naverAuthService;

    @Test
    @DisplayName("엑세스 토큰 발급 테스트")
    void getAccessTokenTest() {
        NaverAuth auth = naverAuthService.getAuth("uT2Kogm5YPNgPh06CR");
        System.out.println(auth.toString());
    }
}