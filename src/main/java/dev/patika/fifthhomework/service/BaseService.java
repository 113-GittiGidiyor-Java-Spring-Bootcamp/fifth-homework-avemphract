package dev.patika.fourthhomeworkavemphract.service;

import dev.patika.fourthhomeworkavemphract.dto.BaseDTO;
import dev.patika.fourthhomeworkavemphract.model.BaseEntity;

import java.util.List;

public interface BaseService<T extends BaseEntity,D extends BaseDTO> {
    List<T> findAll();
    T findById(int id);
    T save(D object);
    T deleteById(int id);
    T update(D object);
}
