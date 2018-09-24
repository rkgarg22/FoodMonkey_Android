package com.kartik.project.foodmonkey;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by kartikeya on 22/09/2018.
 */

public class FoodMonkey extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
