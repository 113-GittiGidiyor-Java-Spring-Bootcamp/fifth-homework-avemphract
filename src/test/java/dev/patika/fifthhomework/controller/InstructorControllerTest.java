package dev.patika.fifthhomework.controller;

import dev.patika.fifthhomework.dto.InstructorDTO;
import dev.patika.fifthhomework.dto.RegularInstructorDTO;
import dev.patika.fifthhomework.mapper.InstructorMapper;
import dev.patika.fifthhomework.model.Instructor;
import dev.patika.fifthhomework.model.RegularInstructor;
import dev.patika.fifthhomework.service.InstructorService;
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
class InstructorControllerTest {

    @InjectMocks
    InstructorController instructorController;
    @Mock
    InstructorService instructorService;
    @Mock
    InstructorMapper instructorMapper;

    @Test
    void findAll() {
        List<Instructor> expected = new ArrayList<>();
        when(instructorService.findAll()).thenReturn(expected);

        List<Instructor> actual = instructorController.findAll().getBody().stream().map(instructorMapper::toInstructor).collect(Collectors.toList());

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.size(), actual.size()),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void findById() {
        Instructor instructor = new RegularInstructor();
        InstructorDTO expected = new RegularInstructorDTO();

        when(instructorService.findById(anyInt())).thenReturn(instructor);
        when(instructorMapper.toInstructorDTO(instructor)).thenReturn(expected);

        InstructorDTO actual = instructorController.findById(anyInt()).getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );

    }

    @Test
    void save() {
        Instructor instructor = new RegularInstructor();
        InstructorDTO expected = new RegularInstructorDTO();

        when(instructorService.save(any())).thenReturn(instructor);
        when(instructorMapper.toInstructorDTO(instructor)).thenReturn(expected);

        InstructorDTO actual = instructorController.save(any()).getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void update() {
        Instructor instructor = new RegularInstructor();
        InstructorDTO expected = new RegularInstructorDTO();

        when(instructorService.update(any())).thenReturn(instructor);
        when(instructorMapper.toInstructorDTO(instructor)).thenReturn(expected);

        InstructorDTO actual = instructorController.update(any()).getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void updateSalary() {
        Instructor instructor = new RegularInstructor();
        InstructorDTO expected = new RegularInstructorDTO();

        when(instructorService.updateSalary(anyInt(),anyDouble())).thenReturn(instructor);
        when(instructorMapper.toInstructorDTO(instructor)).thenReturn(expected);

        InstructorDTO actual = instructorController.updateSalary(anyInt(),anyDouble()).getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void deleteById() {
        Instructor instructor = new RegularInstructor();
        InstructorDTO expected = new RegularInstructorDTO();

        when(instructorService.deleteById(anyInt())).thenReturn(instructor);
        when(instructorMapper.toInstructorDTO(instructor)).thenReturn(expected);

        InstructorDTO actual = instructorController.deleteById(anyInt()).getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void deleteByName() {
        Instructor instructor = new RegularInstructor();
        InstructorDTO expected = new RegularInstructorDTO();

        when(instructorService.deleteByName(any())).thenReturn(instructor);
        when(instructorMapper.toInstructorDTO(instructor)).thenReturn(expected);

        InstructorDTO actual = instructorController.deleteByName(any()).getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void getHighestSalaryInstructor() {
        List<Instructor> expected = new ArrayList<>();
        when(instructorService.getHighestSalaryInstructor()).thenReturn(expected);

        List<Instructor> actual = new ArrayList<>();
        instructorController.getHighestSalaryInstructor().getBody().stream().map(instructorMapper::toInstructor).collect(Collectors.toList());

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.size(), actual.size()),
                () -> assertEquals(expected, actual)
        );
    }
}