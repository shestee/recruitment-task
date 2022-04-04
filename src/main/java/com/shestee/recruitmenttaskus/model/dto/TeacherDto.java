package com.shestee.recruitmenttaskus.model.dto;

public class TeacherDto {
    private long id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String subject;

    public TeacherDto(long id, String firstName, String lastName, int age, String email, String subject) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.subject = subject;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getSubject() {
        return subject;
    }
}
