package mc.jun.skinshop.domain.entity.shop;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mc.jun.skinshop.domain.entity.member.Member;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Sell {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Sell(Member member, Item item, String text, String title) {
        this.member = member;
        this.item = item;
        this.text = text;
        this.title = title;
        status = false;
        likeCount = 0;
        viewCount = 0;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @NotNull
    @JoinColumn(name = "ITEM_ID")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Item item;

    @Nullable
    private String text;
    @NotNull
    private String title;
    @NotNull @CreatedDate
    private LocalDateTime created;
    @NotNull
    private Boolean status;
    @NotNull
    private int likeCount;
    @NotNull
    private int viewCount;
}
