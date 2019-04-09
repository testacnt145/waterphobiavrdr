package com.waterphobiadr.data.remote.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;
/*
 * Created by shayan.raees on 10/25/2018.
 */

@IgnoreExtraProperties
public class Patient implements Parcelable {

    private String name;
    private String email;
    private String number;
    private int aquaphobiaScore;
    private int astraphobiaScore;
    private int bathophobiaScore;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public Patient() {
    }

    //______________________________________________________________________________________________
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getAquaphobiaScore() {
        return aquaphobiaScore;
    }

    public void setAquaphobiaScore(int aquaphobiaScore) {
        this.aquaphobiaScore = aquaphobiaScore;
    }

    public int getAstraphobiaScore() {
        return astraphobiaScore;
    }

    public void setAstraphobiaScore(int astraphobiaScore) {
        this.astraphobiaScore = astraphobiaScore;
    }

    public int getBathophobiaScore() {
        return bathophobiaScore;
    }

    public void setBathophobiaScore(int bathophobiaScore) {
        this.bathophobiaScore = bathophobiaScore;
    }

    //______________________________________________________________________________________________
    private Patient(Parcel in) {
        this.name = in.readString();
        this.email = in.readString();
        this.number = in.readString();
        this.aquaphobiaScore = in.readInt();
        this.astraphobiaScore = in.readInt();
        this.bathophobiaScore = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(number);
        dest.writeInt(aquaphobiaScore);
        dest.writeInt(astraphobiaScore);
        dest.writeInt(bathophobiaScore);
    }

    public static final Parcelable.Creator<Patient> CREATOR = new Parcelable.Creator<Patient>() {
        @Override
        public Patient createFromParcel(Parcel in) {
            return new Patient(in);
        }

        @Override
        public Patient[] newArray(int size) {
            return new Patient[size];
        }
    };
}