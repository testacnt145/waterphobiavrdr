package com.waterphobiadr.data.remote.model;

import com.google.firebase.database.IgnoreExtraProperties;
/*
 * Created by shayan.raees on 10/25/2018.
 */

@IgnoreExtraProperties
public class Patient {

    private String name;
    private String email;
    private String contact;
    private int score;
    private int level;

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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}