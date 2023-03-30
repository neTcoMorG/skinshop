package mc.jun.skinshop.domain.service.member;

import mc.jun.skinshop.domain.dto.shop.dto.MemberProfileDto;
import mc.jun.skinshop.domain.entity.member.Member;

public interface MemberService {
    Member create (MemberProfileDto profileDto);
}
