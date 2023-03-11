package mc.jun.skinshop.domain.entity.member;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mc.jun.skinshop.domain.entity.shop.Shop;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    public Member (String name, String profileImgUrl, String email) {
        this.name = name;
        this.profileImgUrl = profileImgUrl;
        this.email = email;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String profileImgUrl;

    @CreatedDate
    private LocalDateTime created;

    @OneToOne(mappedBy = "member")
    private Shop shop;

}
