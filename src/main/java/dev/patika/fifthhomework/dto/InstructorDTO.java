package dev.patika.fourthhomeworkavemphract.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import dev.patika.fourthhomeworkavemphract.model.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = false)
@JsonTypeInfo(use= JsonTypeInfo.Id.DEDUCTION, defaultImpl = InstructorDTO.class)
@JsonSubTypes({
        @JsonSubTypes.Type(GuestInstructorDTO.class),
        @JsonSubTypes.Type(RegularInstructorDTO.class)
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class InstructorDTO extends BaseDTO {

    private Integer id;

    private String name;
    private String address;
    private long phoneNumber;

    private final Set<Integer> coursesId=new HashSet<>();
}
