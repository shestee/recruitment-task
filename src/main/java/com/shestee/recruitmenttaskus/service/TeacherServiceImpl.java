package com.shestee.recruitmenttaskus.service;

import com.shestee.recruitmenttaskus.model.Teacher;
import com.shestee.recruitmenttaskus.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    TeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }
    
    @Override
    public List<Teacher> getAllTeachers(int pageNumber, Sort.Direction sort) {
        return teacherRepository.findAllTeachers(PageRequest.of(pageNumber, 10, Sort.by(sort, "id")));
    }

    @Override
    public Teacher saveTeacher(Teacher teacher) {
        return null;
    }

    @Transactional
    @Override
    public Teacher editTeacher(Teacher teacher) {
        Teacher teacherToEdit = teacherRepository.findById(teacher.getId()).orElseThrow();
        teacherToEdit.setFirstName(teacher.getFirstName());
        teacherToEdit.setLastName(teacher.getLastName());
        teacherToEdit.setAge(teacher.getAge());
        teacherToEdit.setEmail(teacher.getEmail());
        teacherToEdit.setSubject(teacher.getSubject());
        teacher.getStudents().forEach(teacherToEdit::addStudent);

        return teacherToEdit;
    }

    @Override
    public void removeTeacherById(long id) {
        teacherRepository.deleteById(id);
    }


    @Override
    public Teacher findTeacherByFirstNameAndLastName(String firstName, String lastName) {
        return teacherRepository.findByFirstNameAndLastName(firstName, lastName);
    }
}
