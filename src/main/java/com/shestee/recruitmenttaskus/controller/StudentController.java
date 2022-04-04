package com.shestee.recruitmenttaskus.controller;

import com.shestee.recruitmenttaskus.exception.PersonExistsException;
import com.shestee.recruitmenttaskus.exception.PersonNotFoundException;
import com.shestee.recruitmenttaskus.model.Student;
import com.shestee.recruitmenttaskus.model.dto.StudentDto;
import com.shestee.recruitmenttaskus.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
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

    @GetMapping(value = "/students", params = "noTeachers")
    public List<StudentDto> findAllStudentsWithoutTeachers(@RequestParam(required = false, defaultValue = "true") boolean noTeachers,
                                                           @RequestParam(required = false) Integer page, Sort.Direction sort) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;

        return studentService.getAllStudentsWithoutTeachers(pageNumber, sortDirection);
    }

    @GetMapping(value = "/students", params = {"firstname", "lastname"})
    public List<Student> findStudentsByFirstAndLastName(@RequestParam String firstname,
                                                        @RequestParam String lastname
    ) throws PersonNotFoundException {
        return studentService.findStudentByFirstNameAndLastName(firstname, lastname);
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody @Valid Student student) throws PersonExistsException {
        return studentService.saveStudent(student);
    }

    @PutMapping(value = "/students", params = "id")
    public Student updateStudent(@RequestParam long id, @Valid @RequestBody Student student) throws PersonNotFoundException {
        return studentService.editStudent(id, student);
    }

    @DeleteMapping(value = "/students", params = "id")
    public void deleteStudentById(@RequestParam long id) {
        studentService.removeStudent(id);
    }
}
