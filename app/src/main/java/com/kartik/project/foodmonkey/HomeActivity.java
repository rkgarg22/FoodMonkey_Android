package com.kartik.project.foodmonkey;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.kartik.project.foodmonkey.Adapters.NavigationMenuAdapter;
import com.kartik.project.foodmonkey.Adapters.RestaurantAdapter;
import com.kartik.project.foodmonkey.Models.NavigationModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.left)
    ImageView left;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @BindView(R.id.navigationRecyclerView)
    RecyclerView navigationRecyclerView;

    @BindView(R.id.restaurantRecyclerView)
    RecyclerView restaurantRecyclerView;

    @BindView(R.id.menuRecyclerView)
    RecyclerView menuRecyclerView;

    @BindView(R.id.orderRecyclerView)
    RecyclerView orderRecyclerView;


    ArrayList<NavigationModel> navigationModelArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        left.setImageResource(R.drawable.menu);
        left.setVisibility(View.VISIBLE);
        toolbarText.setText(getString(R.string.foodMonkey));
        navigationRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        setNavigationData();
        NavigationMenuAdapter navigationMenuAdapter = new NavigationMenuAdapter(this, navigationModelArrayList);
        navigationRecyclerView.setAdapter(navigationMenuAdapter);

        restaurantRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        restaurantRecyclerView.setAdapter(new RestaurantAdapter(this, getString(R.string.restaurant)));

        menuRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        menuRecyclerView.setAdapter(new RestaurantAdapter(this, getString(R.string.takeOut)));

        orderRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        orderRecyclerView.setAdapter(new RestaurantAdapter(this, getString(R.string.yourOrder)));
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
        Intent intent = new Intent(HomeActivity.this, HomeListingActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.menuSeeMore)
    void setMenuSeeMore() {
        Intent intent = new Intent(HomeActivity.this, HomeListingActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.orderSeeMore)
    void setOrderSeeMore() {
        Intent intent = new Intent(HomeActivity.this, HomeListingActivity.class);
        startActivity(intent);
    }
}
