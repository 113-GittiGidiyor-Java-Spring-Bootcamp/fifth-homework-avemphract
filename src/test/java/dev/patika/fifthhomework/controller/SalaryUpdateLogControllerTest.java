package dev.patika.fifthhomework.controller;

import dev.patika.fifthhomework.dto.SalaryUpdateLogDTO;
import dev.patika.fifthhomework.mapper.SalaryUpdateLogMapper;
import dev.patika.fifthhomework.model.SalaryUpdateLog;
import dev.patika.fifthhomework.service.SalaryUpdateLogService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SalaryUpdateLogControllerTest {

    @InjectMocks
    SalaryUpdateLogController salaryUpdateLogController;

    @Mock
    SalaryUpdateLogService salaryUpdateLogService;

    @Mock
    SalaryUpdateLogMapper salaryUpdateLogMapper;

    @Test
    void findAll() {
        List<SalaryUpdateLog> expected = new ArrayList<>();
        when(salaryUpdateLogService.findAll()).thenReturn(expected);

        List<SalaryUpdateLog> actual = salaryUpdateLogController.findAll().getBody().stream().map(salaryUpdateLogMapper::toSalaryUpdateLog).collect(Collectors.toList());

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.size(), actual.size()),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void findByInstructorId() {
        List<SalaryUpdateLog> list = new ArrayList<>();
        List<SalaryUpdateLogDTO> expected = new ArrayList<>();
        when(salaryUpdateLogService.findByInstructorId(anyInt())).thenReturn(list);
        when(salaryUpdateLogMapper.salaryUpdateLogDTOList(list)).thenReturn(expected);

        List<SalaryUpdateLog> actual=salaryUpdateLogMapper.salaryUpdateLogList(salaryUpdateLogController.findByInstructorId(anyInt()).getBody());

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.size(), actual.size()),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void findById() {
        SalaryUpdateLog salaryUpdateLog = new SalaryUpdateLog();
        SalaryUpdateLogDTO expected = new SalaryUpdateLogDTO();

        when(salaryUpdateLogService.findById(anyInt())).thenReturn(salaryUpdateLog);
        when(salaryUpdateLogMapper.toSalaryUpdateLogDTO(salaryUpdateLog)).thenReturn(expected);

        SalaryUpdateLogDTO actual = salaryUpdateLogController.findById(anyInt()).getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void deleteById() {
        SalaryUpdateLog salaryUpdateLog = new SalaryUpdateLog();
        SalaryUpdateLogDTO expected = new SalaryUpdateLogDTO();

        when(salaryUpdateLogService.deleteById(anyInt())).thenReturn(salaryUpdateLog);
        when(salaryUpdateLogMapper.toSalaryUpdateLogDTO(salaryUpdateLog)).thenReturn(expected);

        SalaryUpdateLogDTO actual = salaryUpdateLogController.deleteById(anyInt()).getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }
}