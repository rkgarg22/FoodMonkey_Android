package com.kartik.project.foodmonkey.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kartik.project.foodmonkey.Fragments.MenuFragment;
import com.kartik.project.foodmonkey.Models.ChildAndAddonModel;
import com.kartik.project.foodmonkey.Models.HeaderDataModel;
import com.kartik.project.foodmonkey.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kartikeya on 27/09/2018.
 */

public class MenuExpandableAdapter extends BaseExpandableListAdapter {
    Context mContext;
    private ArrayList<HeaderDataModel> headerListDate = new ArrayList<>(); // header titles
    // child data in format of header title, child title
    private HashMap<HeaderDataModel, List<ChildAndAddonModel>> childListDate = new HashMap<>();

    public MenuExpandableAdapter(Context mContext, ArrayList<HeaderDataModel> headerListDate,
                                 HashMap<HeaderDataModel, List<ChildAndAddonModel>> childListDate) {
        this.mContext = mContext;
        this.headerListDate = headerListDate;
        this.childListDate = childListDate;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.childListDate.get(this.headerListDate.get(groupPosition))
                .get(childPosititon).getItemName();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_child_expandable, null);
        }

        ImageView vegNonVegIndicator = (ImageView) convertView.findViewById(R.id.vegNonVegIndicator);
        final TextView childItemAmmount = (TextView) convertView
                .findViewById(R.id.childItemAmmount);
        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.childItem);

        txtListChild.setText(childText);
        childItemAmmount.setText(childListDate.get(headerListDate.get(groupPosition)).get(childPosition).getItemPrice());
        if (childListDate.get(headerListDate.get(groupPosition)).get(childPosition).getIsNonVeg() == 1) {
            vegNonVegIndicator.setImageResource(R.drawable.nonveg_icon);
        } else {
            vegNonVegIndicator.setImageResource(R.drawable.veg_icon);
        }
//        childItemAmmount.setText(childListDate.get(headerListDate.get(groupPosition)).get(childPosition).getItemPrice());

        txtListChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (childListDate.get(headerListDate.get(groupPosition)).get(childPosition).getAddOnSize() != 0) {
                    new MenuFragment().setPopStatus(mContext, "subItemDisplay", childPosition, groupPosition);
                } else {
                    new MenuFragment().setPopStatus(mContext, "itemDisplay", childPosition, groupPosition);
                }
            }
        });
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.childListDate.get(this.headerListDate.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.headerListDate.get(groupPosition).getHeaderName();
    }

    @Override
    public int getGroupCount() {
        return this.headerListDate.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group_expandable, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.header);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


}