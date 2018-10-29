package com.kartik.project.foodmonkey;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kartik.project.foodmonkey.Adapters.EditCartAdapter;
import com.kartik.project.foodmonkey.Models.AddItemsToCartModel;
import com.kartik.project.foodmonkey.Models.EditCartModel;

import java.util.ArrayList;
import java.util.List;

import Infrastructure.AppCommon;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditCartActivity extends AppCompatActivity {

    @BindView(R.id.editCartRecyclerView)
    RecyclerView editCartRecyclerView;

    @BindView(R.id.left)
    ImageView left;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @BindView(R.id.addItems)
    Button addItems;

    @BindView(R.id.done)
    Button done;

    @BindView(R.id.totalAmount)
    TextView totalAmount;

    float totalValue = 0;

    EditCartAdapter editCartAdapter;

    ArrayList<EditCartModel> editCartModelArrayList = new ArrayList<>();
    List<AddItemsToCartModel> addItemsToCartModelArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_cart);
        ButterKnife.bind(this);
        left.setVisibility(View.VISIBLE);
        toolbarText.setText(getString(R.string.editCart));
        toolbarText.setGravity(Gravity.START | Gravity.CENTER);

        if (getIntent() != null) {
            addItemsToCartModelArrayList = (List<AddItemsToCartModel>) getIntent().getSerializableExtra("cardArray");
        }

        editCartRecyclerView.setLayoutManager(new LinearLayoutManager(EditCartActivity.this));
        editCartRecyclerView.setNestedScrollingEnabled(false);

//        dummyData();

        editCartAdapter = new EditCartAdapter(this, addItemsToCartModelArrayList);
        editCartRecyclerView.setAdapter(editCartAdapter);

        for (int i = 0; i < addItemsToCartModelArrayList.size(); i++) {
            totalValue = totalValue + (Float.parseFloat(addItemsToCartModelArrayList.get(i).getPrice())
                    * Integer.parseInt(addItemsToCartModelArrayList.get(i).getQuantity()));
        }
        updateTotalPrice(totalValue, "update");
    }

//    void dummyData() {
//        editCartModelArrayList.clear();
//        editCartModelArrayList.add(new EditCartModel("Caramel chew chew", "$2.49", "2"));
//        editCartModelArrayList.add(new EditCartModel("Chocolate Fudge Browine", "$2.49", "4"));
//        editCartModelArrayList.add(new EditCartModel("Cookie Dough", "$2.49", "1"));
//        editCartModelArrayList.add(new EditCartModel("Oreo Shake", "$2.49", "1"));
//    }

    @OnClick(R.id.left)
    void setLeft() {
        onBackPressed();
    }

    @OnClick(R.id.done)
    void setDone() {
        AppCommon.getInstance(this).setAddToCartObject(addItemsToCartModelArrayList);
        Intent returnIntent = new Intent();
        returnIntent.putExtra("totalValue",this.totalValue);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();

    }

    @OnClick(R.id.addItems)
    void setAddItems() {

    }

    public void updateTotalPrice(float totalValue, String status) {
        if (status.equals("remove")) {
            this.totalValue = this.totalValue - totalValue;
        } else if (status.equals("adding")) {
            this.totalValue = this.totalValue + totalValue;

        } else if (status.equals("update")) {
            this.totalValue = totalValue;
        }
        totalAmount.setText("$" + this.totalValue);
    }
}
