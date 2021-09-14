package dev.patika.fifthhomework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO extends BaseDTO{

    private Integer id;

    private String name;
    private LocalDate birthDate;
    private String address;
    private String gender;

    private final Set<Integer> coursesId=new HashSet<>();

}
