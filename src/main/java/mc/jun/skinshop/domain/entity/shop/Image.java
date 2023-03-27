package mc.jun.skinshop.domain.entity.shop;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Image {

    public Image(Sale sale, String uuid, String path) {
        this.sale = sale;
        this.uuid = uuid;
        this.path = path;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SALE_ID")
    private Sale sale;

    private String uuid;
    private String path;
}
