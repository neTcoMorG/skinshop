package mc.jun.skinshop.domain.repository;

import mc.jun.skinshop.domain.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Long, Member> {
}
