package dev.patika.fifthhomework.service;

import dev.patika.fifthhomework.exception.AbsentEntityException;
import dev.patika.fifthhomework.exception.ErrorEntity;
import dev.patika.fifthhomework.repository.ErrorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ErrorServiceTest {

    @InjectMocks
    ErrorService errorService;

    @Mock
    ErrorRepository errorRepository;

    @Test
    void findAll() {
        List<ErrorEntity> expected=new ArrayList<>();
        when(errorRepository.findAll()).thenReturn(expected);

        List<ErrorEntity> actual=new ArrayList<>();
        errorService.findAll().forEach(actual::add);

        assertAll(
                ()->assertNotNull(actual),
                ()->assertEquals(expected.size(),actual.size()),
                ()->assertEquals(expected,actual)
        );
    }

    @Test
    void findById() {
        Optional<ErrorEntity> expected=Optional.of(new ErrorEntity());
        when(errorRepository.findById(anyInt())).thenReturn(expected);

        Optional<ErrorEntity> actual=Optional.of(errorService.findById(anyInt()));

        assertAll(
                ()->assertNotNull(actual),
                ()->assertEquals(expected,actual)
        );
    }

    @Test
    void findById_if_id_does_not_exist(){
        when(errorRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(AbsentEntityException.class,
                ()->errorService.findById(anyInt()));
    }

    @Test
    void save() {
        ErrorEntity expected=new ErrorEntity();
        when(errorRepository.save(any())).thenReturn(expected);

        ErrorEntity actual=errorService.save(any());

        assertAll(
                ()->assertNotNull(actual),
                ()->assertEquals(expected,actual)
        );
    }

    @Test
    void findByErrorCode() {
        List<ErrorEntity> expected=new ArrayList<>();
        when(errorRepository.findByErrorCode(anyInt())).thenReturn(expected);

        List<ErrorEntity> actual=errorService.findByErrorCode(anyInt());

        assertAll(
                ()->assertNotNull(actual),
                ()->assertEquals(expected,actual)
        );
    }

    @Test
    void findByDate() {
        List<ErrorEntity> expected=new ArrayList<>();
        when(errorRepository.findByDateBetween(any(),any())).thenReturn(expected);

        List<ErrorEntity> actual=errorService.findByDate(any(),any());

        assertAll(
                ()->assertNotNull(actual),
                ()->assertEquals(expected.size(),actual.size()),
                ()->assertEquals(expected,actual)
        );
    }
}