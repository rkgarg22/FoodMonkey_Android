package com.kartik.project.foodmonkey;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;

import com.kartik.project.foodmonkey.Adapters.HomeListingAdapter;
import com.kartik.project.foodmonkey.Adapters.PopUpAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeListingActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.deliveryTakeAwayImg)
    ImageView deliveryTakeAwayImg;

    @BindView(R.id.filterImg)
    ImageView filterImg;

    @BindView(R.id.filterImgTwo)
    ImageView filterImgTwo;

    HomeListingAdapter homeListingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_listing);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);
        homeListingAdapter = new HomeListingAdapter(this);
        recyclerView.setAdapter(homeListingAdapter);
        setOnClickListerner();

    }

    void setOnClickListerner() {
        deliveryTakeAwayImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindow popupWindow = deliveryPopUp();
                popupWindow.showAsDropDown(v, -40, 18); // where u want show on view click event popupwindow.showAsDropDown(view, x, y);
            }
        });

        filterImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindow popupWindow = filterPopUp();
                popupWindow.showAsDropDown(v, -40, 18); // where u want show on view click event popupwindow.showAsDropDown(view, x, y);
            }
        });

        filterImgTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindow popupWindow = categoriesPopUp();
                popupWindow.showAsDropDown(v, -40, 18); // where u want show on view click event popupwindow.showAsDropDown(view, x, y);
            }
        });
    }

    public PopupWindow deliveryPopUp() {

        final PopupWindow popupWindow = new PopupWindow(this);

        // inflate your layout or dynamically add view
        LayoutInflater inflater = (LayoutInflater) HomeListingActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.custom_delivery_layout, null);

//        Button item = (Button) view.findViewById(R.id.button1);

        popupWindow.setFocusable(true);
        popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(view);

        return popupWindow;
    }

    public PopupWindow filterPopUp() {

        final PopupWindow popupWindow = new PopupWindow(this);

        // inflate your layout or dynamically add view
        LayoutInflater inflater = (LayoutInflater) HomeListingActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.custom_filter_layout, null);

//        Button item = (Button) view.findViewById(R.id.button1);

        popupWindow.setFocusable(true);
        popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(view);

        return popupWindow;
    }

    public PopupWindow categoriesPopUp() {

        final PopupWindow popupWindow = new PopupWindow(this);

        // inflate your layout or dynamically add view
        LayoutInflater inflater = (LayoutInflater) HomeListingActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.custom_categories_layout, null);

        RecyclerView popUprecyclerView = (RecyclerView) view.findViewById(R.id.popUprecyclerView);
        popUprecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.clear();
        arrayList.add("Afghan(2)");
        arrayList.add("American(10)");
        arrayList.add("Arabic(1)");
        arrayList.add("Asian(6)");
        arrayList.add("Bagels(12)");
        arrayList.add("Bangladeshi(22)");
        arrayList.add("Breakfast(2)");
        arrayList.add("Burgers(22)");
        arrayList.add("Cakes(1)");
        arrayList.add("Caribbean(1)");

        PopUpAdapter popUpAdapter = new PopUpAdapter(this, arrayList);
        popUprecyclerView.setAdapter(popUpAdapter);

        popupWindow.setFocusable(true);
        popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(view);

        return popupWindow;
    }


}
