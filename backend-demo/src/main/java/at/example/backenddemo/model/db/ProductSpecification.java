package at.example.backenddemo.model.db;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

/**
 * The "ProductSpecification" class represents additional information to a product.
 *
 * @author Bert Ödemis
 */
@Data
@EqualsAndHashCode
@Entity(name = "product_specification")
@Table(name = "product_specification", schema = "demo")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ProductSpecification implements Serializable {

    @Id
    @Column(name = "product_specification_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
