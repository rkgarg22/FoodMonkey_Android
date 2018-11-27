package com.kartik.project.foodmonkey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import Infrastructure.AppCommon;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedbackActivity extends AppCompatActivity {

    @BindView(R.id.left)
    ImageView left;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.review)
    TextView review;

    @BindView(R.id.ratingBar)
    RatingBar ratingBar;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);
        left.setVisibility(View.VISIBLE);
        toolbarText.setText(getString(R.string.settings));
    }

    @OnClick(R.id.left)
    void setLeft() {
        onBackPressed();
    }

    @OnClick(R.id.postFeedBack)
    void setPostFeedBack() {
        if (validation()) {
            callingFeedbackAPI(AppCommon.getInstance(this).getDeviceToken(),"");
        }
    }

    private void callingFeedbackAPI(String deviceToken, String customerID) {

    }

    private boolean validation() {
        boolean flag = true;
        String title = this.title.getText().toString().trim();
        String review = this.review.getText().toString().trim();
        float ratingValue = this.ratingBar.getRating();

        if (title.isEmpty()) {
            flag = false;
            Toast.makeText(this, getString(R.string.pleaseEnterTitle), Toast.LENGTH_SHORT).show();
        } else if (review.isEmpty()) {
            flag = false;
            Toast.makeText(this, getString(R.string.pleaseEnterTitle), Toast.LENGTH_SHORT).show();
        } else if (ratingValue == 0) {
            flag = false;
            Toast.makeText(this, getString(R.string.pleaseGiveSomeRate), Toast.LENGTH_SHORT).show();
        }

        return flag;

    }
}
