package dev.patika.fifthhomework.exception;

import dev.patika.fifthhomework.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@Data
@RequiredArgsConstructor
public class AbsentEntityException extends RuntimeException{
    private int id;

    public AbsentEntityException(Class<?> entityClass, int id) {
        super("Database has not Id:"+id+" in "+entityClass.getSimpleName());
        this.id=id;
    }
}
