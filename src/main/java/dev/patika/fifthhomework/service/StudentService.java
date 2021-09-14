package dev.patika.fifthhomework.service;

import dev.patika.fifthhomework.exception.AbsentEntityException;
import dev.patika.fifthhomework.exception.StudentAgeNotValidException;
import dev.patika.fifthhomework.exception.StudentNumberForOneCourseExceededException;
import dev.patika.fifthhomework.mapper.StudentMapper;
import dev.patika.fifthhomework.model.Course;
import dev.patika.fifthhomework.model.Student;
import dev.patika.fifthhomework.repository.StudentRepository;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class StudentService implements BaseService<Student> {
    private static final int MINIMUM_AGE=18;
    private static final int MAXIMUM_AGE=40;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentMapper studentMapper;

    /**
     * Get all students in database
     * @return List of Students
     */
    @Override
    @Transactional(readOnly = true)
    public List<Student> findAll() {
        List<Student> list=new ArrayList<>();
        studentRepository.findAll().forEach(s->list.add(s));
        return list;
    }

    /**
     * Get student from id
     * @param id requested courses id
     * @return Course
     */
    @Override
    @Transactional(readOnly = true)
    public Student findById(int id) {
        Optional<Student> optional=studentRepository.findById(id);
        return optional.orElse(null);
    }

    /**
     * Save student and return saved student
     * @param object StudentDTO
     * @return Student
     */
    @Override
    @Transactional(readOnly = false)
    public Student save(Student object) {
        Student student=verify(object);
        return studentRepository.save(student);
    }

    /**
     * Delete student from id and return deleted student
     * @param id student id
     * @return Student
     */
    @Override
    @Transactional(readOnly = false)
    public Student deleteById(int id) {
        Optional<Student> optional = studentRepository.findById(id);
        if (!optional.isPresent())
            throw new AbsentEntityException(Student.class,id);
        studentRepository.deleteById(id);
        return optional.get();
    }

    /**
     * Delete student from student name and return deleted student
     * @param name student name
     * @return Student
     */
    @Transactional(readOnly = false)
    public Student deleteByName(String name) {
        Optional<Student> optional = Optional.ofNullable(studentRepository.findByName(name));
        if (!optional.isPresent())
            throw new AbsentEntityException(Student.class,0);
        studentRepository.deleteByName(name);
        return optional.get();
    }

    /**
     * Update student and return updated student
     * @param object StudentDTO
     * @return Student
     */
    @Override
    @Transactional(readOnly = false)
    public Student update(Student object) {
        if (!studentRepository.isIdExists(object.getId()))
            throw new AbsentEntityException(Student.class,object.getId());
        Student student=verify(object);
        return studentRepository.save(student);
    }

    /**
     * Group students by address
     * @return Address-Count
     */
    @Transactional(readOnly = true)
    public List<Pair<String,Long>> groupByAddress(){
        return studentRepository.groupByAddress();
    }

    /**
     * Group student by gender
     * @return Gender-Count
     */
    @Transactional(readOnly = true)
    public List<Pair<String,Long>> groupByGender(){
        return studentRepository.groupByGender();
    }

    private Student verify(Student object) {
        int age = Period.between(object.getBirthDate(), LocalDate.now()).getYears();
        if (!(MINIMUM_AGE<=age && age<=MAXIMUM_AGE))
            throw new StudentAgeNotValidException(object);
        for (Course course:object.getCourses()){
            if (!course.getStudents().contains(object) && course.getStudents().size()>19)
                throw new StudentNumberForOneCourseExceededException(course);
            if (course.getStudents().contains(object) && course.getStudents().size()>20)
                throw new StudentNumberForOneCourseExceededException(course);
        }
        return object;
    }

}
