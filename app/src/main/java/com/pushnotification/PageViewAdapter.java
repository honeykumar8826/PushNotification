package com.pushnotification;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

class PageViewAdapter   extends FragmentPagerAdapter {
    public static String TAG ="Adapter";
    public PageViewAdapter(FragmentManager fm) {
        super(fm);

    }
    @Override
    public Fragment getItem(int position) {
        Log.i(TAG, "getItem: ");
        switch (position)
        {
            case 0: return new ProfileFragment();
            case 1: return new AllUsersFragment();
            case 2: return new NotificationFragment();
            default:return  null;
        }
    }
    @Override
    public int getCount() {
        Log.i(TAG, "getCount: ");
        return 3;
    }
}
