package dev.patika.fifthhomework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryUpdateLogDTO extends BaseDTO {
    private int id;

    private String sessionId;
    private String clientURL;
    private String requestURI;

    private double oldSalary;
    private double newSalary;
    private double percentageOfChange;

    private int instructorId;
}
