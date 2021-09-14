package dev.patika.fourthhomeworkavemphract.service;

import dev.patika.fourthhomeworkavemphract.dto.InstructorDTO;
import dev.patika.fourthhomeworkavemphract.exception.AbsentEntityException;
import dev.patika.fourthhomeworkavemphract.exception.InstructorIsAlreadyExistException;
import dev.patika.fourthhomeworkavemphract.mapper.InstructorMapper;
import dev.patika.fourthhomeworkavemphract.model.Course;
import dev.patika.fourthhomeworkavemphract.model.Instructor;
import dev.patika.fourthhomeworkavemphract.repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class InstructorService implements BaseService<Instructor, InstructorDTO> {
    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    private InstructorMapper instructorMapper;

    /**
     * Get all instructors in database
     * @return List of Courses
     */
    @Override @Transactional(readOnly = true)
    public List<Instructor> findAll() {
        List<Instructor> list=new ArrayList<>();
        instructorRepository.findAll().forEach(n->list.add(n));
        return list;
    }

    /**
     * Get instructor from id
     * @param id requested id
     * @return Instructor
     */
    @Override @Transactional(readOnly = true)
    public Instructor findById(int id) {
        Optional<Instructor> optionalInstructor=instructorRepository.findById(id);
        return optionalInstructor.orElse(null);
    }

    /**
     * Save instructor and return saved instructor
     * @param object InstructorDTO
     * @return Instructor
     */
    @Override @Transactional(readOnly = false)
    public Instructor save(InstructorDTO object) {
        if (instructorRepository.isPhoneNumberExists(object.getPhoneNumber()))
            throw new InstructorIsAlreadyExistException(instructorRepository.findByPhoneNumber(object.getPhoneNumber()));
        return instructorRepository.save(instructorMapper.instructorDTOtoInstructor(object));
    }

    /**
     * Delete instructor from id and return deleted instructor
     * @param id instructor id
     * @return Instructor
     */
    @Override @Transactional(readOnly = false)
    public Instructor deleteById(int id) {
        Optional<Instructor> optional = instructorRepository.findById(id);
        if (!optional.isPresent())
            throw new AbsentEntityException(Instructor.class,id);
        deleting(optional.get());
        instructorRepository.deleteById(id);
        return optional.get();
    }

    /**
     * Delete instructor from instructor name and return deleted instructor
     * @param name instructor name
     * @return Instructor
     */
    @Transactional(readOnly = false)
    public Instructor deleteByName(String name){
        Optional<Instructor> optional = Optional.of(instructorRepository.findById(name));
        if (!optional.isPresent())
            throw new AbsentEntityException(Instructor.class,0);
        deleting(optional.get());
        instructorRepository.deleteByName(name);
        return optional.get();
    }

    private void deleting(Instructor instructor){
        for (Course course:instructor.getCourses())
            course.setInstructor(null);
        instructor.getCourses().clear();
    }

    /**
     * Update instructor and return updated one
     * @param object InstructorDTO
     * @return Instructor
     */
    @Override @Transactional(readOnly = false)
    public Instructor update(InstructorDTO object) {
        if (!instructorRepository.isIdExists(object.getId()))
            throw new AbsentEntityException(Instructor.class,object.getId());
        Instructor samePhone=instructorRepository.findByPhoneNumber(object.getPhoneNumber());
        if (samePhone!=null && !samePhone.getId().equals(object.getId()))
            throw new InstructorIsAlreadyExistException(samePhone);
        return instructorRepository.save(instructorMapper.instructorDTOtoInstructor(object));
    }

    /**
     * Get the 3 highest hourly salary GuestInstructors and the 3 highest constant salary RegularInstructors
     * @return List of Instructor
     */
    @Transactional(readOnly = true)
    public List<Instructor> getHighestSalaryInstructor(){
        List<Instructor> instructorList=new ArrayList<>();
        instructorList.addAll(instructorRepository.getGuestInstructorsSortFromSalary().subList(0,3));
        instructorList.addAll(instructorRepository.getRegularInstructorsSortFromSalary().subList(0,3));
        return instructorList;
    }
}
