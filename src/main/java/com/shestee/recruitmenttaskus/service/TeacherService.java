package com.shestee.recruitmenttaskus.service;

import com.shestee.recruitmenttaskus.exception.PersonExistsException;
import com.shestee.recruitmenttaskus.exception.PersonNotFoundException;
import com.shestee.recruitmenttaskus.model.Teacher;
import com.shestee.recruitmenttaskus.model.dto.TeacherDto;
import org.springframework.data.domain.Sort;
import java.util.List;

public interface TeacherService {
    List<Teacher> getAllTeachers(int pageNumber, Sort.Direction sortDirection);

    List<TeacherDto> getAllTeachersWithoutStudents(int pageNumber, Sort.Direction sortDirection);

    Teacher saveTeacher(Teacher teacher) throws PersonExistsException;

    Teacher editTeacher(long id, Teacher teacher) throws PersonNotFoundException;

    void removeTeacher(long id) throws PersonNotFoundException;

    Teacher addStudentById(long teacherId, long studentId) throws PersonNotFoundException;

    List<Teacher> findTeacherByFirstNameAndLastName(String firstName, String lastName) throws PersonNotFoundException;
}
