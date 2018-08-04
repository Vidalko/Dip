package com.example.vidal.dipl.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.vidal.dipl.R;
import com.example.vidal.dipl.entities.ResultFindObject;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ResultFindAdapter extends RecyclerView.Adapter<ResultFindViewHolder>{

    private Context context;
    private List<ResultFindObject> allTours;

    public ResultFindAdapter(Context context, List<ResultFindObject> allTours) {
        this.context = context;
        this.allTours = allTours;
    }

    @Override
    public ResultFindViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tour, parent, false);
        return new  ResultFindViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ResultFindViewHolder holder, int position) {
        ResultFindObject infoTour = allTours.get(position);
        holder.tvResort.setText(infoTour.getTourResort().toString());
        holder.tvRating.setText(infoTour.getTourRating().toString());
        holder.tvDate.setText(infoTour.getTourDate().toString() + " - " + infoTour.getTourDuration() + " " + context.getResources().getString(R.string.text_duration));
        Picasso.with(context).load(infoTour.getTourImages().toString()).into(holder.imResort);
        try {
            Float f1 = new Float(infoTour.getTourRating().toString());
            System.out.println(f1);
            holder.rBar.setRating(f1);
        } catch (NumberFormatException e) {
            System.err.println("Неверный формат строки!");
        }
        holder.btnPrice.setText(infoTour.getTourPrice().toString() + " " + context.getResources().getString(R.string.text_grn));
        holder.tvCity.setText(infoTour.getTourCity().toString());
        if(infoTour.getTourHot().toString().isEmpty())
            holder.hotTour.setVisibility(View.INVISIBLE);
        else
            holder.hotTour.setVisibility(View.VISIBLE);

        ////imageView.setImageResource(playlistObject.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return allTours.size();
    }

}
