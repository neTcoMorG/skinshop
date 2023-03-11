package mc.jun.skinshop.web.controller.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mc.jun.skinshop.domain.dto.shop.dto.MemberProfileDto;
import mc.jun.skinshop.domain.entity.member.Member;
import mc.jun.skinshop.domain.repository.MemberRepository;
import mc.jun.skinshop.domain.service.member.MemberService;
import mc.jun.skinshop.domain.service.oauth.naver.NaverAuthService;
import mc.jun.skinshop.domain.service.oauth.naver.json.NaverAuth;
import mc.jun.skinshop.domain.service.oauth.naver.json.NaverProfile;
import mc.jun.skinshop.domain.util.JwtProvider;
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
    private final JwtProvider jwtProvider;
    private final MemberService memberService;

    @GetMapping
    public String callback (@RequestParam String access_token) {
        NaverProfile profile = authService.getProfile(access_token);
        Member createMember = memberService.create(mappedTo(profile));
        return jwtProvider.create(createMember.getId());
    }

    private MemberProfileDto mappedTo (NaverProfile naverProfile) {
        return new MemberProfileDto(naverProfile.getResponse().getNickname(),
                naverProfile.getResponse().getEmail(),
                naverProfile.getResponse().getProfile_image());
    }
}
