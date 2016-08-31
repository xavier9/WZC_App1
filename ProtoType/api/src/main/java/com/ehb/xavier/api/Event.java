package com.ehb.xavier.api;


import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.Date;

/**
 * Created by xavier on 14/02/2015.
 */
@Entity
public class Event{
    @Id
    private Long id;
    private String name;
    private int startYear;
    private int startMonth;
    private int startDag, startHour, startMinute, endYear;
    private int endMonth, endDay, endHour, endMinute;
    private Date startTime, endTime;
    private Long UserID;
    private int ids;

    public Event() {

    }

    public Event(long id, String name, int startYear, int startMonth,
                 int startDay, int startHour, int startMinute, int endYear,
                 int endMonth, int endDay, int endHour, int endMinute) {
        this.id = id;
        this.name = name;
        this.startYear = startYear;
        this.startMonth = startMonth;
        this.startDag = startDay;
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.endYear = endYear;
        this.endMonth = endMonth;
        this.endDay = endDay;
        this.endHour = endHour;
        this.endMinute = endMinute;

    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startYear=" + startYear +
                ", startMonth=" + startMonth +
                ", startDag=" + startDag +
                ", startHour=" + startHour +
                ", startMinute=" + startMinute +
                ", endYear=" + endYear +
                ", endMonth=" + endMonth +
                ", endDay=" + endDay +
                ", endHour=" + endHour +
                ", endMinute=" + endMinute +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    public Event(long id, String name, Date startTime, Date endTime) {
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;

     }

    public Long getID() {
        return id;
    }

    public int getStartMinute() {
        return startMinute;
    }

    public void setStartMinute(int startMinute) {
        this.startMinute = startMinute;
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



    public void setId(Long id) {
        this.id = id;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(int startMonth) {
        this.startMonth = startMonth;
    }

    public int getStartDag() {
        return startDag;
    }

    public void setStartDag(int startDag) {
        this.startDag = startDag;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getGetStartMonth() {
        return startMinute;
    }

    public void setGetStartMonth(int getStartMonth) {
        this.startMinute = getStartMonth;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public int getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(int endMonth) {
        this.endMonth = endMonth;
    }

    public int getEndDay() {
        return endDay;
    }

    public void setEndDay(int endDay) {
        this.endDay = endDay;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public int getEndMinute() {
        return endMinute;
    }

    public void setEndMinute(int endMinute) {
        this.endMinute = endMinute;
    }



    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public Long getUserID() {
        return UserID;
    }

    public void setUserID(Long userID) {
        UserID = userID;
    }

    public int getIds() {
        return ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }
}
