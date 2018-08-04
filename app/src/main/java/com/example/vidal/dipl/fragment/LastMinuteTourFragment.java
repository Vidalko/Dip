package com.example.vidal.dipl.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vidal.dipl.ActivityCountry;
import com.example.vidal.dipl.ActivityDeparture;
import com.example.vidal.dipl.ActivityFood;
import com.example.vidal.dipl.ActivityPeople;
import com.example.vidal.dipl.ActivitySelectDate;
import com.example.vidal.dipl.R;
import com.example.vidal.dipl.ResultFindTourActivity;

import static android.app.Activity.RESULT_OK;


public class LastMinuteTourFragment extends Fragment {

    EditText eTextCountry, eTextCountryDepart, eTextDate;
    TextView tvCountry, tvCountryDepart, tvTextDate;
    Button btnFindTour;
    ImageView imFood;
    ImageView imPeople;

    String food = "";
    String people = "";

    public LastMinuteTourFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //getActivity().setTitle("Optima Tour");
        View view = inflater.inflate(R.layout.fragment_hot_tour, container,false);

        tvCountry = (TextView)view.findViewById(R.id.tvCountry);
        tvCountryDepart = (TextView)view.findViewById(R.id.tvCountry);
        tvTextDate = (TextView)view.findViewById(R.id.tvCountry);

        eTextCountry = (EditText) view.findViewById(R.id.eTextCountry);
        eTextCountryDepart = (EditText)view.findViewById(R.id.eTextCountryDepart);
        eTextDate = (EditText)view.findViewById(R.id.eTextDate);

        btnFindTour = (Button)view.findViewById(R.id.btnFindTour);
        imFood = (ImageView)view.findViewById(R.id.imFood);
        imPeople = (ImageView)view.findViewById(R.id.imPeople);
        //imRating = (ImageView)view.findViewById(R.id.imRating);

        // eTextCountry.setHint("country");

        eTextCountry.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityCountry.class);
                startActivityForResult(intent, 1);
            }
        });
        eTextCountryDepart.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityDeparture.class);
                startActivityForResult(intent, 2);
            }
        });
        eTextDate.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivitySelectDate.class);
                startActivityForResult(intent, 3);
            }
        });
        imFood.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityFood.class);
                startActivityForResult(intent, 4);
            }
        });
        imPeople.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityPeople.class);
                startActivityForResult(intent, 5);
            }
        });
        btnFindTour.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                validationFields();


            }
        });


        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        if(data == null) return;
        String val = data.getStringExtra("value");
        if(val == null || resultCode != RESULT_OK) return;
        switch (requestCode) {
            case 1:
                eTextCountry.setText(val);
                break;
            case 2:
                eTextCountryDepart.setText(val);
                break;
            case 3:
                eTextDate.setText(val);
                break;
            case 4:
                food = val;
                break;
            case 5:
                people = val;
                break;

        }
    }

    private void validationFields() {

        String strTextCountry=eTextCountry.getText().toString();
        String strTextDepart=eTextCountryDepart.getText().toString();
        String strTextDate=eTextDate.getText().toString();
        // String toData=to.getText().toString();


        if(strTextCountry.isEmpty()){
            Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.text_country), Toast.LENGTH_SHORT).show();
        }else {
            if(strTextDepart.isEmpty()){
                Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.text_depart), Toast.LENGTH_SHORT).show();
            }else {
                if(strTextDate.isEmpty()){
                    Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.text_date), Toast.LENGTH_SHORT).show();
                }else {
                    if(people == ""){
                        Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.text_choose_people), Toast.LENGTH_SHORT).show();
                    }else {
                        if (food == "") {
                            Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.text_choose_food), Toast.LENGTH_SHORT).show();
                        } else {

                            Intent intent = new Intent(getActivity(), ResultFindTourActivity.class);
                            intent.putExtra("Country", eTextCountry.getText().toString());
                            intent.putExtra("CountryDepart", eTextCountryDepart.getText().toString());
                            intent.putExtra("Date", eTextDate.getText().toString());
                            intent.putExtra("Food", food);
                            intent.putExtra("People", people);
                            intent.putExtra("Empty", "hot");
                            intent.putExtra("TableName", "hotTours");
                            startActivity(intent);

                        }
                    }
                }
            }
        }
    }


}
