package com.shestee.recruitmenttaskus.service;

import com.shestee.recruitmenttaskus.exception.PersonExistsException;
import com.shestee.recruitmenttaskus.exception.PersonNotFoundException;
import com.shestee.recruitmenttaskus.model.Student;
import com.shestee.recruitmenttaskus.model.Teacher;
import com.shestee.recruitmenttaskus.model.dto.TeacherDto;
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
public class TeacherServiceImpl implements TeacherService {
    TeacherRepository teacherRepository;
    StudentRepository studentRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository, StudentRepository studentRepository) {
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }
    
    @Override
    public List<Teacher> getAllTeachers(int pageNumber, Sort.Direction sort) {
        return teacherRepository.findAllTeachers(PageRequest.of(pageNumber, 10, Sort.by(sort, "id")));
    }

    @Override
    public List<TeacherDto> getAllTeachersWithoutStudents(int pageNumber, Sort.Direction sort) {
        List<Teacher> teachers = teacherRepository.findAllTeachers(PageRequest.of(pageNumber, 10, Sort.by(sort, "id")));

        return mapToTeacherDtos(teachers);
    }

    @Transactional
    @Override
    public Teacher saveTeacher(Teacher teacher) throws PersonExistsException {
        Set<Student> studentsToSave = new HashSet<>();
        if (!teacherRepository.existsByLastNameAndEmail(teacher.getLastName(), teacher.getEmail())) {
            for (Student studentToSaveWithTeacher: teacher.getStudents()) {
                List<Student> students = studentRepository.findByLastNameAndEmail(studentToSaveWithTeacher.getLastName(),
                                                                                  studentToSaveWithTeacher.getEmail());
                if (students.size() != 0) {
                    studentsToSave.addAll(students);
                } else {
                    studentsToSave.add(studentToSaveWithTeacher);
                }
            }
            teacher.setStudents(studentsToSave);

            return teacherRepository.save(teacher);
        } else {
            throw new PersonExistsException("student already exists");
        }
    }

    @Transactional
    @Override
    public Teacher editTeacher(long id, Teacher teacher) throws PersonNotFoundException {
        Teacher teacherToEdit = teacherRepository.findById(id)
                                                 .orElseThrow(PersonNotFoundException::new);
        teacherToEdit.setFirstName(teacher.getFirstName());
        teacherToEdit.setLastName(teacher.getLastName());
        teacherToEdit.setAge(teacher.getAge());
        teacherToEdit.setEmail(teacher.getEmail());
        teacherToEdit.setSubject(teacher.getSubject());
        teacher.getStudents().forEach(teacherToEdit::addStudent);

        return teacherToEdit;
    }

    @Override
    public void removeTeacher(long id) throws PersonNotFoundException {
        if (teacherRepository.existsById(id)) {
            teacherRepository.deleteById(id);
        } else {
            throw new PersonNotFoundException();
        }
    }

    @Override
    public Teacher addStudentById(long teacherId, long studentId) throws PersonNotFoundException {
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(PersonNotFoundException::new);
        Student student = studentRepository.findById(studentId).orElseThrow(PersonNotFoundException::new);

        teacher.addStudent(student);

        return teacherRepository.save(teacher);
    }

    @Override
    public List<Teacher> findTeacherByFirstNameAndLastName(String firstName, String lastName) throws PersonNotFoundException {
        List<Teacher> teachers = teacherRepository.findByFirstNameAndLastName(firstName, lastName);
        if (teachers.isEmpty()) {
            return teachers;
        } else {
            throw new PersonNotFoundException();
        }
    }

    private List<TeacherDto> mapToTeacherDtos(List<Teacher> teachers) {
        return teachers.stream()
                .map(this::mapToTeacherDto)
                .collect(Collectors.toList());
    }

    private TeacherDto mapToTeacherDto(Teacher teacher) {
        return new TeacherDto(teacher.getId(),
                teacher.getFirstName(),
                teacher.getLastName(),
                teacher.getAge(),
                teacher.getEmail(),
                teacher.getSubject());
    }
}
