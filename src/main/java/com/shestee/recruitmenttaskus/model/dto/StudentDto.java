package com.shestee.recruitmenttaskus.model.dto;

public class StudentDto {
    private long id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String fieldOfStudy;

    public StudentDto(long id, String firstName, String lastName, int age, String email, String fieldOfStudy) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.fieldOfStudy = fieldOfStudy;
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

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }
}
