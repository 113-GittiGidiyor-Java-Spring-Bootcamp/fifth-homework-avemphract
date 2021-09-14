package dev.patika.fifthhomework.service;

import dev.patika.fifthhomework.model.BaseEntity;

import java.util.List;

public interface BaseService<T extends BaseEntity> {
    List<T> findAll();
    T findById(int id);
    T save(T object);
    T deleteById(int id);
    T update(T object);
}
