package com.example.julia.wirtec_iticket;

/**
 * Created by shinichi on 4/4/17.
 */

public class Code {
    private String owneruid;
    private String event;

    public Code(){}

    public Code(String owneruid, String event) {
        this.owneruid = owneruid;
        this.event = event;
    }

    public String getOwneruid() {
        return owneruid;
    }

    public void setOwneruid(String owneruid) {
        this.owneruid = owneruid;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
