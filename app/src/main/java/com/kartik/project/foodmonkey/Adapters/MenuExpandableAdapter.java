package com.kartik.project.foodmonkey.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kartik.project.foodmonkey.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kartikeya on 27/09/2018.
 */

public class MenuExpandableAdapter extends BaseExpandableListAdapter {
    Context mContext;
    private ArrayList<String> headerListDate = new ArrayList<>(); // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> childListDate = new HashMap<>();

    public MenuExpandableAdapter(Context mContext, ArrayList<String> headerListDate,
                                 HashMap<String, List<String>> childListDate) {
        this.mContext = mContext;
        this.headerListDate = headerListDate;
        this.childListDate = childListDate;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.childListDate.get(this.headerListDate.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_child_expandable, null);
        }

        ImageView vegNonVegIndicator = (ImageView) convertView.findViewById(R.id.vegNonVegIndicator);
        TextView childItemAmmount = (TextView) convertView
                .findViewById(R.id.childItemAmmount);
        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.childItem);

        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.childListDate.get(this.headerListDate.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.headerListDate.get(groupPosition);
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