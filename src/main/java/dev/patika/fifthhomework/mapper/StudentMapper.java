package dev.patika.fifthhomework.mapper;

import dev.patika.fifthhomework.dto.StudentDTO;
import dev.patika.fifthhomework.model.Course;
import dev.patika.fifthhomework.model.Student;
import dev.patika.fifthhomework.service.CourseService;
import dev.patika.fifthhomework.service.StudentService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
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
    public abstract Student toStudent(StudentDTO studentDTO);
    @Mappings({
            @Mapping(target = "coursesId",expression = "java(studentCoursesId(student))")
    })
    public abstract StudentDTO toStudentDTO(Student student);

    public abstract List<Student> toStudentList(List<StudentDTO> studentDTOList);
    public abstract List<StudentDTO> toStudentListDTO(List<Student> studentList);

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
