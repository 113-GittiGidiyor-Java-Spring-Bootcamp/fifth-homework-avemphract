package dev.patika.fifthhomework.repository;

import dev.patika.fifthhomework.model.Student;
import javafx.util.Pair;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student,Integer> {

    @Query("DELETE FROM Student AS s WHERE s.name= ?1")
    @Modifying
    void deleteByName(String name);

    @Query("SELECT s FROM Student AS s WHERE s.name= ?1")
    Student findByName(String name);

    @Query("SELECT new javafx.util.Pair(s.address ,COUNT(s)) FROM Student AS s GROUP BY s.address")
    List<Pair<String,Long>> groupByAddress();

    @Query("SELECT new javafx.util.Pair(s.address ,COUNT(s)) FROM Student AS s GROUP BY s.gender")
    List<Pair<String,Long>> groupByGender();

    @Query("SELECT " +
            "CASE WHEN COUNT(s)>0 THEN TRUE ELSE FALSE END " +
            "FROM Student AS s " +
            "WHERE s.id = ?1")
    boolean isIdExists(int id);

}
