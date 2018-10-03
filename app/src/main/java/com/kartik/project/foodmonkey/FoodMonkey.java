package com.kartik.project.foodmonkey;

import android.app.Application;
import android.support.multidex.MultiDex;

import com.facebook.FacebookSdk;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.firebase.FirebaseApp;

/**
 * Created by kartikeya on 22/09/2018.
 */

public class FoodMonkey extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        FirebaseApp.initializeApp(FoodMonkey.this);
//        FacebookSdk.sdkInitialize(this);
        MultiDex.install(this);
    }
}
