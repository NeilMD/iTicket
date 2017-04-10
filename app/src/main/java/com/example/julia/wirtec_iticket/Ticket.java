package com.example.julia.wirtec_iticket;

/**
 * Created by shinichi on 4/3/17.
 */

public class Ticket {
    private String code;
    private String eventname;
    private String eventdesc;
    private Long date;
    private Long time;
    private String place;
    private String status;
    private String orderInfo;
    private String checker;

    public Ticket() {

    }

    public Ticket(String code, String eventname, String eventdesc, Long date, Long time, String place, String status, String orderInfo, String checker) {
        this.code = code;
        this.eventname = eventname;
        this.eventdesc = eventdesc;
        this.date = date;
        this.time = time;
        this.place = place;
        this.status = status;
        this.orderInfo = orderInfo;
        this.checker = checker;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getEventdesc() {
        return eventdesc;
    }

    public void setEventdesc(String eventdesc) {
        this.eventdesc = eventdesc;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }
}
