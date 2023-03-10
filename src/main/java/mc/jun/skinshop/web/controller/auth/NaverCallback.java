package mc.jun.skinshop.web.controller.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mc.jun.skinshop.domain.service.oauth.naver.NaverAuthService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth/naver")
@RequiredArgsConstructor
public class NaverCallback {

    private final NaverAuthService authService;

    @GetMapping
    public HttpEntity<?> callback (@RequestParam String code) {
        log.info(authService.getAuth(code).getAccess_token());
        return ResponseEntity.ok(code);
    }
}
