package com.example.vidal.dipl.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.vidal.dipl.R;

public class ResultFindViewHolder  extends RecyclerView.ViewHolder{
    public TextView tvRating;
    public TextView tvResort;
    public TextView tvDate;
    public RatingBar rBar;
    public ImageView imResort;
    public ImageView hotTour;
    public Button btnPrice;
    public TextView tvCity;

    public ResultFindViewHolder(View itemView, TextView tvRating, TextView tvResort, ImageView imResort, TextView tvDate, RatingBar rBar, Button btnPrice, TextView tvCity, ImageView hotTour) {
        super(itemView);
        this.tvRating = tvRating;
        this.tvResort = tvResort;
        this.imResort = imResort;
        this.tvDate = tvDate;
        this.rBar = rBar;
        this.btnPrice = btnPrice;
        this.tvCity = tvCity;
        this.hotTour = hotTour;
    }

    public ResultFindViewHolder(View itemView) {
        super(itemView);

        tvRating = (TextView)itemView.findViewById(R.id.tvRating);
        tvResort = (TextView)itemView.findViewById(R.id.tvResort);
        imResort = (ImageView)itemView.findViewById(R.id.imResort);
        tvDate = (TextView)itemView.findViewById(R.id.tvDate);
        rBar = (RatingBar)itemView.findViewById(R.id.ratingBar);
        btnPrice = (Button) itemView.findViewById(R.id.btnPrice);
        tvCity = (TextView) itemView.findViewById(R.id.tvCity);
        hotTour = (ImageView)itemView.findViewById(R.id.imHotTour);
    }
}
