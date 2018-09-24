package com.kartik.project.foodmonkey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kartik.project.foodmonkey.Adapters.HomeListingAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeListingActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    HomeListingAdapter homeListingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_listing);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        homeListingAdapter = new HomeListingAdapter(this);
        recyclerView.setAdapter(homeListingAdapter);
    }
}
