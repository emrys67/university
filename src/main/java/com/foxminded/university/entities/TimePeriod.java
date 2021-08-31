package com.foxminded.university.entities;


import java.sql.Date;
import java.util.Objects;

public class TimePeriod {
    private long id;
    private Date startTime;
    private Date endTime;

    public TimePeriod(Date startTime) {
        this.startTime = startTime;
    }

    public TimePeriod(Date startTime, Date endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public TimePeriod(Date startTime, Date endTime, long id) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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
        TimePeriod that = (TimePeriod) o;
        return id == that.id && startTime.equals(that.startTime) && endTime.equals(that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startTime, endTime);
    }

    @Override
    public String toString() {
        return "TimePeriod{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
