package com.schedufy.user.schedufy;

import android.text.format.Time;

import java.util.Date;

public class Event {

    private String _category;
    private Date _date;
    private Time _time;
    private String _description;

    public Event() {}

    public Event(String category,
                 Date date,
                 Time time,
                 String description) {
        this._category = category;
        this._date = date;
        this._time = time;
        this._description = description;
    }

    public String getCategory() {
        return this._category;
    }

    public void setCategory(String category) {
        this._category = category;
    }

    public Date getDate() {
        return this._date;
    }

    public void setDate(Date date) {
        this._date = date;
    }

    public Time getTime() {
        return this._time;
    }

    public void setTime(Time time) {
        this._time = time;
    }

    public String getDescription() {
        return this._description;
    }

    public void setDescription(String description) {
        this._description = description;
    }
}

