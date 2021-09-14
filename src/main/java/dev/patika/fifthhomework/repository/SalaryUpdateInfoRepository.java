package dev.patika.fifthhomework.repository;

import dev.patika.fifthhomework.model.SalaryUpdateLog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaryUpdateInfoRepository extends CrudRepository<SalaryUpdateLog,Integer> {

    @Query("SELECT s FROM SalaryUpdateLog AS s WHERE s.instructor.id = ?1")
    List<SalaryUpdateLog> findByInstructorId(int id);
}
