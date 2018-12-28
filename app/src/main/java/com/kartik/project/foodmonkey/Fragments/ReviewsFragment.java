package com.kartik.project.foodmonkey.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.gson.JsonSyntaxException;
import com.kartik.project.foodmonkey.API.FoodMonkeyAppService;
import com.kartik.project.foodmonkey.API.ServiceGenerator;
import com.kartik.project.foodmonkey.Adapters.ReviewsAdapter;
import com.kartik.project.foodmonkey.ApiEntity.AddTokenEntity;
import com.kartik.project.foodmonkey.ApiObject.ResturantFeedbackList;
import com.kartik.project.foodmonkey.ApiResponse.ResturantFeedBackResponse;
import com.kartik.project.foodmonkey.R;

import java.util.List;

import Infrastructure.AppCommon;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewsFragment extends Fragment {

    @BindView(R.id.reviewRecyclerView)
    RecyclerView reviewRecyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    ReviewsAdapter reviewsAdapter;

    int resturantID;

    public ReviewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reviews, container, false);
        ButterKnife.bind(this, view);
        reviewRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        reviewRecyclerView.setNestedScrollingEnabled(false);
        if (getArguments() != null) {
            resturantID = getArguments().getInt("restID");
        }

        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            callingListOfFeedback(AppCommon.getInstance(getActivity()).getDeviceToken(),resturantID);// resturantID);
        }
    }

    void setAdapter(List<ResturantFeedbackList> resturantFeedbackList) {
        reviewsAdapter = new ReviewsAdapter(getActivity(), resturantFeedbackList);
        reviewRecyclerView.setAdapter(reviewsAdapter);
    }

    Call call;

    void callingListOfFeedback(String tokenKey, int resturantID) {
        AppCommon.getInstance(getActivity()).setNonTouchableFlags(getActivity());
        if (AppCommon.getInstance(getActivity()).isConnectingToInternet(getActivity())) {
            progressBar.setVisibility(View.VISIBLE);
            //  final String token = myFirebaseInstanceIDService.getDeviceToken();
            AddTokenEntity addTokenEntity = new AddTokenEntity(tokenKey, resturantID);
            FoodMonkeyAppService foodMonkeyAppService = ServiceGenerator.createService(FoodMonkeyAppService.class);
            call = foodMonkeyAppService.getResturentFeedBack(addTokenEntity);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(getActivity()).clearNonTouchableFlags(getActivity());
                    if (response.code() == 200) {
                        progressBar.setVisibility(View.GONE);
                        ResturantFeedBackResponse restDetailMenuResponse = (ResturantFeedBackResponse) response.body();
                        if (response.body() != null) {
                            if (restDetailMenuResponse.getCode().equals("200")) {

                                setAdapter(restDetailMenuResponse.getResturantFeedbackList());
                            } else {
                                AppCommon.showDialog(getActivity(), restDetailMenuResponse.getMessage());
                            }
                        }
                    } else {
                        AppCommon.showDialog(getActivity(), getString(R.string.serverError));
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    AppCommon.getInstance(getActivity()).clearNonTouchableFlags(getActivity());
                    progressBar.setVisibility(View.GONE);
                    if (t instanceof JsonSyntaxException) {
                        AppCommon.showDialog(getActivity(), "No Rating found");
                    } else {
                        AppCommon.showDialog(getActivity(), getResources().getString(R.string.network_error));

                    }
                }
            });
        } else {
            AppCommon.getInstance(getActivity()).clearNonTouchableFlags(getActivity());
            AppCommon.showDialog(getActivity(), getResources().getString(R.string.network_error));
        }
    }
}
