package com.pushnotification;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    TextView tvProfile,tvAllUsers,tvNotification;
    ViewPager viewPager;
    PageViewAdapter pageViewAdapter;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        tvProfile = findViewById(R.id.tv_profile);
        tvAllUsers = findViewById(R.id.tv_all_users);
        viewPager = findViewById(R.id.viewPager);
        tvNotification = findViewById(R.id.tv_notification);
        pageViewAdapter = new PageViewAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pageViewAdapter);
        setUpViewPager();
        tvProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0);
            }
        });
        tvAllUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
            }
        });
        tvNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(2);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser==null)
        {
            Intent intent= new Intent(this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void setUpViewPager() {
    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onPageSelected(int position) {
            changePageText(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void changePageText(int position) {

        switch (position)
        {
            case 0:
                tvProfile.setTextColor(getColor(R.color.tabSelectedColor));
                tvAllUsers.setTextColor(getColor(R.color.tabUnSelectedColor));
                tvNotification.setTextColor(getColor(R.color.tabUnSelectedColor));
                break;
            case 1:
                tvAllUsers.setTextColor(getColor(R.color.tabSelectedColor));
                tvProfile.setTextColor(getColor(R.color.tabUnSelectedColor));
                tvNotification.setTextColor(getColor(R.color.tabUnSelectedColor));
                break;
            case 2:
                tvNotification.setTextColor(getColor(R.color.tabSelectedColor));
                tvProfile.setTextColor(getColor(R.color.tabUnSelectedColor));
                tvAllUsers.setTextColor(getColor(R.color.tabUnSelectedColor));
                break;
        }
    }

}
