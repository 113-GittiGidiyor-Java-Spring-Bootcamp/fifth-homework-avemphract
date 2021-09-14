package dev.patika.fifthhomework.controller;


import dev.patika.fifthhomework.mapper.CourseMapper;
import dev.patika.fifthhomework.mapper.InstructorMapper;
import dev.patika.fifthhomework.mapper.StudentMapper;
import dev.patika.fifthhomework.model.Course;
import dev.patika.fifthhomework.model.Instructor;
import dev.patika.fifthhomework.model.Student;
import dev.patika.fifthhomework.service.CourseService;
import dev.patika.fifthhomework.service.InstructorService;
import dev.patika.fifthhomework.service.StudentService;
import dev.patika.fifthhomework.utils.RandomCourseGenerator;
import dev.patika.fifthhomework.utils.RandomInstructorGenerator;
import dev.patika.fifthhomework.utils.RandomStudentGenerator;
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
            courseList.add(courseService.save(courseGenerator.generateCourse()));
        }
        for (int i=0;i<instructorCount;i++){
            instructorList.add(instructorService.save(instructorGenerator.generateInstructor()));
        }
        for (int i=0;i<studentCount;i++){
            studentList.add(studentService.save(studentGenerator.generateRandomStudent()));
        }

        instructorGenerator.setCourses(instructorList,courseList);
        studentGenerator.setCourses(studentList,courseList);

        for (Course course:courseList){
            courseService.update(course);
        }
        for (Student student:studentList){
            studentService.update(student);
        }
        for (Instructor instructor:instructorList){
            instructorService.update(instructor);
        }
        return ResponseEntity.ok("Generated courses count: "+courseCount+"\nGenerated instructors count: "+instructorCount+"\nGenerated students count: "+studentCount);
    }


}
