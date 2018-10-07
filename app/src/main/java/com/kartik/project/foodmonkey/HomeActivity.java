package com.kartik.project.foodmonkey;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.JsonSyntaxException;
import com.kartik.project.foodmonkey.API.FoodMonkeyAppService;
import com.kartik.project.foodmonkey.API.ServiceGenerator;
import com.kartik.project.foodmonkey.Adapters.NavigationMenuAdapter;
import com.kartik.project.foodmonkey.Adapters.RestaurantAdapter;
import com.kartik.project.foodmonkey.ApiEntity.AddTokenEntity;
import com.kartik.project.foodmonkey.ApiEntity.ResturantListEnity;
import com.kartik.project.foodmonkey.ApiResponse.CommonResponse;
import com.kartik.project.foodmonkey.ApiResponse.ResturantListResponse;
import com.kartik.project.foodmonkey.Models.NavigationModel;

import java.util.ArrayList;

import Infrastructure.AppCommon;
import Infrastructure.GPSTracker;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.left)
    ImageView left;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.locationBtn)
    ImageView locationBtn;

    @BindView(R.id.navigationRecyclerView)
    RecyclerView navigationRecyclerView;

    @BindView(R.id.restaurantRecyclerView)
    RecyclerView restaurantRecyclerView;

    @BindView(R.id.menuRecyclerView)
    RecyclerView menuRecyclerView;

    @BindView(R.id.orderRecyclerView)
    RecyclerView orderRecyclerView;

    @BindView(R.id.postalCodeText)
    EditText postalCodeText;

    Call call;

    GPSTracker gpsTracker;

    ArrayList<NavigationModel> navigationModelArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        gpsTracker = new GPSTracker(this);
        gpsTracker.getPostalCodeByCoordinates();
        left.setImageResource(R.drawable.menu);
        left.setVisibility(View.VISIBLE);
        toolbarText.setText(getString(R.string.foodMonkey));
        restaurantRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        menuRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        orderRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        navigationRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        setNavigationData();
        NavigationMenuAdapter navigationMenuAdapter = new NavigationMenuAdapter(this, navigationModelArrayList);
        navigationRecyclerView.setAdapter(navigationMenuAdapter);

        String tokenKey = AppCommon.getInstance(this).getDeviceToken();
        if (tokenKey.equals("")) {
            callingAddTokenApi(FirebaseInstanceId.getInstance().getToken(),
                    getString(R.string.customerType), getString(R.string.callingChannel));
        } else {
//            callingResturantList(tokenKey, "location", getString(R.string.PostalCodeAPI));
        }


        setAdapter();

    }

    void setAdapter() {
        restaurantRecyclerView.setAdapter(new RestaurantAdapter(this, getString(R.string.restaurant)));
        menuRecyclerView.setAdapter(new RestaurantAdapter(this, getString(R.string.takeOut)));
        orderRecyclerView.setAdapter(new RestaurantAdapter(this, getString(R.string.yourOrders)));
    }

    @OnClick(R.id.left)
    void setLeft() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            drawer.openDrawer(GravityCompat.START);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    void setNavigationData() {
        navigationModelArrayList.clear();
        navigationModelArrayList.add(new NavigationModel(R.drawable.myaccount_icon, getString(R.string.myAccount)));
        navigationModelArrayList.add(new NavigationModel(R.drawable.signout_icon, getString(R.string.signOut)));
        navigationModelArrayList.add(new NavigationModel(R.drawable.referfrnd_icon, getString(R.string.referFriend)));
        navigationModelArrayList.add(new NavigationModel(R.drawable.myorders_icon, getString(R.string.myOrders)));
        navigationModelArrayList.add(new NavigationModel(R.drawable.help_icon, getString(R.string.help)));
        navigationModelArrayList.add(new NavigationModel(R.drawable.terms_icon, getString(R.string.terms)));
        navigationModelArrayList.add(new NavigationModel(R.drawable.setting_icon, getString(R.string.settings)));
    }

    @OnClick(R.id.restSeeMore)
    void setRestSeeMore() {
//        Toast.makeText(this, "" + AppCommon.getInstance(this).getUserLatitude(), Toast.LENGTH_SHORT).show();
        Log.d("Latitude-->", "" + AppCommon.getInstance(this).getUserLatitude());
        Intent intent = new Intent(HomeActivity.this, HomeListingActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.menuSeeMore)
    void setMenuSeeMore() {
        Intent intent = new Intent(HomeActivity.this, HomeListingActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.locationBtn)
    void setLocationBtn() {
        if (AppCommon.getInstance(HomeActivity.this).getUserPostalCode().equals("")) {
            gpsTracker.getLocation();
            gpsTracker.getPostalCodeByCoordinates();
            postalCodeText.setText(AppCommon.getInstance(this).getUserPostalCode());
        } else {
            postalCodeText.setText(AppCommon.getInstance(this).getUserPostalCode());
        }
    }

    @OnClick(R.id.orderSeeMore)
    void setOrderSeeMore() {
        Intent intent = new Intent(HomeActivity.this, HomeListingActivity.class);
        startActivity(intent);
    }

    void callingResturantList(String tokenKey, String searchBy, String postCode) {
        AppCommon.getInstance(this).setNonTouchableFlags(this);
        if (AppCommon.getInstance(HomeActivity.this).isConnectingToInternet(HomeActivity.this)) {
            progressBar.setVisibility(View.VISIBLE);
            //  final String token = myFirebaseInstanceIDService.getDeviceToken();
            ResturantListEnity resturantListEnity = new ResturantListEnity(tokenKey, searchBy, postCode);
            FoodMonkeyAppService foodMonkeyAppService = ServiceGenerator.createService(FoodMonkeyAppService.class);
            call = foodMonkeyAppService.resturantList(resturantListEnity);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(HomeActivity.this).clearNonTouchableFlags(HomeActivity.this);
                    if (response.code() == 200) {
                        progressBar.setVisibility(View.GONE);
                        ResturantListResponse registrationResponse = (ResturantListResponse) response.body();
                        if (registrationResponse.getCode().equals("200")) {
//                            User user = registrationResponse.getUserEntity();

//                            try {
//                                AppCommon.getInstance(HomeActivity.this).setUserLatitude(Double.parseDouble(latitude));
//                                AppCommon.getInstance(HomeActivity.this).setUserLongitude(Double.parseDouble(longitude));
//                            } catch (Exception e) {
//
//                            }
                        } else {
                            AppCommon.showDialog(HomeActivity.this, registrationResponse.getMessage());
                        }
                    } else {
                        AppCommon.showDialog(HomeActivity.this, getString(R.string.serverError));
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    AppCommon.getInstance(HomeActivity.this).clearNonTouchableFlags(HomeActivity.this);
                    progressBar.setVisibility(View.GONE);
                    if (t instanceof JsonSyntaxException) {
                        AppCommon.showDialog(HomeActivity.this, "No resturant found");
                    } else {
                        AppCommon.showDialog(HomeActivity.this, getResources().getString(R.string.network_error));

                    }
                }
            });
        } else {
            AppCommon.getInstance(HomeActivity.this).clearNonTouchableFlags(HomeActivity.this);
            AppCommon.showDialog(this, getResources().getString(R.string.network_error));
        }
    }

    void callingAddTokenApi(final String tokenKey, String customerType, String callingChannel) {
        AppCommon.getInstance(this).setNonTouchableFlags(this);
        if (AppCommon.getInstance(HomeActivity.this).isConnectingToInternet(HomeActivity.this)) {
            progressBar.setVisibility(View.VISIBLE);
            //  final String token = myFirebaseInstanceIDService.getDeviceToken();
            AddTokenEntity addTokenEntity = new AddTokenEntity(tokenKey, customerType, callingChannel);
            FoodMonkeyAppService foodMonkeyAppService = ServiceGenerator.createService(FoodMonkeyAppService.class);
            call = foodMonkeyAppService.addTokenAPI(addTokenEntity);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(HomeActivity.this).clearNonTouchableFlags(HomeActivity.this);
                    if (response.code() == 200) {
                        progressBar.setVisibility(View.GONE);
                        CommonResponse commonResponse = (CommonResponse) response.body();
                        if (commonResponse.getCode().equals("200")) {
                            AppCommon.getInstance(HomeActivity.this).setDeviceToken(tokenKey);
                        } else {
                            AppCommon.showDialog(HomeActivity.this, commonResponse.getMessage());
                        }
                    } else {
                        AppCommon.showDialog(HomeActivity.this, getString(R.string.serverError));
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    AppCommon.getInstance(HomeActivity.this).clearNonTouchableFlags(HomeActivity.this);
                    progressBar.setVisibility(View.GONE);
                    if (t instanceof JsonSyntaxException) {
                        AppCommon.showDialog(HomeActivity.this, getString(R.string.somethingWentWrong));
                    } else {
                        AppCommon.showDialog(HomeActivity.this, getResources().getString(R.string.network_error));

                    }
                }
            });
        } else {
            AppCommon.getInstance(HomeActivity.this).clearNonTouchableFlags(HomeActivity.this);
            AppCommon.showDialog(this, getResources().getString(R.string.network_error));
        }
    }

}
