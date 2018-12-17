package com.kartik.project.foodmonkey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import Infrastructure.AppCommon;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.kartik.project.foodmonkey.API.ServiceGenerator.API_BASE_URL;

public class MyAccountActivty extends AppCompatActivity {

    @BindView(R.id.left)
    ImageView left;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @BindView(R.id.userprofile)
    TextView userprofile;

    @BindView(R.id.profilePic)
    SimpleDraweeView profilePic;

    @BindView(R.id.personalAccountLayout)
    RelativeLayout personalAccountLayout;

    @BindView(R.id.feedBackLayout)
    RelativeLayout feedBackLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account_activty);
        ButterKnife.bind(this);
        left.setVisibility(View.VISIBLE);
        left.setImageResource(R.drawable.back_icon);
        toolbarText.setText(getString(R.string.myAccount));
        userprofile.setText(AppCommon.getInstance(this).getFirstName() + " " + AppCommon.getInstance(this).getSurName());
        profilePic.setController(AppCommon.getDraweeController(profilePic, AppCommon.getInstance(this).getProfilePic()+"jpg", 100));
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

    @OnClick(R.id.feedBackLayout)
    void setFeedBackLayout() {
        Intent intent = new Intent(this, FeedbackActivity.class);
        startActivity(intent);
    }


}
