package dev.patika.fifthhomework.exception;

import dev.patika.fifthhomework.model.Student;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.Period;

@EqualsAndHashCode(callSuper = false)
@Data
public class StudentAgeNotValidException extends RuntimeException{
    private final Student student;

    public StudentAgeNotValidException(Student student) {
        super("Student age not valid. Expected over 18 and below 40.\nActual: "+ Period.between(student.getBirthDate(), LocalDate.now()).getYears() +"\nStudent entity: "+student.toString());
        this.student=student;
    }

}