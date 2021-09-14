package dev.patika.fifthhomework.exception;

import dev.patika.fifthhomework.model.Course;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class StudentNumberForOneCourseExceededException extends RuntimeException{
    Course course;
    public StudentNumberForOneCourseExceededException(Course course) {
        super("Student number exceeded in course.\nCourse: "+course.toString());
        this.course=course;
    }
}
