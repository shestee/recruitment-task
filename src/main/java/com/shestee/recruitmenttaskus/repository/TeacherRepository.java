package com.shestee.recruitmenttaskus.repository;

import com.shestee.recruitmenttaskus.model.Teacher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    @Query("SELECT t FROM Teacher t")
    List<Teacher> findAllTeachers(Pageable page);

    Teacher findByFirstNameAndLastName(String firstName, String lastName);
}
