package dev.patika.fifthhomework.service;

import dev.patika.fifthhomework.exception.AbsentEntityException;
import dev.patika.fifthhomework.exception.InstructorIsAlreadyExistException;
import dev.patika.fifthhomework.model.GuestInstructor;
import dev.patika.fifthhomework.model.Instructor;
import dev.patika.fifthhomework.model.RegularInstructor;
import dev.patika.fifthhomework.model.SalaryUpdateLog;
import dev.patika.fifthhomework.repository.InstructorRepository;
import dev.patika.fifthhomework.repository.SalaryUpdateInfoRepository;
import dev.patika.fifthhomework.utils.RandomInstructorGenerator;
import dev.patika.fifthhomework.utils.SalaryUpdateRequestInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InstructorServiceTest {

    @Mock
    InstructorRepository instructorRepository;
    @Mock
    SalaryUpdateInfoRepository salaryUpdateInfoRepository;


    @InjectMocks
    InstructorService instructorService;

    RandomInstructorGenerator instructorGenerator;
    @BeforeEach
    void setUp() {
        instructorGenerator=new RandomInstructorGenerator();
    }
    @Test
    void findAll() {
        List<Instructor> expected = new ArrayList<>();
        when(instructorRepository.findAll()).thenReturn(expected);

        List<Instructor> actual = instructorService.findAll();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.size(), actual.size()),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void findById() {
        Optional<Instructor> expected = Optional.of(new RegularInstructor());
        when(instructorRepository.findById(anyInt())).thenReturn(expected);

        Optional<Instructor> actual = Optional.of(instructorService.findById(anyInt()));

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void save() {
        Instructor expected = new RegularInstructor();
        when(instructorRepository.save(any())).thenReturn(expected);

        Instructor actual = instructorService.save(new RegularInstructor("", "", 0, 0d));

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void save_InstructorAlreadyExist(){
        when(instructorRepository.isPhoneNumberExists(anyLong())).thenReturn(true);
        when(instructorRepository.findByPhoneNumber(anyLong())).thenReturn(new RegularInstructor());

        assertThrows(InstructorIsAlreadyExistException.class,
                ()->instructorService.save(new RegularInstructor("","",0,0))
        );
    }

    @Test
    void deleteById() {
        Instructor expected = new RegularInstructor();
        when(instructorRepository.findById(anyInt())).thenReturn(Optional.of(expected));

        Instructor actual = instructorService.deleteById(anyInt());

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void deleteById_if_entity_does_not_exist(){
        when(instructorRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(AbsentEntityException.class,
                ()->instructorService.deleteById(anyInt())
        );
    }

    @Test
    void deleteByName() {
        Instructor expected = new RegularInstructor();
        when(instructorRepository.findByName(anyString())).thenReturn(expected);

        Instructor actual = instructorService.deleteByName(anyString());

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );

    }

    @Test
    void deleteByName_if_entity_name_does_not_exist(){
        when(instructorRepository.findByName(anyString())).thenReturn(null);

        assertThrows(AbsentEntityException.class,
                ()->instructorService.deleteByName(anyString()));
    }

    @Test
    void update() {
        Instructor expected = new RegularInstructor();
        when(instructorRepository.save(any())).thenReturn(expected);

        Instructor actual = instructorService.save(new RegularInstructor("", "", 0, 0d));

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void update_instructor_id_does_not_exist(){
        when(instructorRepository.isIdExists(anyInt())).thenReturn(false);

        assertThrows(AbsentEntityException.class,
                ()->instructorService.update(new RegularInstructor("","",0,0)));
    }

    @Test
    void update_instructor_change_number_and_that_number_already_exist(){
        when(instructorRepository.isIdExists(anyInt())).thenReturn(true);
        Instructor savedInstructor=new RegularInstructor("","",1,0);
        savedInstructor.setId(1);
        when(instructorRepository.findByPhoneNumber(anyLong())).thenReturn(savedInstructor);

        assertThrows(InstructorIsAlreadyExistException.class,
                ()->instructorService.update(new RegularInstructor("","",1,0)));

    }

    @Test
    void updateSalary() {
        /*
        Instructor expected = new RegularInstructor();
        when(instructorRepository.findById(anyInt())).thenReturn(Optional.of(expected));
        when(salaryUpdateInfoRepository.save(any())).thenReturn(new SalaryUpdateLog());

        Instructor actual = instructorService.updateSalary(anyInt(),1);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );*/
    }


    @ParameterizedTest
    @CsvSource({"1,1", "1,2", "2,3","3,3","4,3","10,10"})
    void getHighestSalaryInstructor(int regular, int guest) {
        List<RegularInstructor> regularInstructors = new ArrayList<>();
        for (int i=0;i<regular;i++)
            regularInstructors.add(instructorGenerator.generateRegularInstructor());
        when(instructorRepository.getRegularInstructorsSortFromSalary()).thenReturn(regularInstructors);

        List<GuestInstructor> guestInstructors = new ArrayList<>();
        for (int i=0;i<guest;i++)
            guestInstructors.add(instructorGenerator.generateGuestInstructor());
        when(instructorRepository.getGuestInstructorsSortFromSalary()).thenReturn(guestInstructors);
        List<Instructor> expected = new ArrayList<>();
        expected.addAll(instructorRepository.getGuestInstructorsSortFromSalary().subList(0, (int) Math.min(3,instructorRepository.getGuestInstructorCount())));
        expected.addAll(instructorRepository.getRegularInstructorsSortFromSalary().subList(0, (int) Math.min(3,instructorRepository.getRegularInstructorCount())));

        List<Instructor> actual=instructorService.getHighestSalaryInstructor();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.size(), actual.size()),
                () -> assertEquals(expected, actual)
        );
    }
}