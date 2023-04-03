package mc.jun.skinshop.domain.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import mc.jun.skinshop.domain.dto.jwt.JwtUserObject;
import mc.jun.skinshop.domain.exception.NullTokenException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    @Value("${jwt.secret}") private String secret;
    private final ObjectMapper objectMapper;

    public String create (JwtUserObject object) throws Exception {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + Duration.ofDays(1).toMillis()); // 만료기간 1일
        String json = objectMapper.writeValueAsString(object);

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer("skinshop")
                .setIssuedAt(now)
                .setExpiration(expiration)
                .setSubject(json)
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secret.getBytes()))
                .compact();
    }

    public JwtUserObject parseToken (String token) throws Exception {
        token = BearerRemove(token);
        Claims subject = Jwts.parser()
                .setSigningKey(Base64.getEncoder().encodeToString(secret.getBytes()))
                .parseClaimsJws(token)
                .getBody();

        return objectMapper.readValue(subject.getSubject(), JwtUserObject.class);
    }

    private String BearerRemove (String token) {
        if (!token.isBlank() && token.contains("Bearer")) {
            return token.substring("Bearer ".length());
        }
        throw new NullTokenException("cause by BearerRemove");
    }
}
