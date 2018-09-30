package com.kartik.project.foodmonkey;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kartik.project.foodmonkey.Fragments.InfoActivity;
import com.kartik.project.foodmonkey.Fragments.MenuFragment;
import com.kartik.project.foodmonkey.Fragments.ReviewsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.left)
    ImageView left;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @BindView(R.id.cartToolbarLayout)
    RelativeLayout cartToolbarLayout;

    @BindView(R.id.addItemToCartPopUp)
    LinearLayout addItemToCartPopUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        toolbarText.setText("Pepe's Piri Piri");
        setTabs();
        //Creating our pager adapter
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
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

        //Constructor to the class
        public ViewPagerAdapter(FragmentManager fm, int tabCount) {
            super(fm);
            //Initializing tab count
            this.tabCount = tabCount;
        }

        //Overriding method getItem
        @Override
        public Fragment getItem(int position) {
            //Returning the current tabs
            switch (position) {
                case 0:
                    MenuFragment menuFragment = new MenuFragment();
                    return menuFragment;
                case 1:
                    ReviewsFragment reviewsFragment = new ReviewsFragment();
                    return reviewsFragment;
                case 2:
                    InfoActivity infoActivity = new InfoActivity();
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
        startActivity(new Intent(this, CheckOutActivity.class));
    }

    @OnClick(R.id.left)
    void setLeft() {
        onBackPressed();
    }

    @OnClick(R.id.cancelPopUp)
    void setCancelPopUp() {
        setAddItemToCartPopUpVisiblity(false);
    }

    public void setAddItemToCartPopUpVisiblity(boolean status) {
        if (status) {
            addItemToCartPopUp.setVisibility(View.VISIBLE);
        } else {
            addItemToCartPopUp.setVisibility(View.GONE);
        }
    }
}
