package mc.jun.skinshop.domain.service.member;

import lombok.RequiredArgsConstructor;
import mc.jun.skinshop.domain.dto.shop.dto.MemberProfileDto;
import mc.jun.skinshop.domain.entity.member.Member;
import mc.jun.skinshop.domain.exception.MemberNotFoundException;
import mc.jun.skinshop.domain.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImplNaver implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Member create(MemberProfileDto profile) {
        if(!isExits(profile)) {
            return memberRepository.save(new Member(profile.getName(), profile.getImage_url(),
                    profile.getEmail()));
        }
        return memberRepository.findByEmail(profile.getEmail()).orElseThrow(()->
                new MemberNotFoundException());
    }

    private boolean isExits (MemberProfileDto profileDto) {
        String email = profileDto.getEmail();
        return memberRepository.existsByEmail(email);
    }
}
