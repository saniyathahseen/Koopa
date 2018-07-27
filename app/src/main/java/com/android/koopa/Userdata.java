package com.android.koopa;

import android.os.Parcel;
import android.os.Parcelable;

public class Userdata implements Parcelable {
    public String uname;
    public String uwork;
//    public Float urating;
    public String udis;
    public String place;

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }



    public Userdata(String username, String uwork, String udis, String place) {
        this.uname = username;
        this.uwork = uwork;
//        this.urating = urating;
        this.udis = udis;
        this.place=place;
    }

    protected Userdata(Parcel in) {
        uname = in.readString();
        uwork = in.readString();
//        urating = in.readFloat();
        udis = in.readString();
        place=in.readString();
    }

    public static final Creator<Userdata> CREATOR = new Creator<Userdata>() {
        @Override
        public Userdata createFromParcel(Parcel in) {
            return new Userdata(in);
        }

        @Override
        public Userdata[] newArray(int size) {
            return new Userdata[size];
        }
    };

    public String getUsername() {

        return uname;
    }

    public String getUwork() {
        return uwork;
    }

//    public float getUrating() {
//        return urating;
//    }

    public String getUdis() {
        return udis;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uname);
        dest.writeString(uwork);
//        dest.writeFloat(urating);
        dest.writeString(udis);
        dest.writeString(place);
    }
}
