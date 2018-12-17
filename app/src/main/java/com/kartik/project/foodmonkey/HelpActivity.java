package com.kartik.project.foodmonkey;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kartikeya on 15/12/2018.
 */

public class HelpActivity extends AppCompatActivity {
    @BindView(R.id.left)
    ImageView left;

    @BindView(R.id.toolbarText)
    TextView toolbarText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        ButterKnife.bind(this);
        left.setVisibility(View.VISIBLE);
        toolbarText.setText(getString(R.string.help));
    }

    @OnClick(R.id.left)
    void setLeft() {
        onBackPressed();
    }
}