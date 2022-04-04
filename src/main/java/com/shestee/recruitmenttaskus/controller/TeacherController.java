package com.shestee.recruitmenttaskus.controller;

import com.shestee.recruitmenttaskus.exception.PersonExistsException;
import com.shestee.recruitmenttaskus.exception.PersonNotFoundException;
import com.shestee.recruitmenttaskus.model.Teacher;
import com.shestee.recruitmenttaskus.model.dto.TeacherDto;
import com.shestee.recruitmenttaskus.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
public class TeacherController {
    TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/teachers")
    public List<Teacher> findAllTeachers(@RequestParam(required = false) Integer page, Sort.Direction sort) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        return teacherService.getAllTeachers(pageNumber, sortDirection);
    }

    @GetMapping(value = "/teachers", params = "noStudents")
    public List<TeacherDto> findAllTeachersWithoutStudents(@RequestParam(required = false, defaultValue = "true") boolean noStudents,
                                                           @RequestParam(required = false) Integer page, Sort.Direction sort) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;

        return teacherService.getAllTeachersWithoutStudents(pageNumber, sortDirection);
    }

    @GetMapping(value = "/teachers", params = {"firstname", "lastname"})
    public List<Teacher> findTeacherByFirstAndLastName(@RequestParam String firstname,
                                                       @RequestParam String lastname
    ) throws PersonNotFoundException {
        return teacherService.findTeacherByFirstNameAndLastName(firstname, lastname);
    }

    @PostMapping("/teachers")
    public Teacher addTeacher(@RequestBody @Valid Teacher teacher) throws PersonExistsException {
        return teacherService.saveTeacher(teacher);
    }

    @PutMapping(value = "/teachers", params = "id")
    public Teacher updateTeacher(@RequestParam long id, @RequestBody @Valid Teacher teacher) throws PersonNotFoundException {
        return teacherService.editTeacher(id, teacher);
    }

    @DeleteMapping(value = "/teachers", params = "id")
    public void deleteTeacherById(@RequestParam long id) {
        teacherService.removeTeacher(id);
    }
}
