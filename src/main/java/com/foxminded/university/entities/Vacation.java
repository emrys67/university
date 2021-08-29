package com.foxminded.university.entities;

public class Vacation {
    private TimePeriod timePeriod;
    private String description;
    private long id;

    public Vacation() {
    }

    public Vacation(TimePeriod timePeriod, String description, long id) {
        this.timePeriod = timePeriod;
        this.description = description;
        this.id = id;
    }

    public Vacation(TimePeriod timePeriod, String description) {
        this.timePeriod = timePeriod;
        this.description = description;
    }

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(TimePeriod timePeriod) {
        this.timePeriod = timePeriod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
