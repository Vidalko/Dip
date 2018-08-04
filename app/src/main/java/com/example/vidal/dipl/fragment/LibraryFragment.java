package com.example.vidal.dipl.fragment;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vidal.dipl.R;
import com.example.vidal.dipl.adapter.CustomFragmentPageAdapter;


public class LibraryFragment extends Fragment {

    private static final String TAG = LibraryFragment.class.getSimpleName();

    private TabLayout tabLayout;
    private ViewPager viewPager;


    public LibraryFragment() {
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library, container, false);
        getActivity().setTitle("Optima Tour");
        tabLayout = (TabLayout)view.findViewById(R.id.tabs);
        viewPager = (ViewPager)view.findViewById(R.id.view_pager);
        tabLayout.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager.setAdapter(new CustomFragmentPageAdapter(getChildFragmentManager(), getContext()));
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }
}
