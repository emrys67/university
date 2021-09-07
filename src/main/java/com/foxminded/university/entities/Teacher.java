package com.foxminded.university.entities;

import java.sql.Date;
import java.util.Objects;

public class Teacher {
    private long id;
    private Vacation vacation;
    private TimePeriod workingHours;
    private String firstname;
    private String lastname;
    private Date dateOfBirth;
    private String gender;

    public Teacher() {
    }

    public Teacher(Vacation vacation) {
        this.vacation = vacation;
    }

    public Teacher(Vacation vacation, TimePeriod workingHours, String firstname, String lastname, Date dateOfBirth, String gender) {
        this.vacation = vacation;
        this.workingHours = workingHours;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public Teacher(Vacation vacation, TimePeriod workingHours, String firstname, String lastname, Date dateOfBirth, String gender, long id) {
        this.vacation = vacation;
        this.workingHours = workingHours;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.id = id;
    }

    public Vacation getVacation() {
        return vacation;
    }

    public void setVacation(Vacation vacation) {
        this.vacation = vacation;
    }

    public TimePeriod getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(TimePeriod workingHours) {
        this.workingHours = workingHours;
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
        Teacher teacher = (Teacher) o;
        return id == teacher.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", vacation=" + vacation +
                ", workingHours=" + workingHours +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                '}';
    }
}
