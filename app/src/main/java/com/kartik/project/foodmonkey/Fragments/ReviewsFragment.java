package com.kartik.project.foodmonkey.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kartik.project.foodmonkey.Adapters.ReviewsAdapter;
import com.kartik.project.foodmonkey.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewsFragment extends Fragment {

    @BindView(R.id.reviewRecyclerView)
    RecyclerView reviewRecyclerView;

    ReviewsAdapter reviewsAdapter;

    public ReviewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reviews, container, false);
        ButterKnife.bind(this, view);

        reviewRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        reviewRecyclerView.setNestedScrollingEnabled(false);
        reviewsAdapter=new ReviewsAdapter(getActivity());
        reviewRecyclerView.setAdapter(reviewsAdapter);
        return view;
    }

}
