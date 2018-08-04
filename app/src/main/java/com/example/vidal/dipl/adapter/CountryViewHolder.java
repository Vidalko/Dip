package com.example.vidal.dipl.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vidal.dipl.R;

public class CountryViewHolder extends RecyclerView.ViewHolder{
    public TextView countrylistTitle;


    public CountryViewHolder(View itemView) {
        super(itemView);

        countrylistTitle = (TextView)itemView.findViewById(R.id.tv_country_name);
    }

}
