package dev.patika.fifthhomework.service;

import dev.patika.fifthhomework.exception.AbsentEntityException;
import dev.patika.fifthhomework.exception.CourseIsAlreadyExistException;
import dev.patika.fifthhomework.exception.StudentNumberForOneCourseExceededException;
import dev.patika.fifthhomework.model.Course;
import dev.patika.fifthhomework.repository.CourseRepository;
import dev.patika.fifthhomework.utils.RandomStudentGenerator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

    @Mock
    CourseRepository courseRepository;

    @InjectMocks
    CourseService courseService;

    RandomStudentGenerator studentGenerator;

    @BeforeEach
    void setUp() {
        studentGenerator=new RandomStudentGenerator(0,0);
    }

    @Test
    void findAll() {
        List<Course> expected=new ArrayList<>();
        when(courseRepository.findAll()).thenReturn(expected);

        List<Course> actual= new ArrayList<>();
        courseService.findAll().forEach(actual::add);

        assertAll(
                ()->assertNotNull(actual),
                ()->assertEquals(expected.size(),actual.size()),
                ()->assertEquals(expected,actual)
        );
    }

    @Test
    void findByCode() {
        Course expected=new Course();
        when(courseRepository.findByCourseCode(anyString())).thenReturn(expected);

        Course actual=courseService.findByCode(anyString());

        assertAll(
                ()->assertNotNull(actual),
                ()->assertEquals(expected,actual)
        );
    }

    @Test
    void findByCode_if_courseCode_does_not_exist(){
        when(courseRepository.findByCourseCode(anyString())).thenReturn(null);

        assertThrows(AbsentEntityException.class,
                ()->courseService.findByCode(anyString()));
    }

    @Test
    void findById() {
        Course expected=new Course();
        when(courseRepository.findById(anyInt())).thenReturn(Optional.of(expected));

        Course actual=courseService.findById(anyInt());

        assertAll(
                ()->assertNotNull(actual),
                ()->assertEquals(expected,actual)
        );
    }

    @Test
    void save() {
        Course expected=new Course();
        when(courseRepository.save(any())).thenReturn(expected);

        Course actual = courseService.save(new Course("AAAAAA","AAA",0,null));

        assertAll(
                ()->assertNotNull(actual),
                ()->assertEquals(expected,actual)
        );
    }

    @Test
    void save_course_is_already_exist(){
        when(courseRepository.isCodeExists(any())).thenReturn(true);

        assertThrows(CourseIsAlreadyExistException.class,
                ()->courseService.save(new Course("AAAAA","AAA",0,null)));
    }

    @Test
    void save_course_student_size_over_20(){
        when(courseRepository.isCodeExists(any())).thenReturn(false);

        Course course=new Course();
        for (int i=0;i<21;i++){
            course.getStudents().add(studentGenerator.generateRandomStudent());
        }
        assertThrows(StudentNumberForOneCourseExceededException.class,
                ()->courseService.save(course));
    }


    @Test
    void deleteById() {
        Course expected =new Course();
        when(courseRepository.findById(anyInt())).thenReturn(Optional.of(expected));

        Course actual = courseService.deleteById(anyInt());

        assertAll(
                ()->assertNotNull(actual),
                ()->assertEquals(expected,actual)
        );
    }

    @Test
    void deleteById_if_findById_return_null(){
        when(courseRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(AbsentEntityException.class,
                () -> courseService.deleteById(anyInt()));
    }

    @Test
    void deleteByCode() {
        Course expected = new Course();
        when(courseRepository.findByCourseCode(anyString())).thenReturn(expected);

        Course actual=courseService.deleteByCode(anyString());

        assertAll(
                ()->assertNotNull(actual),
                ()->assertEquals(expected,actual)
        );
    }

    @Test
    void deleteByCode_AbsentEntityException(){
        when(courseRepository.findByCourseCode(anyString())).thenReturn(null);

        assertThrows(AbsentEntityException.class,
                () -> courseService.deleteByCode(anyString()));
    }


    @Test
    void update() {
        Course expected=new Course();

        when(courseRepository.save(any())).thenReturn(expected);
        when(courseRepository.isIdExists(expected.getId())).thenReturn(true);

        Course actual= courseService.update(new Course());

        assertAll(
                ()->assertNotNull(actual),
                ()->assertEquals(expected,actual)
        );
    }

    @Test
    void update_isIdExists(){
        assertThrows(AbsentEntityException.class,
                () -> courseService.update(new Course()));
    }

}