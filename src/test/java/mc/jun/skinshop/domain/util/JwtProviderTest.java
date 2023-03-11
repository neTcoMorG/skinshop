package mc.jun.skinshop.domain.util;

import io.jsonwebtoken.MalformedJwtException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@EnableConfigurationProperties
@SpringBootTest
class JwtProviderTest {

    @Autowired
    JwtProvider jwtProvider;

    @Test
    @DisplayName("토큰 생성 테스트")
    void create_token_test () {
        String token = jwtProvider.create(1L);
        jwtProvider.parseToken("Bearer " + token);
        System.out.println(token);
    }
    
    @Test
    @DisplayName("유효하지 않은 토큰 테스트")
    void invalid_token_test () {
        String token = jwtProvider.create(1L);
        String finalToken = token + "dummyText";
        assertThrows(MalformedJwtException.class, ()->jwtProvider.parseToken(finalToken));
    }
}