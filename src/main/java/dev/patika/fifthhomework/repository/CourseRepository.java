package dev.patika.fourthhomeworkavemphract.repository;

import dev.patika.fourthhomeworkavemphract.model.Course;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course,Integer> {

    @Query("SELECT c FROM Course AS c WHERE c.courseName= ?1")
    Course getByName(String name);

    @Query("SELECT " +
            "CASE WHEN COUNT(c)>0 THEN TRUE ELSE FALSE END " +
            "FROM Course AS c " +
            "WHERE c.id = ?1")
    boolean isIdExists(int id);

    @Query("SELECT " +
            "CASE WHEN COUNT(c)>0 THEN TRUE ELSE FALSE END " +
            "FROM Course AS c " +
            "WHERE c.courseCode = ?1")
    boolean isCodeExists(String code);

    @Query("SELECT c FROM Course AS c WHERE c.courseCode= ?1")
    Course findByCourseCode(String courseCode);
}
