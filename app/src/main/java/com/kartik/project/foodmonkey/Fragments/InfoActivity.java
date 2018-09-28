package com.kartik.project.foodmonkey.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.kartik.project.foodmonkey.Adapters.InfoAdapter;
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

    @BindView(R.id.deliveryRecyclerView)
    RecyclerView deliveryRecyclerView;

    InfoAdapter infoAdapter;

    public InfoActivity() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        ButterKnife.bind(this, view);

        deliveryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        deliveryRecyclerView.setNestedScrollingEnabled(false);
        openingHourRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        openingHourRecyclerView.setNestedScrollingEnabled(false);
        ArrayList<InfoModel> objects = new ArrayList<>();
        objects.add(0,new InfoModel("Monday","12:00 - 22:30"));
        objects.add(1,new InfoModel("Tuesday","12:00 - 22:30") );
        objects.add(2,new InfoModel("Wednesday","12:00 - 22:30") );
        objects.add(3, new InfoModel("Thursday","12:00 - 22:30"));
        objects.add(4,new InfoModel("Friday","12:00 - 22:30") );
        objects.add(5,new InfoModel("Saturday","12:00 - 22:30") );
        objects.add(6,new InfoModel("Sunday","12:00 - 22:30"));
        infoAdapter = new InfoAdapter(getActivity(), objects);
        openingHourRecyclerView.setAdapter(infoAdapter);
        deliveryRecyclerView.setAdapter(infoAdapter);

        return view;
    }

}
