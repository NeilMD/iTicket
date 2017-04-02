package com.example.julia.wirtec_iticket;

/**
 * Created by shinichi on 4/2/17.
 */

public class Event {
    private String code;
    private String eventname;
    private String eventdesc;
    private long date;
    private long time;
    private long numberOfTickets;
    private String place;
    private String status;
    private String checker;

    public Event() {
    }

    public Event(String code, String eventname, String eventdesc, long date, long time, long numberOfTickets, String place, String status, String checker) {
        this.code = code;
        this.eventname = eventname;
        this.eventdesc = eventdesc;
        this.date = date;
        this.time = time;
        this.numberOfTickets = numberOfTickets;
        this.place = place;
        this.status = status;
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

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(long numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }
}
