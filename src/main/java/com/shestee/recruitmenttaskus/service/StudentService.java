package com.shestee.recruitmenttaskus.service;

import com.shestee.recruitmenttaskus.exception.PersonExistsException;
import com.shestee.recruitmenttaskus.exception.PersonNotFoundException;
import com.shestee.recruitmenttaskus.model.Student;
import com.shestee.recruitmenttaskus.model.dto.StudentDto;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents(int pageNumber, Sort.Direction sortDirection);

    List<StudentDto> getAllStudentsWithoutTeachers(int pageNumber, Sort.Direction sortDirection);

    Student saveStudent(Student student) throws PersonExistsException;

    Student editStudent(long id, Student student) throws PersonNotFoundException;

    void removeStudent(long id) throws PersonNotFoundException;

    Student addTeacherById(long studentId, long teacherId) throws PersonNotFoundException;

    List<Student> findStudentByFirstNameAndLastName(String firstName, String lastName) throws PersonNotFoundException;
}
