package com.example.vidal.dipl.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vidal.dipl.R;
import com.example.vidal.dipl.entities.CountryObject;

import java.util.Collections;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryViewHolder>{

    private static final String TAG = CountryAdapter.class.getSimpleName();

    private Context context;
    private LayoutInflater inflater;
    private List<CountryObject> country = Collections.emptyList();

    public CountryAdapter(Context context, List<CountryObject> country) {
        this.context = context;
        inflater= LayoutInflater.from(context);
        this.country = country;
    }

    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country, parent, false);
        View view = inflater.inflate(R.layout.item_country, parent, false);
        CountryViewHolder countryViewHolder = new CountryViewHolder(view);
        return countryViewHolder;
    }

    @Override
    public void onBindViewHolder(CountryViewHolder holder, int position) {
        CountryObject countrylistObject = country.get(position);
        holder.countrylistTitle.setText(countrylistObject.getCountrylistTitle());

    }

    @Override
    public int getItemCount() {
        return country.size();
    }
}