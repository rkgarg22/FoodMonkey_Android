package com.kartik.project.foodmonkey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity {

    @BindView(R.id.left)
    ImageView left;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @BindView(R.id.loginText)
    TextView loginText;

    @BindView(R.id.readTerms)
    TextView readTerms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        left.setVisibility(View.VISIBLE);
        toolbarText.setText(getString(R.string.signUp));
        setLoginText();
        setReadTerms();
    }

    private void setLoginText() {
        SpannableString spannableString = new SpannableString(getString(R.string.haveAnAccount));
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                finish();
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                ds.setColor(getResources().getColor(R.color.colorPrimary));
            }
        };
        spannableString.setSpan(clickableSpan, 17, 22, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        loginText.setText(spannableString);
        loginText.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void setReadTerms() {
        SpannableString spannableString = new SpannableString(getString(R.string.readAcceptTermsAndConditions));
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                ds.setColor(getResources().getColor(R.color.colorPrimary));
            }
        };
        spannableString.setSpan(clickableSpan, 21, 41, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        readTerms.setText(spannableString);
        readTerms.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @OnClick(R.id.left)
    void setLeft() {
        onBackPressed();
    }
}
