package com.kartik.project.foodmonkey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kartik.project.foodmonkey.Adapters.AddCardAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CompletePaymentActivity extends AppCompatActivity {

    @BindView(R.id.placeOrderRecyclerView)
    RecyclerView placeOrderRecyclerView;

    @BindView(R.id.left)
    ImageView left;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    AddCardAdapter addCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_payment);
        ButterKnife.bind(this);
        left.setVisibility(View.VISIBLE);
        toolbarText.setText(getString(R.string.completeYourPayment));
        placeOrderRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        placeOrderRecyclerView.setNestedScrollingEnabled(false);
        addCardAdapter = new AddCardAdapter(this);
        placeOrderRecyclerView.setAdapter(addCardAdapter);

    }

    @OnClick(R.id.left)
    void setLeft() {
        onBackPressed();
    }

    @OnClick(R.id.addNewCard)
    void setAddNewCard() {
        startActivity(new Intent(this, AddNewCardActivity.class));
    }

}
