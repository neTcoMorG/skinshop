package mc.jun.skinshop.domain.entity.member;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mc.jun.skinshop.domain.entity.shop.Sell;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ViewHistory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SELL_ID")
    private Sell sell;

    public void updateMember (Member member) {
        this.member = member;
    }

    public void updateSell (Sell sell) {
        this.sell = sell;
    }
}
