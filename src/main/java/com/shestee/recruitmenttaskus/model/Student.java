package com.shestee.recruitmenttaskus.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Student extends Person {
    @NotNull
    private String fieldOfStudy;

    @JsonIgnoreProperties("students")
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(name = "student_teacher",
            joinColumns = { @JoinColumn(name = "student_id") },
            inverseJoinColumns = { @JoinColumn(name = "teacher_id") })
    private Set<Teacher> teachers;

    public Student(String firstName, String lastName, Integer age, String email, String fieldOfStudy) {
        super(firstName, lastName, age, email);
        this.fieldOfStudy = fieldOfStudy;
        teachers = new HashSet<>();
    }

    public Student() {
        teachers = new HashSet<>();
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void addTeacher(Teacher teacher) {
        if (teachers == null) {
            teachers = new HashSet<>();
        }
        teachers.add(teacher);
    }

    @Override
    public String toString() {
        return "Student{" +
                super.toString() +
                ", fieldOfStudy='" + fieldOfStudy + '\'' +
                ", teachers size=" + teachers.size() +
                '}';
    }
}
