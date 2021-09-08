package com.foxminded.university.entities;

import java.sql.Date;
import java.util.Objects;

public class Student {
    private long id;
    private int studyYear;
    private String firstname;
    private String lastname;
    private String gender;
    private Date dateOfBirth;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", studyYear=" + studyYear +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                '}';
    }
}
