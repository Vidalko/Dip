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

public class ContactFragment extends Fragment {
    private static final String TAG = ContactFragment.class.getSimpleName();

    Button call;
    TextView contactInfo;


    public ContactFragment() {
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        getActivity().setTitle(R.string.action_contacts);
        contactInfo = (TextView) view.findViewById(R.id.contactInfo);
        call = (Button) view.findViewById(R.id.email);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + "+380937028888"));
                startActivity(intent);

            }
        });

        return view;
    }
}
