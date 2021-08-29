package com.foxminded.university.entities;

public class Classroom {
    private int capacity;
    private long id;

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
}
