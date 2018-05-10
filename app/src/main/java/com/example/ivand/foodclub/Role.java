package com.example.ivand.foodclub;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pbusk on 03-05-2018.
 */

public class Role implements Parcelable {

    public String title;
    public int holderID = 0;
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

    public Role(Parcel in) {
        this.roleID = in.readInt();
        this.title = in.readString();
        this.holderID = in.readInt();
        int isTaken = in.readByte();
        if (isTaken > 0) {
            this.isTaken = true;
        } else {
            this.isTaken = false;
        }

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.roleID);
        dest.writeString(this.title);
        dest.writeInt(this.holderID);
        dest.writeByte((byte) (this.isTaken ? 1 : 0));
    }

    Role(int roleID, String title, int holderId, boolean isTaken) {
        this.roleID = roleID;
        this.title = title;
        this.holderID = holderId;
        this.isTaken = isTaken;
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

    void setHolderID(int ID) {
        this.holderID = ID;
    }

    int getHolderID() {
        return (holderID);
    }

    void setRoleID(int ID) {
        this.roleID = ID;
    }

    int getRoleID() {
        return (roleID);
    }

}
