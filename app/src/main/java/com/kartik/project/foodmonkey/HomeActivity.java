package com.kartik.project.foodmonkey;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.JsonSyntaxException;
import com.kartik.project.foodmonkey.API.FoodMonkeyAppService;
import com.kartik.project.foodmonkey.API.ServiceGenerator;
import com.kartik.project.foodmonkey.Adapters.NavigationMenuAdapter;
import com.kartik.project.foodmonkey.Adapters.OrderAdapter;
import com.kartik.project.foodmonkey.Adapters.RecentViewAdapter;
import com.kartik.project.foodmonkey.Adapters.RestaurantAdapter;
import com.kartik.project.foodmonkey.ApiEntity.AddTokenEntity;
import com.kartik.project.foodmonkey.ApiEntity.CustomerHomeEntity;
import com.kartik.project.foodmonkey.ApiObject.HomeOrderedObject;
import com.kartik.project.foodmonkey.ApiObject.HomePopularObject;
import com.kartik.project.foodmonkey.ApiObject.HomeRestutantObject;
import com.kartik.project.foodmonkey.ApiObject.HomeViewedObject;
import com.kartik.project.foodmonkey.ApiResponse.CommonResponse;
import com.kartik.project.foodmonkey.ApiResponse.CustomerHomeResponse;
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

import static com.kartik.project.foodmonkey.API.ServiceGenerator.API_BASE_URL;

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

    @BindView(R.id.cancelBtn)
    ImageView cancelBtn;

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

    @BindView(R.id.restaurantLayout)
    RelativeLayout restaurantLayout;

    @BindView(R.id.takeOutLayout)
    RelativeLayout takeOutLayout;

    @BindView(R.id.yourOrderLayout)
    RelativeLayout yourOrderLayout;

    @BindView(R.id.profilePic)
    SimpleDraweeView navProfilePic;

    @BindView(R.id.userName)
    TextView userName;

    @BindView(R.id.userEmail)
    TextView userEmail;

    @BindView(R.id.profileLayout)
    RelativeLayout profileLayout;

    Call call;

    GPSTracker gpsTracker;


    private ArrayList<HomePopularObject> popularRestaurants = new ArrayList<>();

    private ArrayList<HomeViewedObject> viewedRestaurants = new ArrayList<>();

    private ArrayList<HomeOrderedObject> orderedRestaurants = new ArrayList<>();

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

        String tokenKey = AppCommon.getInstance(this).getDeviceToken();
        String customerID = AppCommon.getInstance(this).getCustomerID();
        if (tokenKey.equals("")) {
            callingAddTokenApi(FirebaseInstanceId.getInstance().getToken(),
                    getString(R.string.customerType), getString(R.string.callingChannel));
        } else {
            callingResturantList(tokenKey, customerID);
        }
//        setAdapter(customerHomeResponse.getRestutantList());

        postalCodeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchBy = "postalcode";
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (AppCommon.getInstance(this).isUserLogIn()) {
            userName.setText(AppCommon.getInstance(this).getFirstName() + " " + AppCommon.getInstance(this).getSurName());
            userEmail.setText(AppCommon.getInstance(this).getEmailAddress());
//            navProfilePic.setImageURI(API_BASE_URL + AppCommon.getInstance(this).getProfilePic());
            navProfilePic.setController(AppCommon.getDraweeController(navProfilePic, AppCommon.getInstance(this).getProfilePic()+"jpg", 100));
            profileLayout.setVisibility(View.VISIBLE);
        }
        setNavigationData();
        NavigationMenuAdapter navigationMenuAdapter = new NavigationMenuAdapter(this, navigationModelArrayList);
        navigationRecyclerView.setAdapter(navigationMenuAdapter);

    }

    void setAdapter(HomeRestutantObject restutantList) {
        if (restutantList.getPopularRestaurants().size() == 0) {
            restaurantLayout.setVisibility(View.GONE);
        } else {
            popularRestaurants = restutantList.getPopularRestaurants();
            restaurantRecyclerView.setAdapter(new RestaurantAdapter(this, getString(R.string.restaurant),
                    restutantList.getPopularRestaurants()));
        }
        if (restutantList.getOrderedRestaurants().size() == 0) {
            yourOrderLayout.setVisibility(View.GONE);
        } else {
            orderedRestaurants = restutantList.getOrderedRestaurants();
            orderRecyclerView.setAdapter(new OrderAdapter(this, getString(R.string.yourOrders),
                    restutantList.getOrderedRestaurants()));
        }
        if (restutantList.getViewedRestaurants().size() == 0) {
            takeOutLayout.setVisibility(View.GONE);
        } else {
            viewedRestaurants = restutantList.getViewedRestaurants();
            menuRecyclerView.setAdapter(new RecentViewAdapter(this, getString(R.string.takeOut),
                    restutantList.getViewedRestaurants()));
        }
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
        if (AppCommon.getInstance(this).isUserLogIn()) {
            navigationModelArrayList.add(new NavigationModel(R.drawable.myaccount_icon, getString(R.string.myAccount)));
            navigationModelArrayList.add(new NavigationModel(R.drawable.signout_icon, getString(R.string.signOut)));
        } else {
            navigationModelArrayList.add(new NavigationModel(R.drawable.signout_icon, getString(R.string.logIn)));
            navigationModelArrayList.add(new NavigationModel(R.drawable.signout_icon, getString(R.string.Signup)));
        }
        navigationModelArrayList.add(new NavigationModel(R.drawable.referfrnd_icon, getString(R.string.referFriend)));
        navigationModelArrayList.add(new NavigationModel(R.drawable.myorders_icon, getString(R.string.myOrders)));
        navigationModelArrayList.add(new NavigationModel(R.drawable.help_icon, getString(R.string.help)));
        navigationModelArrayList.add(new NavigationModel(R.drawable.terms_icon, getString(R.string.terms)));
        navigationModelArrayList.add(new NavigationModel(R.drawable.setting_icon, getString(R.string.settings)));
    }

    @OnClick(R.id.restSeeMore)
    void setRestSeeMore() {
        Intent intent = new Intent(HomeActivity.this, HomeListingActivity.class);
        intent.putExtra("comingFrom", "orderRest");
        Bundle bundle = new Bundle();
        bundle.putSerializable("resturant", popularRestaurants);
        intent.putExtras(bundle);
        startActivity(intent);

//        Log.d("Latitude-->", "" + AppCommon.getInstance(this).getUserLatitude());
//        Intent intent = new Intent(HomeActivity.this, HomeListingActivity.class);
//        intent.putExtra("searchBy", searchBy);
//        if (searchBy.equals("postalcode")) {
//            intent.putExtra("postalCode", postalCodeText.getText().toString().trim());
//        } else {
//            intent.putExtra("postalCode", AppCommon.getInstance(this).getUserPostalCode());
//        }
//        startActivity(intent);
    }

    @OnClick(R.id.cancelBtn)
    void setOnCancelBtn() {
        postalCodeText.setText("");
    }

    @OnClick(R.id.searchBtn)
    void setSearchBtn() {
        if (postalCodeText.getText().toString().isEmpty()) {
            AppCommon.showDialog(HomeActivity.this, getString(R.string.locationOrPostalCode));
        } else {
//            Toast.makeText(this, "" + AppCommon.getInstance(this).getUserLatitude(), Toast.LENGTH_SHORT).show();
            Log.d("Latitude-->", "" + AppCommon.getInstance(this).getUserLatitude());
            Intent intent = new Intent(HomeActivity.this, HomeListingActivity.class);
            intent.putExtra("searchBy", searchBy);
            if (searchBy.equals("postalcode")) {
                intent.putExtra("comingFrom","postcode");
                intent.putExtra("postalCode", postalCodeText.getText().toString().trim());
            } else {
                intent.putExtra("comingFrom","location");
                intent.putExtra("postalCode", AppCommon.getInstance(this).getUserPostalCode());
            }
            startActivity(intent);
        }
    }

    @OnClick(R.id.menuSeeMore)
    void setMenuSeeMore() {
        Intent intent = new Intent(HomeActivity.this, HomeListingActivity.class);
        intent.putExtra("comingFrom", "menuTakeOut");
        Bundle bundle = new Bundle();
        bundle.putSerializable("resturant", viewedRestaurants);
        intent.putExtras(bundle);
        startActivity(intent);

//        Intent intent = new Intent(HomeActivity.this, HomeListingActivity.class);
//        intent.putExtra("searchBy", searchBy);
//        if (searchBy.equals("postalcode")) {
//            intent.putExtra("postalCode", postalCodeText.getText().toString().trim());
//        } else {
//            intent.putExtra("postalCode", AppCommon.getInstance(this).getUserPostalCode());
//        }
//        startActivity(intent);
    }

    String searchBy = "";

    @OnClick(R.id.locationBtn)
    void setLocationBtn() {
        searchBy = "location";
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
        intent.putExtra("comingFrom", "orderSeeMore");
        Bundle bundle = new Bundle();
        bundle.putSerializable("resturant", orderedRestaurants);
        intent.putExtras(bundle);
        startActivity(intent);
//        Intent intent = new Intent(HomeActivity.this, HomeListingActivity.class);
//        intent.putExtra("searchBy", searchBy);
//        if (searchBy.equals("postalcode")) {
//            intent.putExtra("postalCode", postalCodeText.getText().toString().trim());
//        } else {
//            intent.putExtra("postalCode", AppCommon.getInstance(this).getUserPostalCode());
//        }
//        startActivity(intent);
    }

    void callingResturantList(final String tokenKey, String customerID) {
        AppCommon.getInstance(this).setNonTouchableFlags(this);
        if (AppCommon.getInstance(HomeActivity.this).isConnectingToInternet(HomeActivity.this)) {
            progressBar.setVisibility(View.VISIBLE);
            CustomerHomeEntity customerHomeEntity = new CustomerHomeEntity(tokenKey, customerID);
            FoodMonkeyAppService foodMonkeyAppService = ServiceGenerator.createService(FoodMonkeyAppService.class);
            call = foodMonkeyAppService.HomeResturantAPI(customerHomeEntity);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(HomeActivity.this).clearNonTouchableFlags(HomeActivity.this);
                    if (response.code() == 200) {
                        progressBar.setVisibility(View.GONE);
                        CustomerHomeResponse customerHomeResponse = (CustomerHomeResponse) response.body();
                        if (response.body() != null) {
                            if (customerHomeResponse.getCode().equals("200")) {
                                setAdapter(customerHomeResponse.getRestutantList());
                            } else if (customerHomeResponse.getCode().equals("401")) {
                                if (!tokenKey.isEmpty())
                                    callingAddTokenApi(tokenKey, getString(R.string.customerType), getString(R.string.callingChannel));
                            } else {
                                AppCommon.showDialog(HomeActivity.this, customerHomeResponse.getMessage());
                            }
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
                            callingResturantList(tokenKey, "");
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

    public void setNavigationClick(int adapterPosition) {
        String title = navigationModelArrayList.get(adapterPosition).getTitle();
        switch (title) {
            case "My Account":
                if (!AppCommon.getInstance(this).isUserLogIn()) {
                    Toast.makeText(this, "Please login before view my account" + adapterPosition, Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(this, MyAccountActivty.class));
                }
                break;
            case "Log In":
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case "Sign Up":
                startActivity(new Intent(this, SignUpActivity.class));
                break;
            case "Signout":
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                builder.setTitle(title);
                builder.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.dismiss();
                        AppCommon.getInstance(HomeActivity.this).clearSharedPreference();
                        startActivity(new Intent(HomeActivity.this, HomeActivity.class));
                        finish();
                    }
                });
                builder.show();
                break;
            case "Refer a friend":
                startActivity(new Intent(this, ReferFriendActivity.class));
                break;
            case "My Orders":
                startActivity(new Intent(this,YourOrderListingActivty.class));
//                startActivity(new Intent(this, YourOrderActivity.class));
//                Toast.makeText(this, "" + adapterPosition, Toast.LENGTH_SHORT).show();
                break;
            case "Help":
                startActivity(new Intent(this, HelpActivity.class));
//                Toast.makeText(this, "" + adapterPosition, Toast.LENGTH_SHORT).show();
                break;
            case "Terms":
                startActivity(new Intent(this, TermsAndConditionsActivity.class));
                break;
            case "Settings":
                startActivity(new Intent(this, SettingActivity.class));
//                Toast.makeText(this, "" + adapterPosition, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
