package com.shakhee.contentproviderexample;

public class CallLogModel {
    public String getName() {
        return name;
    }

    public CallLogModel(String name, String numbers, String duration, String date) {
        this.name = name;
        this.numbers = numbers;
        this.duration = duration;
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String name;
    public String numbers;
    public String duration;
    public String date;

}

