package mc.jun.skinshop.web.resolver;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import mc.jun.skinshop.domain.dto.shop.dto.Token;
import mc.jun.skinshop.domain.exception.NullTokenException;
import mc.jun.skinshop.domain.util.JwtProvider;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class TokenArgumentResolver implements HandlerMethodArgumentResolver {

    private final JwtProvider jwtProvider;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(Token.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        if (request.getHeader(HttpHeaders.AUTHORIZATION) == null)
            throw new NullTokenException();

        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        Claims claims = jwtProvider.parseToken(authorization);
        return new Token(authorization, Long.parseLong(claims.getSubject().toString()));
    }
}
