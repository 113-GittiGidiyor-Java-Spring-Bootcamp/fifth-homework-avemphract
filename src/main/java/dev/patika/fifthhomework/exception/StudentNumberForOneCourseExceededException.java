package dev.patika.fourthhomeworkavemphract.exception;

import dev.patika.fourthhomeworkavemphract.model.Course;
import dev.patika.fourthhomeworkavemphract.model.Student;
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
