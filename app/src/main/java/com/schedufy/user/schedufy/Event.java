package com.schedufy.user.schedufy;

public class Event {

    private String _category;
    private String _date;
    private String _time;
    private String _description;

    public Event() {}

    public Event(String category,
                 String date,
                 String time,
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

    public String getDate() {
        return this._date;
    }

    public void setDate(String date) {
        this._date = date;
    }

    public String getTime() {
        return this._time;
    }

    public void setTime(String time) {
        this._time = time;
    }

    public String getDescription() {
        return this._description;
    }

    public void setDescription(String description) {
        this._description = description;
    }
}

