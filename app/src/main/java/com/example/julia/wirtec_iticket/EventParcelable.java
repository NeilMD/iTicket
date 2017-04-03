package com.example.julia.wirtec_iticket;

/**
 * Created by shinichi on 4/3/17.
 */

public class EventParcelable extends Event {


    public EventParcelable( Event e) {

        setTime(e.getTime());
        setChecker(e.getChecker());
        setCode(e.getCode());
        setChecker(e.getChecker());
        setDate(e.getDate());
        setEventdesc(e.getEventdesc());
        setEventname(e.getEventname());
        setLimitToOne(e.getLimitToOne());
        setNumberOfTickets(e.getNumberOfTickets());

    }

    public EventParcelable(String code, String eventname, String eventdesc, Long date, Long time, Long numberOfTickets, Boolean limitToOne, String place, String checker) {
        super(code, eventname, eventdesc, date, time, numberOfTickets, limitToOne, place, checker);
    }
}
