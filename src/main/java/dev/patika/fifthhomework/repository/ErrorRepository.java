package dev.patika.fourthhomeworkavemphract.repository;

import dev.patika.fourthhomeworkavemphract.exception.ErrorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ErrorRepository extends CrudRepository<ErrorEntity,Integer> {

    @Query("SELECT e FROM ErrorEntity AS e WHERE e.errorCode=?1")
    List<ErrorEntity> findByErrorCode(int errorCode);

    @Query("SELECT e FROM ErrorEntity AS e WHERE (?1<=e.createDate AND e.createDate<=?2)")
    List<ErrorEntity> findByDateBetween(Instant begin, Instant end);

}
