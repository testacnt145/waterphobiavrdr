package com.waterphobiadr.data.remote.model;

import com.google.firebase.database.IgnoreExtraProperties;
/*
 * Created by shayan.raees on 10/25/2018.
 */

@IgnoreExtraProperties
public class Patient {

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
}