package mc.jun.skinshop.domain.entity.shop;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mc.jun.skinshop.domain.entity.member.Member;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Shop {

    public Shop(Member member, String hello) {
        this.member = member;
        this.hello = hello;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "MEMBER")
    private Member member;

    @CreatedDate
    private LocalDateTime created;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Sale> sales = new ArrayList<>();

    private String hello;

    public boolean isOwner (Long memberId) {
        return this.member.getId() == memberId;
    }
}
