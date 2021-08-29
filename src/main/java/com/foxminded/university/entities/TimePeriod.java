package com.foxminded.university.entities;


import java.sql.Date;

public class TimePeriod {
    private Date startTime;
    private Date endTime;
    private long id;

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
}
