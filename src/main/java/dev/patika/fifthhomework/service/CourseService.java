package dev.patika.fifthhomework.service;

import dev.patika.fifthhomework.exception.AbsentEntityException;
import dev.patika.fifthhomework.exception.CourseIsAlreadyExistException;
import dev.patika.fifthhomework.exception.StudentNumberForOneCourseExceededException;
import dev.patika.fifthhomework.mapper.CourseMapper;
import dev.patika.fifthhomework.model.Course;
import dev.patika.fifthhomework.model.Student;
import dev.patika.fifthhomework.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements BaseService<Course> {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseMapper courseMapper;


    /**
     * Get all courses in database
     * @return List of Courses
     */
    @Override @Transactional(readOnly = true)
    public List<Course> findAll() {
        List<Course> list=new ArrayList<>();
        courseRepository.findAll().forEach(n->list.add(n));
        return list;
    }

    /**
     * Get course from id
     * @param id requested id
     * @return Course
     */
    @Override @Transactional(readOnly = true)
    public Course findById(int id) {
        Optional<Course> optionalCourse=courseRepository.findById(id);
        return optionalCourse.orElse(null);
    }

    /**
     * Get course form courseCode
     * @param courseCode requested courseCode
     * @return Course
     */
    public Course findByCode(String courseCode){
        Optional<Course> optionalCourse=Optional.ofNullable(courseRepository.findByCourseCode(courseCode));
        if (!optionalCourse.isPresent())
            throw new AbsentEntityException(Course.class,0);
        return optionalCourse.get();
    }

    /**
     * Save course and return this course
     * @param course Course
     * @return Course
     */
    @Override @Transactional(readOnly = false)
    public Course save(Course course) {
        if (courseRepository.isCodeExists(course.getCourseCode()))
            throw new CourseIsAlreadyExistException(course);
        if (course.getStudents().size()>20)
            throw new StudentNumberForOneCourseExceededException(course);
        return courseRepository.save(course);
    }

    /**
     * Delete course from id and return deleted course
     * @param id course id
     * @return Course
     */
    @Override @Transactional(readOnly = false)
    public Course deleteById(int id) {
        Optional<Course> optional=courseRepository.findById(id);
        if (!optional.isPresent())
            throw new AbsentEntityException(Course.class,id);
        deleting(optional.get());
        courseRepository.delete(optional.get());
        return optional.get();
    }

    /**
     * Delete course from course code and return deleted course
     * @param courseCode courseCode
     * @return Course
     */
    @Transactional(readOnly = false)
    public Course deleteByCode(String courseCode){
        Course course = courseRepository.findByCourseCode(courseCode);
        if (course==null)
            throw new AbsentEntityException(Course.class,0);
        deleting(course);
        courseRepository.delete(course);
        return course;
    }

    private void deleting(Course course){
        for (Student student:course.getStudents()){
            student.getCourses().remove(course);
        }
        course.getStudents().clear();
    }

    /**
     * Update course and return
     * @param object CourseDTO
     * @return Course
     */
    @Override @Transactional(readOnly = false)
    public Course update(Course object) {
        if (!courseRepository.isIdExists(object.getId()))
            throw new AbsentEntityException(Course.class,object.getId());
        Course sameCode=courseRepository.findByCourseCode(object.getCourseCode());
        if (sameCode!=null && !sameCode.getId().equals(object.getId()))
            throw new CourseIsAlreadyExistException(sameCode);
        return courseRepository.save(object);
    }

}
