package mc.jun.skinshop.domain.service.member;

import mc.jun.skinshop.domain.dto.shop.dto.MemberProfileDto;
import mc.jun.skinshop.domain.entity.member.Member;
import mc.jun.skinshop.domain.service.oauth.naver.json.NaverProfile;

public interface MemberService {
    Member create (MemberProfileDto profileDto);
}
