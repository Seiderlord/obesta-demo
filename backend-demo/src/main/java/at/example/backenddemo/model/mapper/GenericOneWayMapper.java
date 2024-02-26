package at.example.backenddemo.model.mapper;

import java.util.List;

public interface GenericOneWayMapper<ENTITY, DTO> {
    DTO toDto(ENTITY entity);

    List<DTO> toDto(List<ENTITY> dtoList);

}
