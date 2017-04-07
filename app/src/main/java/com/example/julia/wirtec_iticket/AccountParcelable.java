package com.example.julia.wirtec_iticket;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Glenn on 4/7/2017.
 */

public class AccountParcelable implements Parcelable {

    private String name;
    private String email;
    private String uid;

    public AccountParcelable() {}

    public AccountParcelable(Account a) {
        setName(a.getName());
        setEmail(a.getEmail());
        setUid(a.getUid());
    }

    public AccountParcelable(String name, String email, String uid) {
        this.name = name;
        this.email = email;
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    protected AccountParcelable(Parcel in) {
        name = in.readString();
        email = in.readString();
        uid = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(uid);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<AccountParcelable> CREATOR = new Parcelable.Creator<AccountParcelable>() {
        @Override
        public AccountParcelable createFromParcel(Parcel in) {
            return new AccountParcelable(in);
        }

        @Override
        public AccountParcelable[] newArray(int size) {
            return new AccountParcelable[size];
        }
    };
}