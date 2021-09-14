package dev.patika.fifthhomework.controller;

import dev.patika.fifthhomework.exception.ErrorEntity;
import dev.patika.fifthhomework.service.ErrorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ErrorControllerTest {

    @InjectMocks
    ErrorController errorController;

    @Mock
    ErrorService errorService;

    @Test
    void findAll() {
        List<ErrorEntity> expected = new ArrayList<>();
        when(errorService.findAll()).thenReturn(expected);

        List<ErrorEntity> actual = errorController.findAll().getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertTrue(expected.size() == actual.size()),
                () -> assertEquals(expected, actual)
        );

    }

    @Test
    void findById() {
        ErrorEntity expected = new ErrorEntity();
        when(errorService.findById(anyInt())).thenReturn(expected);

        ErrorEntity actual = errorController.findById(anyInt()).getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );

    }

    @Test
    void findByErrorCode() {
        List<ErrorEntity> expected = new ArrayList<>();
        when(errorService.findByErrorCode(anyInt())).thenReturn(expected);

        List<ErrorEntity> actual = errorController.findByErrorCode(anyInt()).getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.size(), actual.size()),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void findByDate() {
        List<ErrorEntity> expected = new ArrayList<>();
        when(errorService.findByDate(any(),any())).thenReturn(expected);

        List<ErrorEntity> actual = errorController.findByDate(any(),any()).getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.size(), actual.size()),
                () -> assertEquals(expected, actual)
        );
    }
}