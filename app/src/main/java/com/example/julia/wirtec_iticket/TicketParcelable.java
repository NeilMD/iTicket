package com.example.julia.wirtec_iticket;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Glenn on 4/6/2017.
 */

public class TicketParcelable implements Parcelable {
    private String code;
    private String eventname;
    private String eventdesc;
    private Long date;
    private Long time;
    private String place;
    private String status;
    private String orderInfo;
    private String checker;

    public TicketParcelable() {}

    public TicketParcelable(Ticket t) {
        setCode(t.getCode());
        setEventname(t.getEventname());
        setEventdesc(t.getEventdesc());
        setDate(t.getDate());
        setTime(t.getTime());
        setPlace(t.getPlace());
        setStatus(t.getStatus());
        setOrderInfo(t.getOrderInfo());
        setChecker(t.getChecker());
    }

    public TicketParcelable(String code, String eventname, String eventdesc, Long date, Long time, String place, String status, String orderInfo, String checker) {
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

    protected TicketParcelable(Parcel in) {
        code = in.readString();
        eventname = in.readString();
        eventdesc = in.readString();
        date = in.readByte() == 0x00 ? null : in.readLong();
        time = in.readByte() == 0x00 ? null : in.readLong();
        place = in.readString();
        status = in.readString();
        orderInfo = in.readString();
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
        dest.writeString(place);
        dest.writeString(status);
        dest.writeString(orderInfo);
        dest.writeString(checker);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<TicketParcelable> CREATOR = new Parcelable.Creator<TicketParcelable>() {
        @Override
        public TicketParcelable createFromParcel(Parcel in) {
            return new TicketParcelable(in);
        }

        @Override
        public TicketParcelable[] newArray(int size) {
            return new TicketParcelable[size];
        }
    };
}