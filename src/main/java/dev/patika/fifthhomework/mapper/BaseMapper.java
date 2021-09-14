package dev.patika.fifthhomework.mapper;


import dev.patika.fifthhomework.dto.BaseDTO;
import dev.patika.fifthhomework.model.BaseEntity;

public interface BaseMapper<T extends BaseEntity,D extends BaseDTO> {
    T fromDTO(D dtoObject);
    D toDTO(T object);
}
