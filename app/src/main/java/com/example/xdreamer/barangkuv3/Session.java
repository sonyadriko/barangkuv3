package com.example.xdreamer.barangkuv3;

import android.app.Application;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Session extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
/*
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser != null){
            startActivity(new Intent(Session.this, MainActivity.class));
        }
        */
    }
}
