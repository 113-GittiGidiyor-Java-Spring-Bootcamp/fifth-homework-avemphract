package dev.patika.fifthhomework.exception;

import dev.patika.fifthhomework.model.Instructor;
import lombok.Data;

@Data
public class InstructorIsAlreadyExistException extends RuntimeException{

    private Instructor instructor;
    public InstructorIsAlreadyExistException(Instructor instructor) {
        super("Instructor is already exist in data base. Given instructor: " + instructor.toString());
        this.instructor=instructor;
    }
}
