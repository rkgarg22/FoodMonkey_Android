package com.kartik.project.foodmonkey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddNewCardActivity extends AppCompatActivity {

    @BindView(R.id.left)
    ImageView left;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @BindView(R.id.addToCartBtn)
    Button addToCartBtn;

    @BindView(R.id.nameOnCard)
    EditText nameOnCard;

    @BindView(R.id.cardNumber)
    EditText cardNumber;

    @BindView(R.id.cvvEditText)
    EditText cvvEditText;

    @BindView(R.id.dateSpinner)
    Spinner monthSpinner;

    @BindView(R.id.yearSpinner)
    Spinner yearSpinner;
    ArrayList<String> monthList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_card);
        ButterKnife.bind(this);
        left.setVisibility(View.VISIBLE);
        toolbarText.setText(getString(R.string.addNewCreditAndDebitCard));

        monthList.clear();
        for (int i = 1; i <= 12; i++) {
            monthList.add("" + i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, monthList);
        monthSpinner.setAdapter(adapter);

    }

    @OnClick(R.id.left)
    void setLeft() {
        onBackPressed();
    }

    @OnClick(R.id.addToCartBtn)
    void setAddToCartBtn() {
        if (validation()) {

        }
    }

    private boolean validation() {
        boolean flag = true;


        return flag;
    }
}
