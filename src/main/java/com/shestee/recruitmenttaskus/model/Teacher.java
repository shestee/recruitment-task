package com.shestee.recruitmenttaskus.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Teacher extends Person {
    @NotNull
    private String subject;

    @JsonIgnoreProperties("teachers")
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(name = "student_teacher",
            joinColumns = { @JoinColumn(name = "teacher_id") },
            inverseJoinColumns = { @JoinColumn(name = "student_id") })
    private Set<Student> students;

    public Teacher(String firstName, String lastName, Integer age, String email, String subject) {
        super(firstName, lastName, age, email);
        this.subject = subject;
        students = new HashSet<>();
    }

    public Teacher() {
        students = new HashSet<>();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student) {
        if (students == null) {
            students = new HashSet<>();
        }
        students.add(student);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                super.toString() +
                ", subject='" + subject + '\'' +
                ", students size=" + students.size() +
                '}';
    }
}
