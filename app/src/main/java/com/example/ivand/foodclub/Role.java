package com.example.ivand.foodclub;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pbusk on 03-05-2018.
 */

public class Role implements Parcelable {

    public String title;
    public String holderID = "";
    public int roleID;
    public boolean isTaken;


    public static final Parcelable.Creator<Role> CREATOR = new Parcelable.Creator<Role>() {
        public Role createFromParcel(Parcel in) {
            return new Role(in);
        }

        public Role[] newArray(int size) {
            return new Role[size];
        }
    };

    public Role(Parcel in){
        this.roleID = in.readInt();
        this.title = in.readString();
        this.holderID = in.readString();
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.roleID);
        dest.writeString(this.title);
        dest.writeString(this.holderID);
    }

    Role() {

    }
    Role(int roleID, String title) {
        this.roleID = roleID;
        this.title = title;
    }
    Role(String title) {
        this.title = title;
    }

    void setTitle(String title) {
        this.title = title;
    }
    String getTitle() {
        return (title);
    }

    void setHolderID(String ID) {
        this.holderID = ID;
    }
    String getHolderID() {
        return (holderID);
    }

    void setRoleID(int ID) {
        this.roleID = ID;
    }
    int getRoleID() {
        return(roleID);
    }

}
