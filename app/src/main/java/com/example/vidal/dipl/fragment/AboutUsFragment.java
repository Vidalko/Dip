package com.example.vidal.dipl.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.vidal.dipl.R;

public class AboutUsFragment extends Fragment {
    private static final String TAG = AboutUsFragment.class.getSimpleName();


    TextView contactInfo;


    public AboutUsFragment() {
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);
        getActivity().setTitle(R.string.action_about);
        contactInfo = (TextView) view.findViewById(R.id.contactInfo);


        return view;
    }
}
