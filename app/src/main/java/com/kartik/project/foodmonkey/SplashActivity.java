package com.kartik.project.foodmonkey;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ViewFlipper;

import com.kartik.project.foodmonkey.Adapters.CustomSliderPagerAdapter;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {


    @BindView(R.id.pager)
    ViewPager pager;

    int currentPage = 0;

    int[] mResources = {R.drawable.splash_one, R.drawable.splash_two, R.drawable.splash_three};

    CustomSliderPagerAdapter customSliderPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        customSliderPagerAdapter = new CustomSliderPagerAdapter(SplashActivity.this, mResources);
        pager.setAdapter(customSliderPagerAdapter);

        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == 2) {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }
                pager.setCurrentItem(currentPage++, true);
            }
        };

        Timer timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled

            @Override
            public void run() {
                handler.post(Update);
            }
        }, 500, 3000);
    }

}
