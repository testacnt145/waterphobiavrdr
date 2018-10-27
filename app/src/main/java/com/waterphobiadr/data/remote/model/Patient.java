package com.waterphobiadr.data.remote.model;

import com.google.firebase.database.IgnoreExtraProperties;
/*
 * Created by shayan.raees on 10/25/2018.
 */

@IgnoreExtraProperties
public class Patient {

    public String name;
    public String email;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public Patient() {
    }

    public Patient(String name, String email) {
        this.name = name;
        this.email = email;
    }
}