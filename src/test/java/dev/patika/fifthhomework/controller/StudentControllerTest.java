package dev.patika.fifthhomework.controller;

import dev.patika.fifthhomework.dto.StudentDTO;
import dev.patika.fifthhomework.mapper.StudentMapper;
import dev.patika.fifthhomework.model.Student;
import dev.patika.fifthhomework.service.StudentService;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    @InjectMocks
    StudentController studentController;
    @Mock
    StudentService studentService;
    @Mock
    StudentMapper studentMapper;

    @Test
    void findAll() {
        List<Student> expected=new ArrayList<>();
        when(studentService.findAll()).thenReturn(expected);

        List<Student> actual=studentController.findAll().getBody().stream().map(studentMapper::toStudent).collect(Collectors.toList());

        assertAll(
                ()->assertNotNull(actual),
                ()->assertEquals(expected.size(),actual.size()),
                ()->assertEquals(expected,actual)
        );
    }

    @Test
    void findById() {
        Student student=new Student();
        StudentDTO expected=new StudentDTO();

        when(studentService.findById(anyInt())).thenReturn(student);
        when(studentMapper.toStudentDTO(student)).thenReturn(expected);

        StudentDTO actual = studentController.findById(anyInt()).getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void save() {
        Student student=new Student();
        StudentDTO expected=new StudentDTO();

        when(studentService.save(any())).thenReturn(student);
        when(studentMapper.toStudentDTO(student)).thenReturn(expected);

        StudentDTO actual = studentController.save(any()).getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void update() {
        Student student=new Student();
        StudentDTO expected=new StudentDTO();

        when(studentService.update(any())).thenReturn(student);
        when(studentMapper.toStudentDTO(student)).thenReturn(expected);

        StudentDTO actual = studentController.update(any()).getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void deleteById() {
        Student student=new Student();
        StudentDTO expected=new StudentDTO();

        when(studentService.deleteById(anyInt())).thenReturn(student);
        when(studentMapper.toStudentDTO(student)).thenReturn(expected);

        StudentDTO actual = studentController.deleteById(anyInt()).getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void deleteByName() {
        Student student=new Student();
        StudentDTO expected=new StudentDTO();

        when(studentService.deleteByName(any())).thenReturn(student);
        when(studentMapper.toStudentDTO(student)).thenReturn(expected);

        StudentDTO actual = studentController.deleteByName(any()).getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void groupByAddress() {
        List<Pair<String,Long>> expected=new ArrayList<>();
        when(studentService.groupByAddress()).thenReturn(expected);

        List<Pair<String,Long>> actual=studentController.groupByAddress().getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.size(), actual.size()),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void groupByGender() {
        List<Pair<String,Long>> expected=new ArrayList<>();
        when(studentService.groupByGender()).thenReturn(expected);

        List<Pair<String,Long>> actual=studentController.groupByGender().getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.size(), actual.size()),
                () -> assertEquals(expected, actual)
        );
    }
}