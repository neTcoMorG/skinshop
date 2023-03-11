package mc.jun.skinshop.domain.entity.shop;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Sale {

    public Sale(Shop shop, Item item, String text) {
        this.shop = shop;
        this.item = item;
        this.text = text;
        this.status = SaleStatus.DOING;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private SaleStatus status;

    @JoinColumn(name = "SHOP_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @CreatedDate
    private LocalDateTime created;

    private String text;
}
