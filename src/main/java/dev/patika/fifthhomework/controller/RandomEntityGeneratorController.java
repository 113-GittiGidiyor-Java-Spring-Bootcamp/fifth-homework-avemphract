package dev.patika.fourthhomeworkavemphract.controller;

import dev.patika.fourthhomeworkavemphract.mapper.CourseMapper;
import dev.patika.fourthhomeworkavemphract.mapper.InstructorMapper;
import dev.patika.fourthhomeworkavemphract.mapper.StudentMapper;
import dev.patika.fourthhomeworkavemphract.model.Course;
import dev.patika.fourthhomeworkavemphract.model.Instructor;
import dev.patika.fourthhomeworkavemphract.model.Student;
import dev.patika.fourthhomeworkavemphract.service.CourseService;
import dev.patika.fourthhomeworkavemphract.service.InstructorService;
import dev.patika.fourthhomeworkavemphract.service.StudentService;
import dev.patika.fourthhomeworkavemphract.utils.RandomCourseGenerator;
import dev.patika.fourthhomeworkavemphract.utils.RandomInstructorGenerator;
import dev.patika.fourthhomeworkavemphract.utils.RandomStudentGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/generator")
public class RandomEntityGeneratorController {
    RandomCourseGenerator courseGenerator;
    RandomInstructorGenerator instructorGenerator;
    RandomStudentGenerator studentGenerator;

    CourseService courseService;
    InstructorService instructorService;
    StudentService studentService;

    CourseMapper courseMapper;
    InstructorMapper instructorMapper;
    StudentMapper studentMapper;

    @Autowired
    public RandomEntityGeneratorController(CourseService courseService, InstructorService instructorService, StudentService studentService,
                                           CourseMapper courseMapper, InstructorMapper instructorMapper, StudentMapper studentMapper) {
        this.courseService=courseService;
        this.instructorService=instructorService;
        this.studentService=studentService;

        this.courseMapper=courseMapper;
        this.instructorMapper=instructorMapper;
        this.studentMapper=studentMapper;

        courseGenerator=new RandomCourseGenerator();
        instructorGenerator=new RandomInstructorGenerator();
        studentGenerator=new RandomStudentGenerator(4,20);
    }

    @GetMapping()
    public ResponseEntity<String> generateEntities(@RequestParam int courseCount, @RequestParam int instructorCount, @RequestParam int studentCount){
        courseGenerator.init(courseService.findAll());
        instructorGenerator.init(instructorService.findAll());

        List<Course> courseList = new ArrayList<>();
        List<Instructor> instructorList = new ArrayList<>();
        List<Student> studentList = new ArrayList<>();
        for (int i=0;i<courseCount;i++){
            courseList.add(courseService.save(courseMapper.courseToCourseDTO(courseGenerator.generateCourse())));
        }
        for (int i=0;i<instructorCount;i++){
            instructorList.add(instructorService.save(instructorMapper.instructorToInstructorDTO(instructorGenerator.generateInstructor())));
        }
        for (int i=0;i<studentCount;i++){
            studentList.add(studentService.save(studentMapper.studentToStudentDTO(studentGenerator.generateRandomStudent())));
        }

        instructorGenerator.setCourses(instructorList,courseList);
        studentGenerator.setCourses(studentList,courseList);

        for (Course course:courseList){
            courseService.update(courseMapper.courseToCourseDTO(course));
        }
        for (Student student:studentList){
            studentService.update(studentMapper.studentToStudentDTO(student));
        }
        for (Instructor instructor:instructorList){
            instructorService.update(instructorMapper.instructorToInstructorDTO(instructor));
        }
        return ResponseEntity.ok("Generated courses count: "+courseCount+"\nGenerated instructors count: "+instructorCount+"\nGenerated students count: "+studentCount);
    }


}
