package com.kartik.project.foodmonkey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kartik.project.foodmonkey.Adapters.EditCartAdapter;
import com.kartik.project.foodmonkey.Models.EditCartModel;

import java.util.ArrayList;

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

    EditCartAdapter editCartAdapter;

    ArrayList<EditCartModel> editCartModelArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_cart);
        ButterKnife.bind(this);
        left.setVisibility(View.VISIBLE);
        toolbarText.setText(getString(R.string.editCart));
        toolbarText.setGravity(Gravity.START | Gravity.CENTER);

        editCartRecyclerView.setLayoutManager(new LinearLayoutManager(EditCartActivity.this));
        editCartRecyclerView.setNestedScrollingEnabled(false);

        dummyData();

        editCartAdapter = new EditCartAdapter(this, editCartModelArrayList);
        editCartRecyclerView.setAdapter(editCartAdapter);


    }

    void dummyData() {
        editCartModelArrayList.clear();
        editCartModelArrayList.add(new EditCartModel("Caramel chew chew", "$2.49", "2"));
        editCartModelArrayList.add(new EditCartModel("Chocolate Fudge Browine", "$2.49", "4"));
        editCartModelArrayList.add(new EditCartModel("Cookie Dough", "$2.49", "1"));
        editCartModelArrayList.add(new EditCartModel("Oreo Shake", "$2.49", "1"));
    }

    @OnClick(R.id.left)
    void setLeft() {
        onBackPressed();
    }
}
