package dev.patika.fourthhomeworkavemphract.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@JsonTypeInfo(use= JsonTypeInfo.Id.DEDUCTION, defaultImpl = Instructor.class)
@JsonSubTypes({
        @JsonSubTypes.Type(GuestInstructor.class),
        @JsonSubTypes.Type(RegularInstructor.class)
})
@Entity @Inheritance(strategy = InheritanceType.JOINED)
@ToString(exclude = "courses")
@EqualsAndHashCode(exclude = "courses",callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Instructor extends BaseEntity {

    private String name;
    private String address;
    private long phoneNumber;
    @OneToMany(mappedBy = "instructor",cascade = CascadeType.ALL, fetch = FetchType.EAGER) @JsonManagedReference
    private final Set<Course> courses=new HashSet<>();
}
