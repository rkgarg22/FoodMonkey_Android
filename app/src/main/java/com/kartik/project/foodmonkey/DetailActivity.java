package com.kartik.project.foodmonkey;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.kartik.project.foodmonkey.API.FoodMonkeyAppService;
import com.kartik.project.foodmonkey.API.ServiceGenerator;
import com.kartik.project.foodmonkey.Adapters.MenuExpandableSubItemAdapter;
import com.kartik.project.foodmonkey.ApiEntity.ResturantListEnity;
import com.kartik.project.foodmonkey.ApiObject.MenuAddOnObject;
import com.kartik.project.foodmonkey.ApiObject.MenuDetailCategoryObject;
import com.kartik.project.foodmonkey.ApiObject.ResturantsDetailObject;
import com.kartik.project.foodmonkey.ApiObject.ResturantsObject;
import com.kartik.project.foodmonkey.ApiResponse.RestDetailMenuResponse;
import com.kartik.project.foodmonkey.Fragments.InfoActivity;
import com.kartik.project.foodmonkey.Fragments.MenuFragment;
import com.kartik.project.foodmonkey.Fragments.ReviewsFragment;
import com.kartik.project.foodmonkey.Models.AddItemsToCartModel;

import java.util.ArrayList;
import java.util.List;

import Infrastructure.AppCommon;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.kartik.project.foodmonkey.API.ServiceGenerator.API_BASE_URL;

public class DetailActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.left)
    ImageView left;

//    @BindView(R.id.toolbarText)
//    TextView toolbarText;

    @BindView(R.id.cartToolbarLayout)
    RelativeLayout cartToolbarLayout;

    @BindView(R.id.displayValue)
    TextView displayQuantityValue;

    @BindView(R.id.itemTitleText)
    TextView itemTitleText;

    @BindView(R.id.addItemToCartPopUp)
    LinearLayout addItemToCartPopUp;

    @BindView(R.id.toolbarText)
    EditText toolbarText;

    @BindView(R.id.searchBarLayout)
    RelativeLayout searchBarLayout;

    @BindView(R.id.loginButton)
    Button loginButton;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.displayPic)
    SimpleDraweeView displayPic;

    @BindView(R.id.titleText)
    TextView titleText;

    @BindView(R.id.noOfReviews)
    TextView noOfReviews;

    @BindView(R.id.descriptionsText)
    TextView descriptionsText;

    @BindView(R.id.deliveryText)
    TextView deliveryText;

    @BindView(R.id.minSpendText)
    TextView minSpendText;

    @BindView(R.id.distanceText)
    TextView distanceText;

    @BindView(R.id.ratingBar)
    RatingBar ratingBar;

    @BindView(R.id.innerItemsRecyclerView)
    RecyclerView innerItemsRecyclerView;   // onClick of expandable subchild Items

    @BindView(R.id.innerItemsLayout)
    RelativeLayout innerItemsLayout;   // onClick of expandable subchild whole layout to visible

    @BindView(R.id.totalNoOfItemInCart)
    TextView totalNoOfItemInCart;

    boolean searchFlag;
    int resturantID;

    Call call;

    AddItemsToCartModel addItemsToCartModel;

    List<AddItemsToCartModel> addItemsToCartModelArrayList = new ArrayList<>();

    private void setResturantDetail(ArrayList<ResturantsDetailObject> restaurantDetails) {
        toolbarText.setText(restaurantDetails.get(0).getRestName());
        titleText.setText(restaurantDetails.get(0).getRestName().trim());
        noOfReviews.setText("(" + restaurantDetails.get(0).getNumberOfReviews() + ")");
        descriptionsText.setText(restaurantDetails.get(0).getCousine1() + restaurantDetails.get(0).getCousine2() + "");
        deliveryText.setText("£" + restaurantDetails.get(0).getDelivery());
        minSpendText.setText("£" + restaurantDetails.get(0).getMinSpend());
        ratingBar.setRating(Float.parseFloat(restaurantDetails.get(0).getAggregateFeedback()));
        displayPic.setImageURI(API_BASE_URL + restaurantDetails.get(0).getImageLink());
//        distanceText.setText(restaurantDetails.get(0).getD());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        if (getIntent() != null) {
            resturantID = getIntent().getIntExtra("restID", 0);
        }
        callingResturantDetail(AppCommon.getInstance(DetailActivity.this).getDeviceToken(), String.valueOf(resturantID));

        innerItemsRecyclerView.setLayoutManager(new LinearLayoutManager(DetailActivity.this));
        setTabs();

        addItemsToCartModelArrayList = AppCommon.getInstance(this).getAddToCartObject();
        totalNoOfItemInCart.setText(""+addItemsToCartModelArrayList.size());
        tabLayout.setOnTabSelectedListener(this);
    }

    void setTabs() {
        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.menu)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.reviews)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.info)));
//        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    public class ViewPagerAdapter extends FragmentStatePagerAdapter {

        //integer to count number of tabs
        int tabCount;
        ResturantsObject resturantsObject;

        //Constructor to the class
        public ViewPagerAdapter(FragmentManager fm, int tabCount, ResturantsObject resturantsObject) {
            super(fm);
            //Initializing tab count
            this.tabCount = tabCount;
            this.resturantsObject = resturantsObject;
        }

        //Overriding method getItem
        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            //Returning the current tabs
            switch (position) {
                case 0:
                    MenuFragment menuFragment = new MenuFragment();
                    bundle.putSerializable("allMenus", resturantsObject.getMenuCategory());
                    menuFragment.setArguments(bundle);
                    return menuFragment;
                case 1:
                    ReviewsFragment reviewsFragment = new ReviewsFragment();
                    bundle.putInt("restID", resturantID);
                    reviewsFragment.setArguments(bundle);
                    return reviewsFragment;
                case 2:
                    InfoActivity infoActivity = new InfoActivity();
                    bundle.putSerializable("resturntsDetail", resturantsObject.getRestaurantDetails());
                    infoActivity.setArguments(bundle);
                    return infoActivity;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return tabCount;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String title = null;
            if (position == 0) {
                title = getString(R.string.menu);
            } else if (position == 1) {
                title = getString(R.string.reviews);
            } else if (position == 2) {
                title = getString(R.string.info);
            }
            return title;
        }
    }

    @OnClick(R.id.cartToolbarLayout)
    void setCartToolbarLayout() {
        Intent intent=new Intent(this,CheckOutActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.left)
    void setLeft() {
        onBackPressed();
    }

    @OnClick(R.id.cancelPopUp)
    void setCancelPopUp() {
        setAddItemToCartPopUpVisiblity("cancel", 0, 0);
    }

    String itemID, itemQuantity, itemName, itemRest, itemPrice;

    public void setAddItemToCartPopUpVisiblity(String status, int childPosition, int parentPosition) {
        if (status.equals("subItemDisplay")) {
            addItemToCartPopUp.setVisibility(View.VISIBLE);
            innerItemsLayout.setVisibility(View.VISIBLE);
            loginButton.setText(getString(R.string.addToCartPlus));
            itemRest = menuCategory.get(parentPosition).getMenuCategoryName().trim();
            itemTitleText.setText(menuCategory.get(parentPosition).getMenuCategoryName().trim());
            callsubItemAdapter(childPosition, parentPosition);
        } else if (status.equals("itemDisplay")) {
            addItemToCartPopUp.setVisibility(View.VISIBLE);
            innerItemsLayout.setVisibility(View.GONE);
            loginButton.setText(getString(R.string.addToCartPlus));
            itemTitleText.setText(menuCategory.get(parentPosition).getMenuCategoryName().trim());
            subItemID = String.valueOf(menuCategory.get(parentPosition).getMenuCategoryId());
            subItemName = menuCategory.get(parentPosition).getMenuCategoryName();
////            subItemPrice = menuCategory.get(parentPosition).get
//            for (int i = 0; i < addItemsToCartModelArrayList.size(); i++) {
//                if (itemId == Integer.parseInt(addItemsToCartModelArrayList.get(i).getId())) {
//                    addItemsToCartModelArrayList.remove(i);
//                }/*else if (i == addItemsToCartModelArrayList.size()) {
//                if (itemId == Integer.parseInt(addItemsToCartModelArrayList.get(i).getId())) {
//                    addItemsToCartModelArrayList.add(new AddItemsToCartModel(subItemID, itemRest, subItemName, addOnPrice, String.valueOf(quantity)));
//                }
//            }*/
//            }
        } else if (status.equals("cancel")) {
            addItemToCartPopUp.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
        if (searchFlag) {
            setOnSearch(false);
        } else {
            super.onBackPressed();
        }
    }

    @OnClick(R.id.searchBtn)
    void searchBtn() {
        if (searchFlag) {
            setOnSearch(false);
        } else {
            setOnSearch(true);
        }
    }

    void callsubItemAdapter(int childPosition, int parentPosition) {
        ArrayList<MenuAddOnObject> addOn = menuCategory.get(parentPosition).getMenus().get(childPosition).getAddOn();
        MenuExpandableSubItemAdapter menuExpandableSubItemAdapter = new MenuExpandableSubItemAdapter(this, addOn);
        innerItemsRecyclerView.setAdapter(menuExpandableSubItemAdapter);
    }

    void setOnSearch(boolean status) {
        if (status) {
            toolbarText.setEnabled(true);
            searchBarLayout.setBackgroundResource(R.drawable.search_background);
            left.setVisibility(View.GONE);
            searchFlag = status;
        } else {
            searchFlag = status;
            toolbarText.setEnabled(false);
            left.setVisibility(View.VISIBLE);
            searchBarLayout.setBackgroundResource(android.R.color.transparent);
        }
    }

    private ArrayList<MenuDetailCategoryObject> menuCategory = new ArrayList<>();

    void callingResturantDetail(String tokenKey, String resturantID) {
        AppCommon.getInstance(this).setNonTouchableFlags(this);
        if (AppCommon.getInstance(DetailActivity.this).isConnectingToInternet(DetailActivity.this)) {
            progressBar.setVisibility(View.VISIBLE);
            //  final String token = myFirebaseInstanceIDService.getDeviceToken();
            ResturantListEnity resturantListEnity = new ResturantListEnity(tokenKey, resturantID);
            FoodMonkeyAppService foodMonkeyAppService = ServiceGenerator.createService(FoodMonkeyAppService.class);
            call = foodMonkeyAppService.getResturanetDetailAndMenus(resturantListEnity);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(DetailActivity.this).clearNonTouchableFlags(DetailActivity.this);
                    if (response.code() == 200) {
                        progressBar.setVisibility(View.GONE);
                        RestDetailMenuResponse restDetailMenuResponse = (RestDetailMenuResponse) response.body();
                        if (response.body() != null) {
                            if (restDetailMenuResponse.getCode().equals("200")) {
                                setMenuAdapter(restDetailMenuResponse.getResturants());
                                setResturantDetail(restDetailMenuResponse.getResturants().getRestaurantDetails());
                                menuCategory = restDetailMenuResponse.getResturants().getMenuCategory();
//                                restutantListObject = customerHomeResponse.getRestutantList();
//                                setAdapter(restutantListObject);
//                            User user = registrationResponse.getUserEntity();

//                            try {
//                                AppCommon.getInstance(HomeActivity.this).setUserLatitude(Double.parseDouble(latitude));
//                                AppCommon.getInstance(HomeActivity.this).setUserLongitude(Double.parseDouble(longitude));
//                            } catch (Exception e) {
//
//                            }
                            } else {
                                AppCommon.showDialog(DetailActivity.this, restDetailMenuResponse.getMessage());
                            }
                        }
                    } else {
                        AppCommon.showDialog(DetailActivity.this, getString(R.string.serverError));
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    AppCommon.getInstance(DetailActivity.this).clearNonTouchableFlags(DetailActivity.this);
                    progressBar.setVisibility(View.GONE);
                    if (t instanceof JsonSyntaxException) {
                        AppCommon.showDialog(DetailActivity.this, "No resturant found");
                    } else {
                        AppCommon.showDialog(DetailActivity.this, getResources().getString(R.string.network_error));

                    }
                }
            });
        } else {
            AppCommon.getInstance(DetailActivity.this).clearNonTouchableFlags(DetailActivity.this);
            AppCommon.showDialog(this, getResources().getString(R.string.network_error));
        }
    }

    private void setMenuAdapter(ResturantsObject resturantsObject) {
        //Creating our pager adapter
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), resturantsObject);

        //Adding adapter to pager
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @OnClick(R.id.loginButton)
    void setLoginButton() {
        addItemsToCartModelArrayList.add(new AddItemsToCartModel(subItemID, itemRest, subItemName, subItemPrice, String.valueOf(quantity)));

        AppCommon.getInstance(this).setAddToCartObject(addItemsToCartModelArrayList);
        Toast.makeText(this, "Added to cart", Toast.LENGTH_SHORT).show();
        setAddItemToCartPopUpVisiblity("cancel", 0, 0);
        totalNoOfItemInCart.setText(addItemsToCartModelArrayList.size()+"");

//        if (loginButton.getText().toString().trim().toLowerCase().equals(getString(R.string.addToCartPlus).toLowerCase())) {
//            startActivity(new Intent(this, CheckOutActivity.class));
//        } else {
//            startActivity(new Intent(this, LoginActivity.class));
//        }
    }

    String subItemID;
    String subItemName;
    String subItemPrice;

    public void receiveDataSubItemAdapter(Integer itemId, String addonName, String addOnPrice) {
        subItemID = String.valueOf(itemId);
        subItemName = addonName;
        subItemPrice = addOnPrice;
        for (int i = 0; i < addItemsToCartModelArrayList.size(); i++) {
            if (itemId == Integer.parseInt(addItemsToCartModelArrayList.get(i).getId())) {
                addItemsToCartModelArrayList.remove(i);
            }/*else if (i == addItemsToCartModelArrayList.size()) {
                if (itemId == Integer.parseInt(addItemsToCartModelArrayList.get(i).getId())) {
                    addItemsToCartModelArrayList.add(new AddItemsToCartModel(subItemID, itemRest, subItemName, addOnPrice, String.valueOf(quantity)));
                }
            }*/
        }
    }

    public void removeDataSubItemAdapter(Integer itemId, String addonName, String addOnPrice) {
        for (int i = 0; i < addItemsToCartModelArrayList.size(); i++) {
            if (itemId == Integer.parseInt(addItemsToCartModelArrayList.get(i).getId())) {
                addItemsToCartModelArrayList.remove(i);
            }
        }
    }


    //-----------control item number in cart here----------------

    int quantity = 1;

    @OnClick(R.id.removeValue)
    void setDecreaseQuantity() {
        if (quantity > 0) {
            quantity--;
        }
        displayQuantityValue.setText("" + quantity);
    }

    @OnClick(R.id.addValue)
    void setIncreaseQuantity() {
        quantity++;
        displayQuantityValue.setText("" + quantity);
    }


}
