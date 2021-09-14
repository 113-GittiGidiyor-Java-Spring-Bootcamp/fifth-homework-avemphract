package dev.patika.fourthhomeworkavemphract.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@ToString(exclude = {"courses"})
@EqualsAndHashCode(exclude = {"courses"}, callSuper = true)
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student extends BaseEntity {
    private String name;
    private LocalDate birthDate;
    private String address;
    private String gender;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Student_Course_Pair",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")}
    )
    @JsonIgnore
    private final Set<Course> courses=new HashSet<>();
}
