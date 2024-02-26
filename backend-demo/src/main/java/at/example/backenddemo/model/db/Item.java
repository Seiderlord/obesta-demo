package at.example.backenddemo.model.db;

import at.example.backenddemo.model.enumeration.TaxCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
/**
 * The "Item" class represents an abstract item.
 *
 * @author Bert Ödemis
 */
@Data
@Entity(name = "item")
@Table(name = "item", schema = "demo")
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_mediafile",
            joinColumns =
                    { @JoinColumn(name = "product_id", referencedColumnName = "id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "mediafile_id", referencedColumnName = "id") })
    @OrderColumn
    private List<MediaResource> mediaResources;

    @Column(nullable = false,length = 128)
    @NotNull
    @Size(max = 128)
    private String name;

    @Column(nullable = false,length = 512)
    @NotNull
    @Size(max = 512)
    private String brief;

    @Column(nullable = false,length = 8192)
    @NotNull
    @Size(max = 8192)
    private String description;

    @Column(nullable = false)
    @Digits(integer = 10, fraction = 2)
    @Positive
    @NotNull
    private BigDecimal price;

    @Digits(integer = 10, fraction = 2)
    @PositiveOrZero
    private BigDecimal discountPrice;

    @Column(nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private TaxCategory taxCategory;
    @Override
    public int hashCode() {
        return Objects.hashCode(this.getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Item) {
            return Objects.equals(((Item) obj).getId(), getId());
        }
        return false;
    }
}
