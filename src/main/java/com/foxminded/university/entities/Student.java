package com.foxminded.university.entities;

import java.sql.Date;

public class Student {
    private String firstname;
    private String lastname;
    private int studyYear;
    private Date dateOfBirth;
    private String gender;
    private long id;

    public Student() {
    }

    public Student(String firstname, String lastname, int studyYear, Date dateOfBirth, String gender) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.studyYear = studyYear;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public Student(String firstname, String lastname, int studyYear, Date dateOfBirth, String gender, long id) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.studyYear = studyYear;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(int studyYear) {
        this.studyYear = studyYear;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
