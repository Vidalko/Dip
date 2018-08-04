package com.example.vidal.dipl.adapter;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.vidal.dipl.R;
import com.example.vidal.dipl.fragment.FindTourFragment;
import com.example.vidal.dipl.fragment.LastMinuteTourFragment;


public class CustomFragmentPageAdapter extends FragmentPagerAdapter{

    private static final String TAG = CustomFragmentPageAdapter.class.getSimpleName();

    private static final int FRAGMENT_COUNT = 2;
    private Context context;

    public CustomFragmentPageAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;

    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FindTourFragment();
            case 1:
                return new LastMinuteTourFragment();



        }
        return null;
    }

    @Override
    public int getCount() {
        return FRAGMENT_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return context.getResources().getString(R.string.tab_find_tour);
            case 1:
                return context.getResources().getString(R.string.tab_last_minute_tour);

        }
        return null;
    }
}
