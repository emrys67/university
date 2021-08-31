package com.foxminded.university.entities;

import java.util.Objects;

public class Vacation {
    private long id;
    private TimePeriod timePeriod;
    private String description;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacation vacation = (Vacation) o;
        return id == vacation.id && timePeriod.equals(vacation.timePeriod) && description.equals(vacation.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timePeriod, description);
    }

    @Override
    public String toString() {
        return "Vacation{" +
                "id=" + id +
                ", timePeriod=" + timePeriod +
                ", description='" + description + '\'' +
                '}';
    }
}
