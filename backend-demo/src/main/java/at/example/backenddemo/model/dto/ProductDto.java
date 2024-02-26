package at.example.backenddemo.model.dto;

import at.example.backenddemo.model.db.ProductSpecification;
import at.example.backenddemo.model.enumeration.ProductAvailability;
import at.example.backenddemo.model.enumeration.ProductType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductDto extends ItemDto{

    @NotNull
    private ProductType productType;
    private UUID supplier;
    private ProductAvailability productAvailability;
    private ProductSpecification specification;
}
