package com.example.ivand.foodclub;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Event implements Parcelable {

    int ID;
    int dist;
    int ownerID;
    String name;
    String menu;
    String place;
    String description;
    String time;
    int price;
    public ArrayList<Role> roles = new ArrayList<>();

    public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>() {
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    public void addRole(Role role) {
        Role tempRole = role;
        this.roles.add(tempRole);
    }

    public Role getRole() {
        return (roles.get(roles.size() - 1));
    }

    public int getOwnerID() {
        return (ownerID);
    }

    public int getID() {
        return (this.ID);
    }

    public void setID(int ID) {
        this.ID = ID;
    }


    public Event() {

    }

    public Event(String name) {
        this.name = name;
    }

    //constructor
        /*public Event(int ID, int dist, String name, String menu, String place, String description, String time, int price) {

            this.ID = ID;
            this.dist = dist;
            this.name = name;
            this.menu = menu;
            this.place = place;
            this.description = description;
            this.time = time;
            this.price = price;
        }*/
    public Event(int ID, int ownerID, int dist, String name, String menu, String place, String description, String time, int price) {

        this.ID = ID;
        this.ownerID = ownerID;
        this.dist = dist;
        this.name = name;
        this.menu = menu;
        this.place = place;
        this.description = description;
        this.time = time;
        this.price = price;


    }


    public String getName() {
        return (this.name);
    }

    public Event(Parcel in) {
        this.ID = in.readInt();
        this.ownerID = in.readInt();
        this.dist = in.readInt();
        this.name = in.readString();
        this.menu = in.readString();
        this.place = in.readString();
        this.description = in.readString();
        this.time = in.readString();
        this.price = in.readInt();


        this.roles = new ArrayList<>();
        in.readTypedList(roles, Role.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.ID);
        dest.writeInt(this.ownerID);
        dest.writeInt(this.dist);
        dest.writeString(this.name);
        dest.writeString(this.menu);
        dest.writeString(this.place);
        dest.writeString(this.description);
        dest.writeString(this.time);
        dest.writeInt(this.price);
        dest.writeTypedList(roles);
    }


}