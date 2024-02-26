package at.example.backenddemo.model.db;
import at.example.backenddemo.model.enumeration.ProductAvailability;
import at.example.backenddemo.model.enumeration.ProductType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
/**
 * The "Product" class represents a product.
 *
 * @author Bert Ödemis
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "product")
@Table(name = "product", schema = "demo")
@AllArgsConstructor
@NoArgsConstructor
public class Product extends Item {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private ProductType type;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private ProductAvailability availabilityState;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "product_specification_id")
    private ProductSpecification specification;
}
