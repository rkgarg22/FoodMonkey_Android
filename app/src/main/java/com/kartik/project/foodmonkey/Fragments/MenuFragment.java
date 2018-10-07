package com.kartik.project.foodmonkey.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.kartik.project.foodmonkey.Adapters.MenuExpandableAdapter;
import com.kartik.project.foodmonkey.DetailActivity;
import com.kartik.project.foodmonkey.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment {

    @BindView(R.id.expandableListView)
    ExpandableListView expandableListView;

    MenuExpandableAdapter menuExpandableAdapter;
    ArrayList<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        ButterKnife.bind(this, view);

        // preparing list data
        prepareListData();

        menuExpandableAdapter = new MenuExpandableAdapter(getActivity(), listDataHeader, listDataChild);

        // setting list adapter
        expandableListView.setAdapter(menuExpandableAdapter);

        return view;
    }


    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Popular");
        listDataHeader.add("Grilled Collection");
        listDataHeader.add("Sides Collection");
        listDataHeader.add("Salads");
        listDataHeader.add("Extras");

        // Adding child data
        List<String> extras = new ArrayList<String>();
        extras.add("Hummus");
        extras.add("Coles law");
        extras.add("Chicken XL");
        extras.add("Prime Pitta");

        List<String> iceCream = new ArrayList<String>();
        iceCream.add("Caramel Chew Chew");
        iceCream.add("Chocolate Fudge Brownie");
        iceCream.add("Cookie Dough");
        iceCream.add("Stawberry Cheesecake");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("2 Guns");
        comingSoon.add("The Smurfs 2");
        comingSoon.add("The Spectacular Now");
        comingSoon.add("The Canyons");
        comingSoon.add("Europa Report");

        listDataChild.put(listDataHeader.get(0), extras); // Header, Child data
        listDataChild.put(listDataHeader.get(1), iceCream);
        listDataChild.put(listDataHeader.get(2), comingSoon);
        listDataChild.put(listDataHeader.get(3), comingSoon);
        listDataChild.put(listDataHeader.get(4), comingSoon);
    }

    public void setPopStatus(Context context,String data, boolean status) {
//        Toast.makeText(context, "Working Status " + status, Toast.LENGTH_SHORT).show();
        ((DetailActivity)context).setAddItemToCartPopUpVisiblity(data);
    }

}
