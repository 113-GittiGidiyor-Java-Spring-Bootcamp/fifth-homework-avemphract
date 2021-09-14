package dev.patika.fifthhomework.service;

import dev.patika.fifthhomework.exception.AbsentEntityException;
import dev.patika.fifthhomework.exception.StudentAgeNotValidException;
import dev.patika.fifthhomework.exception.StudentNumberForOneCourseExceededException;
import dev.patika.fifthhomework.model.Course;
import dev.patika.fifthhomework.model.Student;
import dev.patika.fifthhomework.repository.StudentRepository;
import dev.patika.fifthhomework.utils.RandomStudentGenerator;
import javafx.util.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @InjectMocks
    StudentService studentService;
    @Mock
    StudentRepository studentRepository;

    RandomStudentGenerator studentGenerator;

    @BeforeEach
    void setUp() {
        studentGenerator=new RandomStudentGenerator(0,0);
    }

    @Test
    void findAll() {
        List<Student> expected = new ArrayList<>();
        when(studentRepository.findAll()).thenReturn(expected);

        List<Student> actual = new ArrayList<>();
        studentService.findAll().forEach(actual::add);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.size(), actual.size()),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void findById() {
        Student expected = new Student();
        when(studentRepository.findById(anyInt())).thenReturn(Optional.of(expected));

        Student actual = studentService.findById(anyInt());

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void save() {
        Student expected = new Student();
        expected.setBirthDate(LocalDate.now().minus(18, ChronoUnit.YEARS));

        when(studentRepository.save(any())).thenReturn(expected);

        Student actual = studentService.save(new Student("",LocalDate.now().minus(18, ChronoUnit.YEARS),"",""));

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @ParameterizedTest
    @MethodSource("parameters_save_if_student_age_below_minimum")
    void save_if_student_age_below_minimum(LocalDate birthDate){
        assertThrows(StudentAgeNotValidException.class,
                ()->studentService.save(new Student("",birthDate,"","")));

    }

    private static Stream<Arguments> parameters_save_if_student_age_below_minimum(){
        return Stream.of(
                Arguments.of(LocalDate.now()),
                Arguments.of(LocalDate.now().minus(1, ChronoUnit.DAYS)),
                Arguments.of(LocalDate.now().minus(1, ChronoUnit.MONTHS)),
                Arguments.of(LocalDate.now().minus(1, ChronoUnit.YEARS)),
                Arguments.of(LocalDate.now().minus(10, ChronoUnit.YEARS)),
                Arguments.of(LocalDate.now().minus(17, ChronoUnit.YEARS))
        );
    }

    @ParameterizedTest
    @MethodSource("parameters_save_if_student_age_above_maximum")
    void save_if_student_age_above_maximum(LocalDate birthDate){
        assertThrows(StudentAgeNotValidException.class,
                ()->studentService.save(new Student("",birthDate,"","")));
    }

    private static Stream<Arguments> parameters_save_if_student_age_above_maximum(){
        return Stream.of(
                Arguments.of(LocalDate.now().minus(41, ChronoUnit.YEARS)),
                Arguments.of(LocalDate.now().minus(100, ChronoUnit.YEARS)),
                Arguments.of(LocalDate.now().minus(1000, ChronoUnit.YEARS))
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {20,21,22,30,35,50})
    void save_if_course_above_20_student(int studentCount){
        Course course=new Course();
        for (int i=0;i<studentCount;i++)
            course.getStudents().add(studentGenerator.generateRandomStudent());
        Student savedStudent=new Student("",LocalDate.now().minus(20,ChronoUnit.YEARS),"","");
        savedStudent.getCourses().add(course);
        assertThrows(StudentNumberForOneCourseExceededException.class,
                ()->studentService.save(savedStudent));

    }

    @Test
    void deleteById() {
        Student expected = new Student();
        when(studentRepository.findById(anyInt())).thenReturn(Optional.of(expected));

        Student actual = studentService.deleteById(anyInt());

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void deleteById_if_id_does_not_exist(){
        when(studentRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(AbsentEntityException.class,
                ()->studentService.deleteById(anyInt()));
    }

    @Test
    void deleteByName() {
        Student expected = new Student();
        when(studentRepository.findByName(anyString())).thenReturn(expected);

        Student actual = studentService.deleteByName(anyString());

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void deleteByName_if_name_does_not_exist(){
        when(studentRepository.findByName(anyString())).thenReturn(null);

        assertThrows(AbsentEntityException.class,
                ()->studentService.deleteByName(anyString()));
    }

    @Test
    void update() {
        Student expected = new Student();
        expected.setBirthDate(LocalDate.now().minus(18, ChronoUnit.YEARS));

        when(studentRepository.save(any())).thenReturn(expected);
        when(studentRepository.isIdExists(anyInt())).thenReturn(true);

        Student actual = studentService.update(new Student("",LocalDate.now().minus(18, ChronoUnit.YEARS),"",""));

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void groupByAddress() {
        List<Pair<String, Long>> expected = new ArrayList<>();
        when(studentRepository.groupByAddress()).thenReturn(expected);

        List<Pair<String, Long>> actual = studentService.groupByAddress();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.size(), actual.size()),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void groupByGender() {
        List<Pair<String, Long>> expected = new ArrayList<>();
        when(studentRepository.groupByGender()).thenReturn(expected);

        List<Pair<String, Long>> actual = studentService.groupByGender();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.size(), actual.size()),
                () -> assertEquals(expected, actual)
        );
    }
}