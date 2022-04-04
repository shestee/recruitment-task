package com.shestee.recruitmenttaskus.service;

import com.shestee.recruitmenttaskus.exception.PersonExistsException;
import com.shestee.recruitmenttaskus.exception.PersonNotFoundException;
import com.shestee.recruitmenttaskus.model.Student;
import com.shestee.recruitmenttaskus.model.Teacher;
import com.shestee.recruitmenttaskus.model.dto.StudentDto;
import com.shestee.recruitmenttaskus.repository.StudentRepository;
import com.shestee.recruitmenttaskus.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    StudentRepository studentRepository;
    TeacherRepository teacherRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Student> getAllStudents(int pageNumber, Sort.Direction sort) {
        return studentRepository.findAllStudents(PageRequest.of(pageNumber, 10, Sort.by(sort, "id")));
    }

    @Override
    public List<StudentDto> getAllStudentsWithoutTeachers(int pageNumber, Sort.Direction sort) {
        List<Student> students = studentRepository.findAllStudents(PageRequest.of(pageNumber, 10, Sort.by(sort, "id")));

        return mapToStudentDtos(students);
    }

    @Transactional
    @Override
    public Student saveStudent(Student student) throws PersonExistsException {
        Set<Teacher> teachersToSave = new HashSet<>();
        if (!studentRepository.existsByLastNameAndEmail(student.getLastName(), student.getEmail())) {
            for (Teacher teacherToSaveWithStudent: student.getTeachers()) {
                List<Teacher> teachers = teacherRepository.findByLastNameAndEmail(teacherToSaveWithStudent.getLastName(), teacherToSaveWithStudent.getEmail());
                if (teachers.size() != 0) {
                    teachersToSave.addAll(teachers);
                } else {
                    teachersToSave.add(teacherToSaveWithStudent);
                }
            }
            student.setTeachers(teachersToSave);

            return studentRepository.save(student);
        } else {
            throw new PersonExistsException("student already exists");
        }
    }

    @Transactional
    @Override
    public Student editStudent(long id, Student student) throws PersonNotFoundException {
        Student studentToEdit = studentRepository.findById(id)
                                                 .orElseThrow(PersonNotFoundException::new);
        studentToEdit.setFirstName(student.getFirstName());
        studentToEdit.setLastName(student.getLastName());
        studentToEdit.setAge(student.getAge());
        studentToEdit.setEmail(student.getEmail());
        studentToEdit.setFieldOfStudy(student.getFieldOfStudy());
        student.getTeachers().forEach(studentToEdit::addTeacher);

        return studentToEdit;
    }

    @Override
    public void removeStudent(long id) throws PersonNotFoundException {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
        } else {
            throw new PersonNotFoundException();
        }
    }

    @Transactional
    @Override
    public Student addTeacherById(long studentId, long teacherId) throws PersonNotFoundException {
        Student student = studentRepository.findById(studentId).orElseThrow(PersonNotFoundException::new);
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(PersonNotFoundException::new);

        student.addTeacher(teacher);

        return studentRepository.save(student);
    }

    @Override
    public List<Student> findStudentByFirstNameAndLastName(String firstName, String lastName) throws PersonNotFoundException {
        List<Student> students = studentRepository.findByFirstNameAndLastName(firstName, lastName);
        if (!students.isEmpty()) {
            return students;
        } else {
            throw new PersonNotFoundException();
        }
    }

    private List<StudentDto> mapToStudentDtos(List<Student> students) {
        return students.stream()
                       .map(this::mapToStudentDto)
                       .collect(Collectors.toList());
    }

    private StudentDto mapToStudentDto(Student student) {
        return new StudentDto(student.getId(),
                              student.getFirstName(),
                              student.getLastName(),
                              student.getAge(),
                              student.getEmail(),
                              student.getFieldOfStudy());
    }
}
