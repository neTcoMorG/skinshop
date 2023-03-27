package mc.jun.skinshop.domain.entity.shop;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

    @CreatedDate
    private LocalDateTime created;

    private String text;

    public void addImage (Image image) {
        this.images.add(image);
    }
}
