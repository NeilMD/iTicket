package com.example.julia.wirtec_iticket;

/**
 * Created by shinichi on 4/3/17.
 */

public class Request {
    private String uid;
    private String name;
    private String email;
    private String event;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    private String numberOfTicketRequested;

    public Request(){}

    public Request(String uid, String name, String email, String event, String numberOfTicketRequested) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.event = event;
        this.numberOfTicketRequested = numberOfTicketRequested;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumberOfTicketRequested() {
        return numberOfTicketRequested;
    }

    public void setNumberOfTicketRequested(String numberOfTicketRequested) {
        this.numberOfTicketRequested = numberOfTicketRequested;
    }
}
