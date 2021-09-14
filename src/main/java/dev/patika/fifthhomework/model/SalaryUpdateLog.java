package dev.patika.fifthhomework.model;

import lombok.*;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalaryUpdateInfoEntity extends BaseEntity {
    private String clientInfo;
    private int instructorId;
    private double oldSalary;
    private double newSalary;
    private double percentageOfChange;
}
