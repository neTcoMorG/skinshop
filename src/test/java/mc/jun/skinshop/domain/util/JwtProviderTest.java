package mc.jun.skinshop.domain.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.MalformedJwtException;
import org.junit.jupiter.api.Disabled;
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
    @DisplayName("토큰 생성 후 파싱 테스트")
    void create_token_test () {
        String token = jwtProvider.create(1L);
        Claims claims = jwtProvider.parseToken("Bearer " + token);
        assertEquals(1L, Long.parseLong(claims.getSubject().toString()));
    }
    
    @Test
    @DisplayName("유효하지 않은 토큰 예외 발생 테스트")
    void invalid_token_test () {
        String token = jwtProvider.create(1L);
        String finalToken = token + "dummyText";
        assertThrows(MalformedJwtException.class, ()-> jwtProvider.parseToken(finalToken));
    }
}