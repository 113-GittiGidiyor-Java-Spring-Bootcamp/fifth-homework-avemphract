package dev.patika.fifthhomework.controller;


import dev.patika.fifthhomework.dto.BaseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BaseController<T extends BaseDTO> {
    public ResponseEntity<List<T>> findAll();

    public ResponseEntity<T> findById(int id);

    public ResponseEntity<T> save(T body);

    public ResponseEntity<T> update(T body);

    public ResponseEntity<T> deleteById(int id);
}
