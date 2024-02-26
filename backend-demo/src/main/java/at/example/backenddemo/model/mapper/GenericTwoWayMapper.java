package at.example.backenddemo.model.mapper;

import java.util.List;

public interface GenericTwoWayMapper<ENTITY, DTO> {
    ENTITY toEntity(DTO dto);

    DTO toDto(ENTITY entity);

    List<ENTITY> toEntity(List<DTO> dtoList);

    List<DTO> toDto(List<ENTITY> dtoList);

}
