package com.kartik.project.foodmonkey;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.JsonSyntaxException;
import com.kartik.project.foodmonkey.API.FoodMonkeyAppService;
import com.kartik.project.foodmonkey.API.ServiceGenerator;
import com.kartik.project.foodmonkey.Adapters.ClosedRestAdapter;
import com.kartik.project.foodmonkey.Adapters.HomeListingAdapter;
import com.kartik.project.foodmonkey.Adapters.OpenResturantAdapter;
import com.kartik.project.foodmonkey.Adapters.PopUpAdapter;
import com.kartik.project.foodmonkey.Adapters.PreOrderRestAdapter;
import com.kartik.project.foodmonkey.ApiEntity.AddTokenEntity;
import com.kartik.project.foodmonkey.ApiEntity.ResturantListEnity;
import com.kartik.project.foodmonkey.ApiObject.CuisinesListObject;
import com.kartik.project.foodmonkey.ApiObject.RestutantListObject;
import com.kartik.project.foodmonkey.ApiResponse.CuisineListResponse;
import com.kartik.project.foodmonkey.ApiResponse.ResturantListResponse;

import java.util.ArrayList;

import Infrastructure.AppCommon;
import Infrastructure.GPSTracker;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeListingActivity extends AppCompatActivity {

//    @BindView(R.id.recyclerView)
//    RecyclerView recyclerView;

    @BindView(R.id.openRestRecyclerView)
    RecyclerView openRestRecyclerView;

    @BindView(R.id.preOrderLayout)
    RelativeLayout preOrderLayout;

    @BindView(R.id.preOrderTitle)
    TextView preOrderTitle;

    @BindView(R.id.preOrderRecyclerView)
    RecyclerView preOrderRecyclerView;

    @BindView(R.id.closeRestLayout)
    RelativeLayout closeRestLayout;

    @BindView(R.id.closeRestTitle)
    TextView closeRestTitle;

    @BindView(R.id.closeRestRecyclerView)
    RecyclerView closeRestRecyclerView;

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

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.totalRestaurants)
    TextView totalRestaurants;

    @BindView(R.id.bestMatchesText)
    TextView bestMatchesText;

    HomeListingAdapter homeListingAdapter;

    GPSTracker gpsTracker;

    Call call;

    ArrayList<CuisinesListObject> cuisinesListObjectArrayList = new ArrayList<>();

    String tokenKey, searchBy, postcode, deliveryOptions, listBy, cuisines;

    String pageNumber = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_listing);
        ButterKnife.bind(this);
        gpsTracker = new GPSTracker(this);
        gpsTracker.getPostalCodeByCoordinates();
        callingListCuisineAPI(AppCommon.getInstance(this).getDeviceToken());
        openRestRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        openRestRecyclerView.setNestedScrollingEnabled(false);

        preOrderRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        preOrderRecyclerView.setNestedScrollingEnabled(false);

        closeRestRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        closeRestRecyclerView.setNestedScrollingEnabled(false);


        tokenKey = AppCommon.getInstance(this).getDeviceToken();
        if (getIntent() != null) {
            if (getIntent().getStringExtra("searchBy").equals("postalcode")) {
                searchBy = "Postcode";
                postcode = getIntent().getStringExtra("postalCode");
                searchEditText.setText(postcode);
            } else {
                searchBy = "Loction";
                postcode = getIntent().getStringExtra("postalCode");
                searchEditText.setText(postcode);
            }
        }
        deliveryOptions = "Delivery";
        listBy = "Best_match";
        cuisines = "";
        postcode = "BD8 7SZ";               // remove it from here to make dynamic postcode
//        callingResturantList(tokenKey, searchBy, postcode, deliveryOptions, listBy, cuisines, pageNumber);
        callingResturantList(tokenKey, searchBy, postcode, deliveryOptions, listBy, cuisines, pageNumber);
        setOnClickListerner();
        gpsTracker.getLocation();

//        searchEditText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                s.toString();
//                filterDataSearch(s);
//            }
//        });

    }

    //    private void filterDataSearch(Editable s) {
//        ArrayList<OpenResturantObject> openResturantObjects = new ArrayList<>();
//        ArrayList<PreOrderRestObject> preOrderRestObjects = new ArrayList<>();
//        ArrayList<CloseResturantObject> closeResturantObject = new ArrayList<>();
//
//        for (int i = 0; i < restutantListObject.getOpenResturant().size(); i++) {
//            if (restutantListObject.getOpenResturant().get(i).getRestName().contains(s)) {
//                openResturantObjects.add(restutantListObject.getOpenResturant().get(i));
//                restutantListObject.setOpenResturant(openResturantObjects);
//            }
//        }
//        for (int i = 0; i < restutantListObject.getPreorderResturant().size(); i++) {
//            if (restutantListObject.getPreorderResturant().get(i).getRestName().contains(s)) {
//                preOrderRestObjects.add(restutantListObject.getPreorderResturant().get(i));
//                restutantListObject.setPreorderResturant(preOrderRestObjects);
//            }
//        }
//        for (int i = 0; i < restutantListObject.getCloseResturant().size(); i++) {
//            if (restutantListObject.getCloseResturant().get(i) != null)
//                if (restutantListObject.getCloseResturant().get(i).getRestName().contains(s)) {
//                    closeResturantObject.add(restutantListObject.getCloseResturant().get(i));
//                    restutantListObject.setCloseResturant(closeResturantObject);
//                }
//        }
//
////        restutantListObject.setOpenResturant(openResturantObjects);
////        restutantListObject.setPreorderResturant(preOrderRestObjects);
////        restutantListObject.setCloseResturant(closeResturantObject);
//        setAdapter(restutantListObject);
//    }
    OpenResturantAdapter openResturantAdapter;
    PreOrderRestAdapter preOrderRestAdapter;
    ClosedRestAdapter closedRestAdapter;

    void setAdapter(RestutantListObject restutantListObject) {
        totalRestaurants.setText("All restaurants");
        openResturantAdapter = new OpenResturantAdapter(this, restutantListObject.getOpenResturant());
        preOrderRestAdapter = new PreOrderRestAdapter(this, restutantListObject.getPreorderResturant());
        closedRestAdapter = new ClosedRestAdapter(this, restutantListObject.getCloseResturant());
        openRestRecyclerView.setVisibility(View.GONE);
        preOrderLayout.setVisibility(View.GONE);
        closeRestLayout.setVisibility(View.GONE);

//        homeListingAdapter = new HomeListingAdapter(this, restutantListObject);
        if (restutantListObject.getOpenResturant().size() != 0) {
            openRestRecyclerView.setVisibility(View.VISIBLE);
        }
        if (restutantListObject.getPreorderResturant().size() != 0) {
            preOrderLayout.setVisibility(View.VISIBLE);
            preOrderTitle.setText(restutantListObject.getPreorderResturant().size() + " " + getString(R.string.restaurantTakingPreOrder));
        }
        if (restutantListObject.getCloseResturant().size() != 0) {
            closeRestLayout.setVisibility(View.VISIBLE);
            closeRestTitle.setText(getString(R.string.closedRestaurents));
        }

        openRestRecyclerView.setAdapter(openResturantAdapter);
        preOrderRecyclerView.setAdapter(preOrderRestAdapter);
        closeRestRecyclerView.setAdapter(closedRestAdapter);

//        recyclerView.setAdapter(homeListingAdapter);
    }

    void setOnClickListerner() {
        deliveryTakeAwayImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupWindow popupWindow = deliveryPopUp();
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
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);

        RadioButton deliveryRadio = (RadioButton) view.findViewById(R.id.deliveryRadio);
        RadioButton takeAwayRadio = (RadioButton) view.findViewById(R.id.takeAwayRadio);
        RadioButton deliveryTakeAwayRadio = (RadioButton) view.findViewById(R.id.deliveryTakeAwayRadio);

        switch (deliveryOptions) {
            case "Delivery":
                deliveryRadio.setChecked(true);
                break;
            case "Collections":
                takeAwayRadio.setChecked(true);
                break;
            case "Collections_Delivery":
                deliveryTakeAwayRadio.setChecked(true);
                break;
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (group.getCheckedRadioButtonId()) {
                    case R.id.deliveryRadio:
                        deliveryOptions = getString(R.string.delivery);
                        break;
                    case R.id.takeAwayRadio:
                        deliveryOptions = getString(R.string.collections);
                        break;
                    case R.id.deliveryTakeAwayRadio:
                        deliveryOptions = getString(R.string.collections_delivery);
                        break;
                }
                callingResturantList(tokenKey, searchBy, postcode, deliveryOptions, listBy, cuisines, pageNumber);
                popupWindow.dismiss();
            }
        });

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

        RadioButton bestMatchedRadio = (RadioButton) view.findViewById(R.id.bestMatchedRadio);
        RadioButton distanceRadio = (RadioButton) view.findViewById(R.id.distanceRadio);
        RadioButton newRadio = (RadioButton) view.findViewById(R.id.newRadio);
        RadioButton avgRatingRadio = (RadioButton) view.findViewById(R.id.avgRatingRadio);
        RadioButton a_zRadio = (RadioButton) view.findViewById(R.id.a_zRadio);

        switch (listBy) {
            case "Best_match":
                bestMatchedRadio.setChecked(true);
                break;
            case "Distance":
                distanceRadio.setChecked(true);
                break;
            case "New":
                newRadio.setChecked(true);
                break;
            case "Avg_Rating":
                avgRatingRadio.setChecked(true);
                break;
            case "A_Z":
                a_zRadio.setChecked(true);
                break;
        }

        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (group.getCheckedRadioButtonId()) {
                    case R.id.bestMatchedRadio:
                        listBy = "Best_match";
                        listByText = "Best match";
                        break;
                    case R.id.distanceRadio:
                        listBy = "Distance";
                        listByText = "Distance";
                        break;
                    case R.id.newRadio:
                        listBy = "New";
                        listByText = "New";
                        break;
                    case R.id.avgRatingRadio:
                        listBy = "Avg_Rating";
                        listByText = "Average Rating";
                        break;
                    case R.id.a_zRadio:
                        listBy = "A_Z";
                        listByText = "A_Z ";
                        break;
                }
                bestMatchesText.setText(cuisinesText + " - " + listByText);
                callingResturantList(tokenKey, searchBy, postcode, deliveryOptions, listBy, cuisines, pageNumber);
                popupWindow.dismiss();
            }
        });

        popupWindow.setFocusable(true);
        popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(view);

        return popupWindow;
    }

    String cuisinesText = "All cuisines";
    String listByText = "Best match";
    PopupWindow categoriesPopWindow;

    public PopupWindow categoriesPopUp() {

        categoriesPopWindow = new PopupWindow(this);

        // inflate your layout or dynamically add view
        LayoutInflater inflater = (LayoutInflater) HomeListingActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.custom_categories_layout, null);

        RecyclerView popUprecyclerView = (RecyclerView) view.findViewById(R.id.popUprecyclerView);
        popUprecyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (cuisinesListObjectArrayList.size() == 0) {
            AppCommon.showDialog(HomeListingActivity.this, "Some thing went wrong, please try again");
        } else {
            setCuisineAdapter(popUprecyclerView, cuisinesListObjectArrayList);
        }

        categoriesPopWindow.setFocusable(true);
        categoriesPopWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        categoriesPopWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        categoriesPopWindow.setContentView(view);

        return categoriesPopWindow;
    }

    void setCuisineAdapter(RecyclerView popUprecyclerView, ArrayList<CuisinesListObject> arrayList) {

        PopUpAdapter popUpAdapter = new PopUpAdapter(this, arrayList);
        popUprecyclerView.setAdapter(popUpAdapter);
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
            postcode = searchEditText.getText().toString();
            searchBy = "Postcode";
            callingResturantList(tokenKey, searchBy, postcode, deliveryOptions, listBy, cuisines, pageNumber);

        }
    }

    void callingListCuisineAPI(String tokenKey)//CuisineListResponse
    {
        if (AppCommon.getInstance(HomeListingActivity.this).isConnectingToInternet(HomeListingActivity.this)) {
            AddTokenEntity addTokenEntity = new AddTokenEntity(tokenKey);
            FoodMonkeyAppService foodMonkeyAppService = ServiceGenerator.createService(FoodMonkeyAppService.class);
            call = foodMonkeyAppService.ListCuisinesAPI(addTokenEntity);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(HomeListingActivity.this).clearNonTouchableFlags(HomeListingActivity.this);
                    if (response.code() == 200) {
                        CuisineListResponse cuisineListResponse = (CuisineListResponse) response.body();
                        if (response.body() != null) {
                            if (cuisineListResponse.getCode().equals("200")) {
                                cuisinesListObjectArrayList = cuisineListResponse.getCuisinesList();
//                                setAdapter(customerHomeResponse.getRestutantList());
//                            User user = registrationResponse.getUserEntity();

//                            try {
//                                AppCommon.getInstance(HomeActivity.this).setUserLatitude(Double.parseDouble(latitude));
//                                AppCommon.getInstance(HomeActivity.this).setUserLongitude(Double.parseDouble(longitude));
//                            } catch (Exception e) {
//
//                            }
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    if (t instanceof JsonSyntaxException) {
                        AppCommon.showDialog(HomeListingActivity.this, "No resturant found");
                    } else {
                        AppCommon.showDialog(HomeListingActivity.this, getResources().getString(R.string.network_error));

                    }
                }
            });
        }
    }

    public void setCuisineSelectedID(int selectedPosition) {
        cuisines = cuisinesListObjectArrayList.get(selectedPosition).getCuisineName();
        callingResturantList(tokenKey, searchBy, postcode, deliveryOptions, listBy, cuisines, pageNumber);
        categoriesPopWindow.dismiss();

        cuisinesText = cuisines;
        bestMatchesText.setText(cuisinesText + " - " + listByText);

    }

    RestutantListObject restutantListObject;

    void callingResturantList(String tokenKey, String searchBy, String postCode, String deliveryOptions, String listBy, String cuisines, String pageNumber) {
        AppCommon.getInstance(this).setNonTouchableFlags(this);
        if (AppCommon.getInstance(HomeListingActivity.this).isConnectingToInternet(HomeListingActivity.this)) {
            progressBar.setVisibility(View.VISIBLE);
            //  final String token = myFirebaseInstanceIDService.getDeviceToken();
            ResturantListEnity resturantListEnity = new ResturantListEnity(tokenKey, searchBy, postCode, deliveryOptions, listBy, cuisines, pageNumber, "App");
            FoodMonkeyAppService foodMonkeyAppService = ServiceGenerator.createService(FoodMonkeyAppService.class);
            call = foodMonkeyAppService.resturantListAPI(resturantListEnity);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(HomeListingActivity.this).clearNonTouchableFlags(HomeListingActivity.this);
                    if (response.code() == 200) {
                        progressBar.setVisibility(View.GONE);
                        ResturantListResponse customerHomeResponse = (ResturantListResponse) response.body();
                        if (response.body() != null) {
                            if (customerHomeResponse.getCode().equals("200")) {
                                restutantListObject = customerHomeResponse.getRestutantList();
                                setAdapter(restutantListObject);
//                            User user = registrationResponse.getUserEntity();
//                            try {
//                                AppCommon.getInstance(HomeActivity.this).setUserLatitude(Double.parseDouble(latitude));
//                                AppCommon.getInstance(HomeActivity.this).setUserLongitude(Double.parseDouble(longitude));
//                            } catch (Exception e) {
//
//                            }
                            } else {
                                AppCommon.showDialog(HomeListingActivity.this, customerHomeResponse.getMessage());
                            }
                        }
                    } else {
                        AppCommon.showDialog(HomeListingActivity.this, getString(R.string.serverError));
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    AppCommon.getInstance(HomeListingActivity.this).clearNonTouchableFlags(HomeListingActivity.this);
                    progressBar.setVisibility(View.GONE);
                    if (t instanceof JsonSyntaxException) {
                        AppCommon.showDialog(HomeListingActivity.this, "No resturant found");
                    } else {
                        AppCommon.showDialog(HomeListingActivity.this, getResources().getString(R.string.network_error));
                    }
                }
            });
        } else {
            AppCommon.getInstance(HomeListingActivity.this).clearNonTouchableFlags(HomeListingActivity.this);
            AppCommon.showDialog(this, getResources().getString(R.string.network_error));
        }
    }

}
