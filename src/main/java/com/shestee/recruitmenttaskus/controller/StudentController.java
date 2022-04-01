package com.shestee.recruitmenttaskus.controller;

import com.shestee.recruitmenttaskus.model.Student;
import com.shestee.recruitmenttaskus.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> findAllStudents(@RequestParam(required = false) Integer page, Sort.Direction sort) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        return studentService.getAllStudents(pageNumber, sortDirection);
    }

    @GetMapping("/students/{firstName}/{lastName}")
    public List<Student> findStudentByFirsAndLastName(@PathVariable String firstName,
                                                      @PathVariable String lastName
    ) {
        return studentService.findStudentByFirstNameAndLastName(firstName, lastName);
    }

    @PostMapping("students")
    public Student addStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @PutMapping("/students")
    public Student updateStudent(@RequestBody Student student) {
        return studentService.editStudent(student);
    }

    @DeleteMapping("students/{id}")
    public void deleteStudentById(@PathVariable long id) {
        studentService.removeStudent(id);
    }

}
