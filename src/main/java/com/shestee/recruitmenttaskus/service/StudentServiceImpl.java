package com.shestee.recruitmenttaskus.service;

import com.shestee.recruitmenttaskus.model.Student;
import com.shestee.recruitmenttaskus.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents(int pageNumber, Sort.Direction sort) {
        return studentRepository.findAllStudents(PageRequest.of(pageNumber, 10, Sort.by(sort, "id")));
    }

    @Transactional
    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Transactional
    @Override
    public Student editStudent(Student student) {
        Student studentToEdit = studentRepository.findById(student.getId()).orElseThrow();
        studentToEdit.setFirstName(student.getFirstName());
        studentToEdit.setLastName(student.getLastName());
        studentToEdit.setAge(student.getAge());
        studentToEdit.setEmail(student.getEmail());
        studentToEdit.setFieldOfStudy(student.getFieldOfStudy());
        student.getTeachers().forEach(studentToEdit::addTeacher);

        return studentToEdit;
    }

    @Override
    public void removeStudent(long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> findStudentByFirstNameAndLastName(String firstName, String lastName) {
        return studentRepository.findByFirstNameAndLastName(firstName, lastName);
    }
}
