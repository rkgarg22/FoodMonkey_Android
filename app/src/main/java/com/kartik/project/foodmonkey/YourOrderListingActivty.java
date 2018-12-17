package com.kartik.project.foodmonkey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YourOrderListingActivty extends AppCompatActivity {


    @BindView(R.id.left)
    ImageView left;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_order_listing_activty);
        ButterKnife.bind(this);
        left.setVisibility(View.VISIBLE);
        toolbarText.setText(getString(R.string.yourOrder));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }
}
