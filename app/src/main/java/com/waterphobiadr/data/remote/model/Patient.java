package com.waterphobiadr.data.remote.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
/*
 * Created by shayan.raees on 10/25/2018.
 */

@IgnoreExtraProperties
public class Patient implements Parcelable {

    private String name;
    private String email;
    private String number;
    private String image;
    private int aquaphobiaScore;
    private int astraphobiaScore;
    private int bathophobiaScore;
    private ArrayList<Feedback> feedbacks;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public ArrayList<Feedback> getFeedback() {
        return feedbacks;
    }

    public void setFeedback(ArrayList<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    //______________________________________________________________________________________________
    private Patient(Parcel in) {
        this.name = in.readString();
        this.email = in.readString();
        this.number = in.readString();
        this.image = in.readString();
        this.aquaphobiaScore = in.readInt();
        this.astraphobiaScore = in.readInt();
        this.bathophobiaScore = in.readInt();
        this.feedbacks = in.readArrayList((Feedback.class.getClassLoader()));
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
        dest.writeString(image);
        dest.writeInt(aquaphobiaScore);
        dest.writeInt(astraphobiaScore);
        dest.writeInt(bathophobiaScore);
        dest.writeList(feedbacks);
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