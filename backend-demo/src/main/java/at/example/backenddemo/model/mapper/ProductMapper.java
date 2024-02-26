package at.example.backenddemo.model.mapper;

import at.example.backenddemo.model.db.MediaResource;
import at.example.backenddemo.model.db.Product;
import at.example.backenddemo.model.dto.ProductDto;
import at.example.backenddemo.service.ImageService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = {ImageService.class})
public abstract class ProductMapper implements GenericTwoWayMapper<Product, ProductDto> {

    @Autowired
    protected ImageService imageService;

    @Mapping(target = "mediaResourceIds", expression = "java(mapMediaResourceToId(product.getMediaResources()))")
    @Mapping(target = "productType", source = "type")
    @Mapping(target = "productAvailability", source = "availabilityState")
    public abstract ProductDto toDto(Product product);

    @Mapping(target = "mediaResources", expression = "java(mapIdsToMediaResources(productDto.getMediaResourceIds()))")
    @Mapping(target = "type", source = "productType")
    @Mapping(target = "availabilityState", source = "productAvailability")
    public abstract Product toEntity(ProductDto productDto);

    List<MediaResource> mapIdsToMediaResources(List<UUID> mediaResourceId) {
        return mediaResourceId.stream()
                .map((id) -> imageService.loadMediaResource(id))
                .toList();
    }

    List<UUID> mapMediaResourceToId(List<MediaResource> mediaResources){
        return mediaResources.stream().map(MediaResource::getId).toList();
    }
}
