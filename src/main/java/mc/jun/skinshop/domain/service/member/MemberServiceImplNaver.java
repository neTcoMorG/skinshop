package mc.jun.skinshop.domain.service.member;

import lombok.RequiredArgsConstructor;
import mc.jun.skinshop.domain.dto.shop.dto.MemberProfileDto;
import mc.jun.skinshop.domain.entity.member.Member;
import mc.jun.skinshop.domain.entity.shop.Shop;
import mc.jun.skinshop.domain.exception.MemberNotFoundException;
import mc.jun.skinshop.domain.repository.MemberRepository;
import mc.jun.skinshop.domain.repository.ShopRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImplNaver implements MemberService {

    private final MemberRepository memberRepository;
    private final ShopRepository shopRepository;

    @Override
    @Transactional
    public Member create (MemberProfileDto profile) {
        if(!exits(profile)) {
            Member createMember = memberRepository.save(new Member(profile.getName(), profile.getImage_url(),
                    profile.getEmail()));
            shopRepository.save(new Shop(createMember, createMember.getName() + "님의 공작소입니다"));
            return createMember;
        }
        return memberRepository.findByEmail(profile.getEmail()).orElseThrow(()->
                new MemberNotFoundException());
    }

    private boolean exits (MemberProfileDto profileDto) {
        String email = profileDto.getEmail();
        return memberRepository.existsByEmail(email);
    }
}
