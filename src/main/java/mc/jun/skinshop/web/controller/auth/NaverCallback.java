package mc.jun.skinshop.web.controller.auth;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mc.jun.skinshop.domain.dto.shop.dto.MemberProfileDto;
import mc.jun.skinshop.domain.entity.member.Member;
import mc.jun.skinshop.domain.service.member.MemberService;
import mc.jun.skinshop.domain.service.oauth.naver.NaverAuthService;
import mc.jun.skinshop.domain.service.oauth.naver.json.NaverProfile;
import mc.jun.skinshop.domain.util.JwtProvider;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/auth/naver")
@RequiredArgsConstructor
public class NaverCallback {

    @Value("${oauth.naver.page}") private String pageUrl;

    private final NaverAuthService authService;
    private final JwtProvider jwtProvider;
    private final MemberService memberService;

    @GetMapping
    public String callback2 (@RequestParam String code) {
        String access_token = authService.getAuth(code).getAccess_token();
        NaverProfile profile = authService.getProfile(access_token);
        Member createMember = memberService.create(mappedTo(profile));
        return jwtProvider.create(createMember.getId());
    }

    private MemberProfileDto mappedTo (NaverProfile naverProfile) {
        return new MemberProfileDto(naverProfile.getResponse().getNickname(),
                naverProfile.getResponse().getEmail(),
                naverProfile.getResponse().getProfile_image());
    }

    @GetMapping("/login")
    public void loginPage (HttpServletResponse response) throws IOException {
        response.sendRedirect(pageUrl);
    }
}
