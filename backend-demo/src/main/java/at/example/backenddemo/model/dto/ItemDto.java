package at.example.backenddemo.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
public abstract class ItemDto {
    @NotNull(groups = {ProductValidationGroups.UpdateGroup.class})
    @Null(groups = {ProductValidationGroups.CreateGroup.class})
    private Long id;
    private List<UUID> mediaResourceIds;
    private @NotEmpty String name;
    private String brief;
    private String description;

    private @NotNull BigDecimal price;
    private BigDecimal discountPrice;
    private TaxCategory taxCategory;
}
