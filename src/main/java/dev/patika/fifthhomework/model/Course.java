package dev.patika.fourthhomeworkavemphract.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@ToString(exclude = {"instructor","students"})
@EqualsAndHashCode(exclude = {"instructor","students"},callSuper = true)
@Data @AllArgsConstructor @NoArgsConstructor
public class Course extends BaseEntity {
    private String courseName;
    private String courseCode;
    private double credit;
    
    @ManyToOne(fetch = FetchType.EAGER) @JsonBackReference
    private Instructor instructor;

    @ManyToMany(mappedBy = "courses", cascade = CascadeType.ALL, fetch = FetchType.EAGER) @JsonIgnore
    private final Set<Student> students=new HashSet<>();

}
