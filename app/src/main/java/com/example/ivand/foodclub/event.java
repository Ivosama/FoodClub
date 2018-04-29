package com.example.ivand.foodclub;

import android.os.Parcel;
import android.os.Parcelable;

public class event implements Parcelable {
    String name;
    String menu;
    String place;
    String description;
    String price;
    public static final Parcelable.Creator<event> CREATOR = new Parcelable.Creator<event>() {
        public event createFromParcel(Parcel in) {
            return new event(in);
        }

        public event[] newArray(int size) {
            return new event[size];
        }
    };

        //constructor
        public event(String name, String menu, String place, String description, String price) {
            this.name = name;
            this.menu = menu;
            this.place = place;
            this.description = description;
            this.price = price;

        }


    public event(Parcel in){
        this.name = in.readString();
        this.menu = in.readString();
        this.place = in.readString();
        this.description = in.readString();
        this.price = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.menu);
        dest.writeString(this.place);
        dest.writeString(this.description);
        dest.writeString(this.price);
    }


}