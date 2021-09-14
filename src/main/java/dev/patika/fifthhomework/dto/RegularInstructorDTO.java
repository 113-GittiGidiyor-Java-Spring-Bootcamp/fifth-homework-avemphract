package dev.patika.fourthhomeworkavemphract.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegularInstructorDTO extends InstructorDTO{
    private double constantSalary;

}
