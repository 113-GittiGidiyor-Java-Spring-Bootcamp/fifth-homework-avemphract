package dev.patika.fifthhomework.exception;

import dev.patika.fifthhomework.model.Course;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CourseIsAlreadyExistException extends RuntimeException{

    private Course course;
    public CourseIsAlreadyExistException(Course course) {
        super("Course is already exist in data base. Given course: "+course.toString());
        this.course=course;
    }
}
