package dev.patika.fifthhomework.mapper;

import dev.patika.fifthhomework.dto.CourseDTO;
import dev.patika.fifthhomework.model.Course;
import dev.patika.fifthhomework.model.Student;
import dev.patika.fifthhomework.service.CourseService;
import dev.patika.fifthhomework.service.InstructorService;
import dev.patika.fifthhomework.service.StudentService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring")
public abstract class CourseMapper {
    @Autowired
    protected CourseService courseService;
    @Autowired
    protected InstructorService instructorService;
    @Autowired
    protected StudentService studentService;


    @Mappings({
            @Mapping(target = "instructor", expression = "java(instructorService.findById(courseDTO.getInstructorId()))"),
            @Mapping(target = "students", expression = "java(getStudents(courseDTO))")
    })
    public abstract Course toCourse(CourseDTO courseDTO);

    @Mappings({
            @Mapping(target = "instructorId", expression = "java(getInstructorId(course))"),
            @Mapping(target = "studentsId", expression = "java(getStudentsId(course))")
    })
    public abstract CourseDTO toCourseDTO(Course course);

    protected Set<Integer> getStudentsId(Course course){
        Set<Integer> studentsIdSet=new HashSet<>();
        course.getStudents().iterator().forEachRemaining(student -> studentsIdSet.add(student.getId()));
        return studentsIdSet;
    }
    protected Set<Student> getStudents(CourseDTO courseDTO){
        Set<Student> studentSet=new HashSet<>();
        courseDTO.getStudentsId().iterator().forEachRemaining(id->studentSet.add(studentService.findById(id)));
        return studentSet;
    }

    protected int getInstructorId(Course course){
        if (course.getInstructor()==null)
            return 0;
        else
            return course.getInstructor().getId();
    }

}
