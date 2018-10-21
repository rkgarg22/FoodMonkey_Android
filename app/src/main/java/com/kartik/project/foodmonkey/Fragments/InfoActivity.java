package com.kartik.project.foodmonkey.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.kartik.project.foodmonkey.Adapters.InfoAdapter;
import com.kartik.project.foodmonkey.ApiObject.MenuDetailCategoryObject;
import com.kartik.project.foodmonkey.ApiObject.ResturantsDetailObject;
import com.kartik.project.foodmonkey.ApiResponse.ResturantListResponse;
import com.kartik.project.foodmonkey.Models.InfoModel;
import com.kartik.project.foodmonkey.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoActivity extends Fragment {

    @BindView(R.id.openingHourRecyclerView)
    RecyclerView openingHourRecyclerView;

    @BindView(R.id.descriptionsText)
    TextView descriptionsText;

    @BindView(R.id.deliveryRecyclerView)
    RecyclerView deliveryRecyclerView;

//    @BindView(R.id.descriptionsText)
//    TextView descriptionText;

    InfoAdapter infoAdapter;

    private ArrayList<ResturantsDetailObject> restaurantDetails = new ArrayList<>();

    public InfoActivity() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        ButterKnife.bind(this, view);

        if (getArguments() != null) {
            restaurantDetails = (ArrayList<ResturantsDetailObject>) getArguments().getSerializable("resturntsDetail");
            setValues(restaurantDetails);
        }

        deliveryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        deliveryRecyclerView.setNestedScrollingEnabled(false);
        openingHourRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        openingHourRecyclerView.setNestedScrollingEnabled(false);
        ArrayList<InfoModel> objects = new ArrayList<>();
        objects.add(0, new InfoModel("Monday", restaurantDetails.get(0).getMondayOpen()+"-"+restaurantDetails.get(0).getMondayClose()));
        objects.add(1, new InfoModel("Tuesday", restaurantDetails.get(0).getTuesdayOpen()+"-"+restaurantDetails.get(0).getTuesdayClose()));
        objects.add(2, new InfoModel("Wednesday", restaurantDetails.get(0).getWednesdayOpen()+"-"+restaurantDetails.get(0).getWednesdayClose()));
        objects.add(3, new InfoModel("Thursday", restaurantDetails.get(0).getThursdayOpen()+"-"+restaurantDetails.get(0).getThursdayClose()));
        objects.add(4, new InfoModel("Friday", restaurantDetails.get(0).getFridayOpen()+"-"+restaurantDetails.get(0).getFridayClose()));
        objects.add(5, new InfoModel("Saturday", restaurantDetails.get(0).getSaturdayOpen()+"-"+restaurantDetails.get(0).getFridayOpen()));
        objects.add(6, new InfoModel("Sunday", restaurantDetails.get(0).getSunday_open()+"-"+restaurantDetails.get(0).getTuesdayClose()));
        infoAdapter = new InfoAdapter(getActivity(), objects);
        openingHourRecyclerView.setAdapter(infoAdapter);
        deliveryRecyclerView.setAdapter(infoAdapter);

        return view;
    }

    private void setValues(ArrayList<ResturantsDetailObject> restaurantDetails) {
        descriptionsText.setText(restaurantDetails.get(0).getRestInfo());
    }

}
