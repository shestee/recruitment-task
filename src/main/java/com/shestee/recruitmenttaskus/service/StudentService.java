package com.shestee.recruitmenttaskus.service;

import com.shestee.recruitmenttaskus.model.Student;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents(int pageNumber, Sort.Direction sortDirection);
    Student saveStudent(Student student);
    Student editStudent(Student student);
    void removeStudent(long id);
    List<Student> findStudentByFirstNameAndLastName(String firstName, String lastName);
}
