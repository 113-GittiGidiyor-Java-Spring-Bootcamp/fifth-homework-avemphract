package dev.patika.fifthhomework.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.Instant;

@EqualsAndHashCode
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryUpdateLog {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String sessionId;
    private String clientURL;
    private String requestURI;

    private double oldSalary;
    private double newSalary;
    private double percentageOfChange;

    @CreatedDate
    private Instant createdTime=Instant.now();
    @ManyToOne @JsonBackReference
    private Instructor instructor;
}
