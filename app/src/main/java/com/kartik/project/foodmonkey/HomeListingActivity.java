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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.kartik.project.foodmonkey.Adapters.HomeListingAdapter;
import com.kartik.project.foodmonkey.Adapters.PopUpAdapter;

import java.util.ArrayList;

import Infrastructure.GPSTracker;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeListingActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.left)
    ImageView left;

    @BindView(R.id.deliveryTakeAwayImg)
    ImageView deliveryTakeAwayImg;

    @BindView(R.id.filterImg)
    ImageView filterImg;

    @BindView(R.id.filterImgTwo)
    ImageView filterImgTwo;

    @BindView(R.id.searchEditText)
    EditText searchEditText;

    @BindView(R.id.seachLayout)
    RelativeLayout seachLayout;

    HomeListingAdapter homeListingAdapter;

    GPSTracker gpsTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_listing);
        ButterKnife.bind(this);
        gpsTracker = new GPSTracker(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);
        homeListingAdapter = new HomeListingAdapter(this);
        recyclerView.setAdapter(homeListingAdapter);
        setOnClickListerner();
        gpsTracker.getLocation();

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

    @OnClick(R.id.left)
    void setLeft() {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        if (searchFlag) {
            setOnSearch(false);
        } else {
            super.onBackPressed();
        }
    }

    boolean searchFlag = false;

    @OnClick(R.id.searchImg)
    void setOnSearchClick() {
        if (searchFlag) {
            setOnSearch(false);
        } else {
            setOnSearch(true);
        }
    }

    void setOnSearch(boolean status) {
        if (status) {
            searchEditText.setEnabled(true);
            seachLayout.setBackgroundResource(R.drawable.search_background);
            left.setVisibility(View.GONE);
            searchFlag = status;
        } else {
            searchFlag = status;
            searchEditText.setEnabled(false);
            left.setVisibility(View.VISIBLE);
            seachLayout.setBackgroundResource(android.R.color.transparent);
        }
    }


}
