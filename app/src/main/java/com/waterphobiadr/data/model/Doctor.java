package com.waterphobiadr.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;
/*
 * Created by shayan.raees on 10/25/2018.
 */

@IgnoreExtraProperties
public class Doctor implements Parcelable {

    private int id;
    private String name;
    private String degree;
    private String university;
    private String email;
    private String number;

    public Doctor() {
    }

    //______________________________________________________________________________________________
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String degree) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    //______________________________________________________________________________________________
    private Doctor(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.degree = in.readString();
        this.university = in.readString();
        this.email = in.readString();
        this.number = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(degree);
        dest.writeString(university);
        dest.writeString(email);
        dest.writeString(number);
    }

    public static final Creator<Doctor> CREATOR = new Creator<Doctor>() {
        @Override
        public Doctor createFromParcel(Parcel in) {
            return new Doctor(in);
        }

        @Override
        public Doctor[] newArray(int size) {
            return new Doctor[size];
        }
    };


}