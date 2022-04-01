package com.shestee.recruitmenttaskus.controller;

import com.shestee.recruitmenttaskus.model.Teacher;
import com.shestee.recruitmenttaskus.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/teachers/{firstName}/{lastName}")
    public Teacher findTeacherByFirstAndLastName(@PathVariable("firstName") String firstName,
                                                 @PathVariable("lastName") String lastName) {
        return teacherService.findTeacherByFirstNameAndLastName(firstName, lastName);
    }

    @PostMapping("/teachers")
    public Teacher addTeacher(@RequestBody Teacher teacher) {
        return teacherService.saveTeacher(teacher);
    }

    @PutMapping("/teachers")
    public Teacher updateTeacher(Teacher teacher) {
        return teacherService.editTeacher(teacher);
    }

    @DeleteMapping("teachers/{id}")
    public void deleteTeacherById(@PathVariable long id) {
        teacherService.removeTeacherById(id);
    }
}
