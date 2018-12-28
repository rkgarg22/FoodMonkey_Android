package com.kartik.project.foodmonkey.Fragments;


import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
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
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoActivity extends Fragment implements OnMapReadyCallback {

    @BindView(R.id.openingHourRecyclerView)
    RecyclerView openingHourRecyclerView;

    @BindView(R.id.descriptionsText)
    TextView descriptionsText;

    @BindView(R.id.deliveryRecyclerView)
    RecyclerView deliveryRecyclerView;

    @BindView(R.id.addressText)
    TextView addressText;

    @BindView(R.id.ratingUrl)
    TextView ratingUrl;

//    @BindView(R.id.descriptionsText)
//    TextView descriptionText;

//    @BindView(R.id.mapView)
//    MapView mMapView;

    InfoAdapter infoAdapter;

    private ArrayList<ResturantsDetailObject> restaurantDetails = new ArrayList<>();
    private ArrayList<String> deliveryPostCode = new ArrayList<>();
    private GoogleMap mMap;
    Double latitude, longitude;

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
            deliveryPostCode = getArguments().getStringArrayList("deliveryPostCode");
            setValues(restaurantDetails);
        }
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        deliveryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        deliveryRecyclerView.setNestedScrollingEnabled(false);
        openingHourRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        openingHourRecyclerView.setNestedScrollingEnabled(false);
        ArrayList<InfoModel> objects = new ArrayList<>();
        objects.add(0, new InfoModel("Monday", restaurantDetails.get(0).getMondayOpen() + "-" + restaurantDetails.get(0).getMondayClose()));
        objects.add(1, new InfoModel("Tuesday", restaurantDetails.get(0).getTuesdayOpen() + "-" + restaurantDetails.get(0).getTuesdayClose()));
        objects.add(2, new InfoModel("Wednesday", restaurantDetails.get(0).getWednesdayOpen() + "-" + restaurantDetails.get(0).getWednesdayClose()));
        objects.add(3, new InfoModel("Thursday", restaurantDetails.get(0).getThursdayOpen() + "-" + restaurantDetails.get(0).getThursdayClose()));
        objects.add(4, new InfoModel("Friday", restaurantDetails.get(0).getFridayOpen() + "-" + restaurantDetails.get(0).getFridayClose()));
        objects.add(5, new InfoModel("Saturday", restaurantDetails.get(0).getSaturdayOpen() + "-" + restaurantDetails.get(0).getSaturdayClose()));
        objects.add(6, new InfoModel("Sunday", restaurantDetails.get(0).getSunday_open() + "-" + restaurantDetails.get(0).getSundayClose()));
        infoAdapter = new InfoAdapter(getActivity(), objects);
        openingHourRecyclerView.setAdapter(infoAdapter);

        InfoAdapter infoAdapter = new InfoAdapter(deliveryPostCode, getActivity(), "deliveryCode");
        deliveryRecyclerView.setAdapter(infoAdapter);

        return view;
    }

    private void setValues(ArrayList<ResturantsDetailObject> restaurantDetails) {
        latitude = restaurantDetails.get(0).getCordinateLatitude();
        longitude = restaurantDetails.get(0).getCordinateLongitude();
        addressText.setText(restaurantDetails.get(0).getRestStreetLine1() + "," + restaurantDetails.get(0).getRestStreetLine2()
                + ",\n" + restaurantDetails.get(0).getRestCity() + ",\n" + restaurantDetails.get(0).getRestPostCode());
        descriptionsText.setText(restaurantDetails.get(0).getRestInfo());
    }

    @OnClick(R.id.ratingUrl)
    void setRatingUrl() {
        try {
            Intent i = new Intent("android.intent.action.MAIN");
            i.setComponent(ComponentName.unflattenFromString("com.android.chrome/com.android.chrome.Main"));
            i.addCategory("android.intent.category.LAUNCHER");
            i.setData(Uri.parse(getString(R.string.ratingUrl)));
            startActivity(i);
        } catch (ActivityNotFoundException e) {
            // Chrome is not installed
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.ratingUrl)));
            startActivity(i);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
        LatLng sydney = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        this.mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 12.0f));

    }

}
