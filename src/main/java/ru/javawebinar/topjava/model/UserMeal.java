package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;
import java.time.LocalTime;

import ru.javawebinar.topjava.util.TimeUtil;


/**
 * GKislin
 * 11.01.2015.
 */
public class UserMeal {

    protected Integer id;

    protected final LocalDateTime dateTime;

    protected final String description;

    protected final int calories;

    public UserMeal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public UserMeal(Integer id, LocalDateTime dateTime, String description, int calories) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNew(){
        return id ==null;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public boolean isBetween(LocalTime startTime, LocalTime endTime){
        return TimeUtil.isBetween(LocalTime.from(this.dateTime), startTime, endTime);
    }

    @Override
    public String toString(){
        return "UserMeal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
