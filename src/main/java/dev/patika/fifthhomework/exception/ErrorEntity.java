package dev.patika.fourthhomeworkavemphract.exception;

import dev.patika.fourthhomeworkavemphract.model.BaseEntity;
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
