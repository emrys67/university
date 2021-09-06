package com.foxminded.university.entities;

import java.util.Objects;

public class Classroom {
    private long id;
    private int capacity;

    public Classroom() {
    }

    public Classroom(int capacity) {
        this.capacity = capacity;
    }

    public Classroom(int capacity, long id) {
        this.capacity = capacity;
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
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
        Classroom classroom = (Classroom) o;
        return id == classroom.id && capacity == classroom.capacity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, capacity);
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "id=" + id +
                ", capacity=" + capacity +
                '}';
    }
}
