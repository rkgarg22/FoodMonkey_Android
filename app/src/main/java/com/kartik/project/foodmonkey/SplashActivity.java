package com.kartik.project.foodmonkey;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.kartik.project.foodmonkey.Adapters.CustomSliderPagerAdapter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Timer;
import java.util.TimerTask;

import Infrastructure.AppCommon;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashActivity extends AppCompatActivity {


    @BindView(R.id.pager)
    ViewPager pager;

    @BindView(R.id.SliderDots)
    LinearLayout sliderDots;

    @BindView(R.id.skip)
    TextView skip;

    int currentPage = 0;

    private int dotscount;

    private ImageView[] dots;

    int[] mResources = {R.drawable.splash_one, R.drawable.splash_two, R.drawable.splash_three};

    CustomSliderPagerAdapter customSliderPagerAdapter;

    Timer timer = new Timer();

    final Handler handler = new Handler();

    final Runnable Update = new Runnable() {
        public void run() {
            if (currentPage == 2) {
                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                finish();
            }
            pager.setCurrentItem(currentPage++, true);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(SplashActivity.this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        printHashKey(this);
//
//        if (AppCommon.getInstance(this).getDeviceToken().isEmpty()) {
//            String deviceToken = "" + FirebaseInstanceId.getInstance().getToken();
//        }

        customSliderPagerAdapter = new CustomSliderPagerAdapter(SplashActivity.this, mResources);
        pager.setAdapter(customSliderPagerAdapter);

        dotscount = customSliderPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for (int i = 0; i < dotscount; i++) {

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.indicator_unactive));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDots.addView(dots[i], params);

        }

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dotscount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.indicator_unactive));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.indicator));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled

            @Override
            public void run() {
                handler.post(Update);
            }
        }, 500, 3000);
    }

    public void printHashKey(Context pContext) {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String hashKey = new String(Base64.encode(md.digest(), 0));
                Log.i("msg", "printHashKey() Hash Key: " + hashKey);
            }
        } catch (NoSuchAlgorithmException e) {
            Log.e("msg", "printHashKey()", e);
        } catch (Exception e) {
            Log.e("msg", "printHashKey()", e);
        }
    }

    @OnClick(R.id.skip)
    void setSkip() {
        timer.cancel();
        handler.removeCallbacks(Update);
        startActivity(new Intent(SplashActivity.this, HomeActivity.class));
        finish();


    }

}
