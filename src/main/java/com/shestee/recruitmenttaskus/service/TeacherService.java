package com.shestee.recruitmenttaskus.service;

import com.shestee.recruitmenttaskus.model.Teacher;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface TeacherService {
    List<Teacher> getAllTeachers(int pageNumber, Sort.Direction sortDirection);
    Teacher saveTeacher(Teacher teacher);
    Teacher editTeacher(Teacher teacher);
    void removeTeacherById(long id);
    Teacher findTeacherByFirstNameAndLastName(String firstName, String lastName);
}
