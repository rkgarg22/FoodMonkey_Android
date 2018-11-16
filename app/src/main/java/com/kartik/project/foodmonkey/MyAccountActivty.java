package com.kartik.project.foodmonkey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyAccountActivty extends AppCompatActivity {

    @BindView(R.id.left)
    ImageView left;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @BindView(R.id.personalAccountLayout)
    RelativeLayout personalAccountLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account_activty);
        ButterKnife.bind(this);
        left.setVisibility(View.VISIBLE);
        left.setImageResource(R.drawable.back_icon);
        toolbarText.setText(getString(R.string.myAccount));
    }

    @OnClick(R.id.left)
    void setLeft() {
        onBackPressed();
    }

    @OnClick(R.id.personalAccountLayout)
    void setPersonalAccountLayout() {
        Intent intent = new Intent(this, PersonalAccountActivity.class);
        startActivity(intent);
    }
}
