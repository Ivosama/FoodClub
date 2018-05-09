package com.example.ivand.foodclub;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    
    public int id;
    public String firstName;
    public String lastName;
    public String password;
    public String allergies;
    public String email;
    public String description;


    // Constructor
    public User(int id, String firstName, String lastName, String password, String allergies, String email, String description){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.allergies = allergies;
        this.email = email;
        this.description = description;
    }

    public User(){

    }

    public  User(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getfirstName() {
        return firstName;
    }

    public void setfirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }
    
    // Parcelling part
    public User(Parcel in){
        this.id = in.readInt();
        this.firstName = in.readString();
        this.lastName =  in.readString();
        this.email =  in.readString();
        this.password =  in.readString();
        this.allergies = in.readString();
        this.description =  in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeString(this.email);
        dest.writeString(this.password);
        dest.writeString(this.allergies);
        dest.writeString(this.description);

    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", allergies='" + allergies + '\'' +
                ", profileDescription='" + description + '\'' +
                '}';
    }
}