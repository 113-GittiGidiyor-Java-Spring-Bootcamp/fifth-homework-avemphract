package dev.patika.fourthhomeworkavemphract.mapper;

import dev.patika.fourthhomeworkavemphract.dto.BaseDTO;
import dev.patika.fourthhomeworkavemphract.model.BaseEntity;

public interface BaseMapper<T extends BaseEntity,D extends BaseDTO> {
    T fromDTO(D dtoObject);
    D toDTO(T object);
}
