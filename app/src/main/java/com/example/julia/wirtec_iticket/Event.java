package com.example.julia.wirtec_iticket;

/**
 * Created by shinichi on 4/2/17.
 */

public class Event {
    private String code;
    private String eventname;
    private String eventdesc;
    private Long date;
    private Long time;
    private Long numberOfTickets;
    private Boolean limitToOne;
    private String place;
    private String checker;

    public Event() {
    }

    public Event(String code, String eventname, String eventdesc, Long date, Long time, Long numberOfTickets, Boolean limitToOne, String place, String checker) {
        this.code = code;
        this.eventname = eventname;
        this.eventdesc = eventdesc;
        this.date = date;
        this.time = time;
        this.numberOfTickets = numberOfTickets;
        this.limitToOne = limitToOne;
        this.place = place;
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


    public long getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(long numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public Boolean getLimitToOne() {
        return limitToOne;
    }

    public void setLimitToOne(Boolean limitToOne) {
        this.limitToOne = limitToOne;
    }
}
