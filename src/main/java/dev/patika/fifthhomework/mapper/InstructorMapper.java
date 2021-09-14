package dev.patika.fourthhomeworkavemphract.mapper;

import dev.patika.fourthhomeworkavemphract.dto.GuestInstructorDTO;
import dev.patika.fourthhomeworkavemphract.dto.InstructorDTO;
import dev.patika.fourthhomeworkavemphract.dto.RegularInstructorDTO;
import dev.patika.fourthhomeworkavemphract.model.Course;
import dev.patika.fourthhomeworkavemphract.model.GuestInstructor;
import dev.patika.fourthhomeworkavemphract.model.Instructor;
import dev.patika.fourthhomeworkavemphract.model.RegularInstructor;
import dev.patika.fourthhomeworkavemphract.service.CourseService;
import dev.patika.fourthhomeworkavemphract.service.InstructorService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;


@Mapper(componentModel = "spring")
public abstract class InstructorMapper {
    @Autowired
    protected InstructorService instructorService;

    @Autowired
    protected CourseService courseService;


    @Mappings({
            @Mapping(target = "courses", expression = "java(instructorsCourses(instructorDTO))")
    })
    public abstract Instructor instructorDTOtoInstructor(InstructorDTO instructorDTO);
    @Mappings({
            @Mapping(target = "coursesId",expression = "java(instructorsCoursesId(instructor))")
    })
    public abstract InstructorDTO instructorToInstructorDTO(Instructor instructor);

    @ObjectFactory
    protected InstructorDTO toInstructorDTO(Instructor instructor){
        if (instructor==null){
            throw new RuntimeException();
        }
        else if (instructor instanceof RegularInstructor){
            RegularInstructorDTO regularInstructorDTO=new RegularInstructorDTO();
            regularInstructorDTO.setConstantSalary(((RegularInstructor) instructor).getConstantSalary());
            return regularInstructorDTO;
        }
        else if (instructor instanceof GuestInstructor){
            GuestInstructorDTO guestInstructorDTO=new GuestInstructorDTO();
            guestInstructorDTO.setHourlySalary(((GuestInstructor) instructor).getHourlySalary());
            return guestInstructorDTO;
        }
        else {
            throw new RuntimeException();
        }
    }

    @ObjectFactory
    protected Instructor toInstructor(InstructorDTO instructorDTO){
        if (instructorDTO==null){
            throw new RuntimeException();
        }
        else if (instructorDTO instanceof RegularInstructorDTO){
            RegularInstructor regularInstructor=new RegularInstructor();
            regularInstructor.setConstantSalary(((RegularInstructorDTO) instructorDTO).getConstantSalary());
            return regularInstructor;
        }
        else if (instructorDTO instanceof GuestInstructorDTO){
            GuestInstructor guestInstructor=new GuestInstructor();
            guestInstructor.setHourlySalary(((GuestInstructorDTO) instructorDTO).getHourlySalary());
            return guestInstructor;
        }
        else {
            throw new RuntimeException();
        }
    }

    protected Set<Integer> instructorsCoursesId(Instructor instructor){
        Set<Integer> coursesId=new HashSet<>();
        instructor.getCourses().iterator().forEachRemaining(course -> coursesId.add(course.getId()));
        return coursesId;
    }
    protected Set<Course> instructorsCourses(InstructorDTO instructorDTO){
        Set<Course> courses=new HashSet<>();
        for (int i:instructorDTO.getCoursesId()){
            courses.add(courseService.findById(i));
        }
        return courses;
    }
}
