package dev.patika.fifthhomework.exception;

import dev.patika.fifthhomework.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class ErrorEntity extends BaseEntity {
    private String errorMessage;
    private String erroredEntity;
    private int errorCode;
}
