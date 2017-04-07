package com.example.julia.wirtec_iticket;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by shinichi on 4/2/17.
 */

public class EventParcelable implements Parcelable {
    private String code;
    private String eventname;
    private String eventdesc;
    private Long date;
    private Long time;
    private Long numberOfTickets;
    private Boolean limitToOne;
    private String place;
    private String checker;

    public EventParcelable() {}

    public EventParcelable( Event e) {
        setTime(e.getTime());
        setPlace(e.getPlace());
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

    protected EventParcelable(Parcel in) {
        code = in.readString();
        eventname = in.readString();
        eventdesc = in.readString();
        date = in.readByte() == 0x00 ? null : in.readLong();
        time = in.readByte() == 0x00 ? null : in.readLong();
        numberOfTickets = in.readByte() == 0x00 ? null : in.readLong();
        byte limitToOneVal = in.readByte();
        limitToOne = limitToOneVal == 0x02 ? null : limitToOneVal != 0x00;
        place = in.readString();
        checker = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(code);
        dest.writeString(eventname);
        dest.writeString(eventdesc);
        if (date == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeLong(date);
        }
        if (time == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeLong(time);
        }
        if (numberOfTickets == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeLong(numberOfTickets);
        }
        if (limitToOne == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (limitToOne ? 0x01 : 0x00));
        }
        dest.writeString(place);
        dest.writeString(checker);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<EventParcelable> CREATOR = new Parcelable.Creator<EventParcelable>() {
        @Override
        public EventParcelable createFromParcel(Parcel in) {
            return new EventParcelable(in);
        }

        @Override
        public EventParcelable[] newArray(int size) {
            return new EventParcelable[size];
        }
    };
}