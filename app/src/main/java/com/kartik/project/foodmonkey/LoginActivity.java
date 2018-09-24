package com.kartik.project.foodmonkey;

import android.content.Intent;
import android.media.Image;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.left)
    ImageView left;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @BindView(R.id.signUpText)
    TextView signUpText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        left.setVisibility(View.VISIBLE);
        toolbarText.setText(getString(R.string.login));
        setSignUpText();
    }

    @OnClick(R.id.loginButton)
    void setLoginButton()
    {
        startActivity(new Intent(this,HomeActivity.class));
    }

    private void setSignUpText() {
        SpannableString spannableString = new SpannableString(getString(R.string.dontHaveAccount));
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                ds.setColor(getResources().getColor(R.color.colorPrimary));
            }
        };
        spannableString.setSpan(clickableSpan, 24, 31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        signUpText.setText(spannableString);
        signUpText.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @OnClick(R.id.forgotPassword)
    void setForgotPassword() {
        startActivity(new Intent(this, ForgetPasswordActvity.class));
    }
}
