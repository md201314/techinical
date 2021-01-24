package com.architect.techinical.controller.main.model;

import com.architect.techinical.common.util.UdfDateTime;

public class AttendanceDatetime {
    private String startTime;
    private String endTime;

    public AttendanceDatetime() {
        Long currentTime = System.currentTimeMillis();
        Long monthAgo = currentTime - 2592000000L*3;
        this.startTime = UdfDateTime.timestamp2DateTimeWithSecond(monthAgo);
        this.endTime = UdfDateTime.timestamp2DateTimeWithSecond(currentTime);
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /*private Long startTime = System.currentTimeMillis() - 2592000000L*6;
    private Long endTime = System.currentTimeMillis();

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }*/
}
