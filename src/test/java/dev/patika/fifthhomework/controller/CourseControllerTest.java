package dev.patika.fifthhomework.controller;

import dev.patika.fifthhomework.dto.CourseDTO;
import dev.patika.fifthhomework.mapper.CourseMapper;
import dev.patika.fifthhomework.model.Course;
import dev.patika.fifthhomework.service.CourseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourseControllerTest {

    @InjectMocks
    CourseController courseController;
    @Mock
    CourseService courseService;
    @Mock
    CourseMapper courseMapper;

    @Test
    void findAll() {
        List<Course> expected = new ArrayList<>();
        when(courseService.findAll()).thenReturn(expected);

        List<Course> actual = courseController.findAll().getBody().stream().map(courseMapper::toCourse).collect(Collectors.toList());

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.size(), actual.size()),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void findById() {
        Course course = new Course();
        CourseDTO expected = new CourseDTO();

        when(courseService.findById(anyInt())).thenReturn(course);
        when(courseMapper.toCourseDTO(course)).thenReturn(expected);

        CourseDTO actual = courseController.findById(anyInt()).getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void findByCode() {
        Course course = new Course();
        CourseDTO expected = new CourseDTO();
        when(courseService.findByCode(any())).thenReturn(course);
        when(courseMapper.toCourseDTO(course)).thenReturn(expected);

        CourseDTO actual = courseController.findByCode(any()).getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void save() {
        Course course = new Course();
        CourseDTO expected = new CourseDTO();
        when(courseService.save(any())).thenReturn(course);
        when(courseMapper.toCourseDTO(any())).thenReturn(expected);

        CourseDTO actual = courseController.save(courseMapper.toCourseDTO(any())).getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void update() {
        Course course = new Course();
        CourseDTO expected = new CourseDTO();
        when(courseService.update(any())).thenReturn(course);
        when(courseMapper.toCourseDTO(any())).thenReturn(expected);

        CourseDTO actual = courseController.update(courseMapper.toCourseDTO(any())).getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void deleteById() {
        Course course = new Course();
        CourseDTO expected = new CourseDTO();
        when(courseService.deleteById(anyInt())).thenReturn(course);
        when(courseMapper.toCourseDTO(any())).thenReturn(expected);

        CourseDTO actual = courseController.deleteById(anyInt()).getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected,actual)
        );
    }

    @Test
    void deleteByCode() {
        Course course = new Course();
        CourseDTO expected = new CourseDTO();
        when(courseService.deleteByCode(any())).thenReturn(course);
        when(courseMapper.toCourseDTO(any())).thenReturn(expected);

        CourseDTO actual = courseController.deleteByCode(any()).getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected,actual)
        );
    }
}