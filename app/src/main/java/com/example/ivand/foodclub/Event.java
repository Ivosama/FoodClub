package com.example.ivand.foodclub;

import android.os.Parcel;
import android.os.Parcelable;

public class Event implements Parcelable {
    int ID;
    int dist;
    int maxRoles = 10;
    String name;
    String menu;
    String place;
    String description;
    int price;
    Role[] roles = new Role[maxRoles];

    public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>() {
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        public Event[] newArray(int size) {
            return new Event[size];
        }
    };


    public Event() {

    }

    public Event(String name) {
        this.name = name;
    }

        //constructor
        public Event(int ID, int dist, String name, String menu, String place, String description, int price) {
            this.ID = ID;
            this.dist = dist;
            this.name = name;
            this.menu = menu;
            this.place = place;
            this.description = description;
            this.price = price;

        }

        public String getName() {
            return(this.name);
        }

    public Event(Parcel in){
            this.ID = in.readInt();
            this.dist = in.readInt();
        this.name = in.readString();
        this.menu = in.readString();
        this.place = in.readString();
        this.description = in.readString();
        this.price = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.ID);
            dest.writeInt(this.dist);
        dest.writeString(this.name);
        dest.writeString(this.menu);
        dest.writeString(this.place);
        dest.writeString(this.description);
        dest.writeInt(this.price);
    }


}