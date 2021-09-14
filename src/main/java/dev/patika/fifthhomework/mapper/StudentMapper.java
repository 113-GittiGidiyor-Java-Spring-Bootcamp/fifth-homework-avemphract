package dev.patika.fourthhomeworkavemphract.mapper;

import dev.patika.fourthhomeworkavemphract.dto.StudentDTO;
import dev.patika.fourthhomeworkavemphract.model.Course;
import dev.patika.fourthhomeworkavemphract.model.Student;
import dev.patika.fourthhomeworkavemphract.repository.CourseRepository;
import dev.patika.fourthhomeworkavemphract.repository.StudentRepository;
import dev.patika.fourthhomeworkavemphract.service.CourseService;
import dev.patika.fourthhomeworkavemphract.service.StudentService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

@Mapper
public abstract class StudentMapper {
    @Autowired
    protected StudentService studentService;
    @Autowired
    protected CourseService courseService;

    @Mappings({
            @Mapping(target = "courses",expression = "java(studentCourses(studentDTO))")
    })
    public abstract Student studentDTOtoStudent(StudentDTO studentDTO);
    @Mappings({
            @Mapping(target = "coursesId",expression = "java(studentCoursesId(student))")
    })
    public abstract StudentDTO studentToStudentDTO(Student student);

    protected Set<Course> studentCourses(StudentDTO studentDTO){
        Set<Course> courses=new HashSet<>();
        for (int i:studentDTO.getCoursesId()){
            courses.add(courseService.findById(i));
        }
        return courses;
    }
    protected Set<Integer> studentCoursesId(Student student){
        Set<Integer> coursesId=new HashSet<>();
        for (Course c:student.getCourses()){
            coursesId.add(c.getId());
        }
        return coursesId;
    }
}
