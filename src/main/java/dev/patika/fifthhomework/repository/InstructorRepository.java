package dev.patika.fifthhomework.repository;


import dev.patika.fifthhomework.model.GuestInstructor;
import dev.patika.fifthhomework.model.Instructor;
import dev.patika.fifthhomework.model.RegularInstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructorRepository extends CrudRepository<Instructor,Integer> {

    @Query("DELETE FROM Instructor AS i WHERE i.name= ?1")
    @Modifying
    void deleteByName(String name);

    @Query("SELECT i FROM Instructor AS i WHERE i.name= ?1")
    Instructor findByName(String name);

    @Query("SELECT i FROM RegularInstructor AS i")
    List<RegularInstructor> getAllRegularInstructor();

    @Query("SELECT i FROM GuestInstructor AS i")
    List<GuestInstructor> getAllGuestInstructor();

    @Query("SELECT COUNT(i) FROM RegularInstructor AS i")
    Long getRegularInstructorCount();

    @Query("SELECT COUNT(i) FROM GuestInstructor AS i")
    Long getGuestInstructorCount();

    @Query("SELECT i FROM RegularInstructor AS i ORDER BY i.constantSalary DESC ")
    List<RegularInstructor> getRegularInstructorsSortFromSalary();

    @Query("SELECT i FROM GuestInstructor AS i ORDER BY i.hourlySalary DESC ")
    List<GuestInstructor> getGuestInstructorsSortFromSalary();


    @Query("SELECT " +
            "CASE WHEN COUNT(i)>0 THEN TRUE ELSE FALSE END " +
            "FROM Instructor AS i " +
            "WHERE i.id = ?1")
    boolean isIdExists(int id);

    @Query("SELECT " +
            "CASE WHEN COUNT(i)>0 THEN TRUE ELSE FALSE END " +
            "FROM Instructor AS i " +
            "WHERE i.phoneNumber = ?1")
    boolean isPhoneNumberExists(long phoneNumber);

    @Query("SELECT i FROM Instructor AS i WHERE i.phoneNumber=?1")
    Instructor findByPhoneNumber(long phoneNumber);
}
