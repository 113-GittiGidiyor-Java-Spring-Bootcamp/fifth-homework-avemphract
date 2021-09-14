package dev.patika.fifthhomework.service;

import dev.patika.fifthhomework.exception.AbsentEntityException;
import dev.patika.fifthhomework.model.SalaryUpdateLog;
import dev.patika.fifthhomework.repository.SalaryUpdateInfoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SalaryUpdateLogServiceTest {

    @InjectMocks
    SalaryUpdateLogService salaryUpdateLogService;

    @Mock
    SalaryUpdateInfoRepository salaryUpdateInfoRepository;

    @Test
    void findAll() {
        List<SalaryUpdateLog> expected = new ArrayList<>();
        when(salaryUpdateInfoRepository.findAll()).thenReturn(expected);

        List<SalaryUpdateLog> actual = new ArrayList<>();
        salaryUpdateLogService.findAll().forEach(actual::add);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.size(), actual.size()),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void findByInstructorId() {
        List<SalaryUpdateLog> expected = new ArrayList<>();
        when(salaryUpdateInfoRepository.findByInstructorId(anyInt())).thenReturn(expected);

        List<SalaryUpdateLog> actual = salaryUpdateLogService.findByInstructorId(anyInt());

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.size(), actual.size()),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void findById() {
        SalaryUpdateLog expected = new SalaryUpdateLog();
        when(salaryUpdateInfoRepository.findById(anyInt())).thenReturn(Optional.of(expected));

        SalaryUpdateLog actual = salaryUpdateLogService.findById(anyInt());

        assertAll(
                ()->assertNotNull(actual),
                ()->assertEquals(expected,actual)
        );
    }

    @Test
    void deleteById() {
        SalaryUpdateLog expected = new SalaryUpdateLog();
        when(salaryUpdateInfoRepository.findById(anyInt())).thenReturn(Optional.of(expected));

        SalaryUpdateLog actual = salaryUpdateLogService.findById(anyInt());

        assertAll(
                ()->assertNotNull(actual),
                ()->assertEquals(expected,actual)
        );
    }

    @Test
    void deleteById_id_does_not_exist(){
        when(salaryUpdateInfoRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(AbsentEntityException.class,
                ()->salaryUpdateLogService.deleteById(anyInt()));
    }
}