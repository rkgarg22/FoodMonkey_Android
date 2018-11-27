package com.kartik.project.foodmonkey.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.kartik.project.foodmonkey.Adapters.DetailProductAdapter;
import com.kartik.project.foodmonkey.Adapters.MenuExpandableAdapter;
import com.kartik.project.foodmonkey.ApiObject.MenuDetailCategoryObject;
import com.kartik.project.foodmonkey.ApiObject.ResturantsDetailObject;
import com.kartik.project.foodmonkey.ApiObject.ResturantsObject;
import com.kartik.project.foodmonkey.DetailActivity;
import com.kartik.project.foodmonkey.Models.ChildAndAddonModel;
import com.kartik.project.foodmonkey.Models.HeaderDataModel;
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

//    @BindView(R.id.expandableListView)
//    ExpandableListView expandableListView;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    MenuExpandableAdapter menuExpandableAdapter;
    ArrayList<HeaderDataModel> listDataHeader;
    HashMap<HeaderDataModel, List<ChildAndAddonModel>> listDataChild;

    private ArrayList<MenuDetailCategoryObject> menuCategory = new ArrayList<>();

    //------------------------------------------------------------------------

    DetailProductAdapter detailProductAdapter;

    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setNestedScrollingEnabled(false);
        if (getArguments() != null) {
            menuCategory = (ArrayList<MenuDetailCategoryObject>) getArguments().getSerializable("allMenus");
            prepareListData(menuCategory);
        }

        // preparing list data
        detailProductAdapter = new DetailProductAdapter(getActivity(), menuCategory);
        recyclerView.setAdapter(detailProductAdapter);
//        menuExpandableAdapter = new MenuExpandableAdapter(getActivity(), listDataHeader, listDataChild);

        // setting list adapter
//        expandableListView.setAdapter(menuExpandableAdapter);

        return view;
    }


    /*
     * Preparing the list data
     */
    private void prepareListData(ArrayList<MenuDetailCategoryObject> menuCategory) {
        listDataHeader = new ArrayList<HeaderDataModel>();
        listDataChild = new HashMap<HeaderDataModel, List<ChildAndAddonModel>>();

        List<ChildAndAddonModel> childAndAddonModels = new ArrayList<ChildAndAddonModel>();
        try {
            for (int i = 0; i < menuCategory.size(); i++) {
                listDataHeader.add(i, new HeaderDataModel(
                        menuCategory.get(i).getMenuCategoryId(),
                        menuCategory.get(i).getMenuCategoryName()));
                for (int j = 0; j < menuCategory.get(i).getMenus().size(); j++) {
                    childAndAddonModels.add(i, new ChildAndAddonModel(
                            menuCategory.get(i).getMenus().get(j).getItemId(),
                            menuCategory.get(i).getMenus().get(j).getRestId(),
                            menuCategory.get(i).getMenus().get(j).getAddOn().size(),
                            menuCategory.get(i).getMenus().get(j).getItemName(),
                            menuCategory.get(i).getMenus().get(j).getItemPrice(),
                            menuCategory.get(i).getMenus().get(j).getItemDesc(),
                            menuCategory.get(i).getMenus().get(j).getItemStatus(),
                            menuCategory.get(i).getMenus().get(j).getIsItemNonVeg()
                    ));
                }
                listDataChild.put(listDataHeader.get(i), childAndAddonModels); // Header, Child data
            }
        } catch (IndexOutOfBoundsException e) {
            Toast.makeText(getActivity(), "No Data Found", Toast.LENGTH_SHORT).show();
        }
    }

    public void setPopStatus(Context context, String data, int childPosition, int parentPosition) {
////        Toast.makeText(context, "Working Status " + status, Toast.LENGTH_SHORT).show();
//        ((DetailActivity) context).setAddItemToCartPopUpVisiblity(data, childPosition, parentPosition);
    }

}
