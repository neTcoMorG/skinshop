package mc.jun.skinshop.domain.service.oauth.naver;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import mc.jun.skinshop.domain.service.oauth.AuthService;
import mc.jun.skinshop.domain.service.oauth.naver.json.NaverAuth;
import mc.jun.skinshop.domain.service.oauth.naver.json.NaverProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class NaverAuthService implements AuthService<NaverAuth, NaverProfile> {

    @Value("${oauth.naver.api.url}") private String API_URL;
    @Value("${oauth.naver.api.token}") private String API_TOKEN_URI;
    @Value("${oauth.naver.api.profile}") private String API_PROFILE_URI;
    @Value("${oauth.naver.api_callback") private String REDIRECT_URI;
    @Value("${oauth.naver.key}") private String CLIENT_ID;
    @Value("${oauth.naver.secret}") private String SECRET;

    private WebClient webClient;

    @PostConstruct
    private void init () {
        webClient = WebClient.builder()
                .baseUrl(API_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=utf-8")
                .build();
    }

    @Override
    public NaverAuth getAuth (String code) {
        return webClient.post()
                .uri(API_TOKEN_URI)
                .body(BodyInserters.fromFormData(createTokenRequestForm(code)))
                .retrieve()
                .onStatus(status -> status.is4xxClientError(), error -> Mono.error(RuntimeException::new))
                .bodyToMono(NaverAuth.class)
                .block();
    }

    private MultiValueMap<String, String> createTokenRequestForm (String code) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", CLIENT_ID);
        params.add("redirect_uri", REDIRECT_URI);
        params.add("code", code);
        params.add("client_secret", SECRET);
        return params;
    }

    @Override
    public NaverProfile getProfile(String accessToken) {
        return null;
    }
}
