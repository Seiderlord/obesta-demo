package at.example.backenddemo.model.mapper;

import at.example.backenddemo.model.db.MediaResource;
import at.example.backenddemo.model.dto.FileDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class MediaResourceMapper implements GenericOneWayMapper<MediaResource, FileDto> {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "documentType", ignore = true)
    public abstract FileDto toDto(MediaResource file);
}
