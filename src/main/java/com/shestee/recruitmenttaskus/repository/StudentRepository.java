package com.shestee.recruitmenttaskus.repository;

import com.shestee.recruitmenttaskus.model.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT s FROM Student s")
    List<Student> findAllStudents(Pageable page);

    List<Student> findByFirstNameAndLastName(String firstName, String lastName);
}
