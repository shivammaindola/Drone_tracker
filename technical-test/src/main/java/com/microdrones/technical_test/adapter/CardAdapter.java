package com.microdrones.technical_test.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.model.LatLng;
import com.microdrones.technical_test.model.CardModel;
import com.microdrones.technical_test.view.MapActivity;
import com.microdrones.test.example.view.R;

import java.io.Serializable;
import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.Viewholder> {

    private Context context;
    private ArrayList<CardModel> courseModelArrayList;

    // Constructor
    public CardAdapter(Context context, ArrayList<CardModel> courseModelArrayList) {
        this.context = context;
        this.courseModelArrayList = courseModelArrayList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        // to set data to textview and imageview of each card layout
        final CardModel model = courseModelArrayList.get(position);
        holder.missionNo.setText(model.getMission().name);
        holder.missionSuccess.setText(model.getMissionSucess());
        String initalPos=model.getMission().points.get(0).latitude+",\n"+model.getMission().points.get(0).longitude;

        int size=model.getMission().points.size();
        String finalPos=model.getMission().points.get(size-1).latitude+",\n"+model.getMission().points.get(size-1).longitude;
        holder.initialPos.setText(initalPos);
        holder.finalPos.setText(finalPos);
        holder.progress.setMax((int) model.getCapacity());
        holder.progress.setProgress((int) model.getProgress());

        if(model.getMissionSucess().equals("Successful"))
            holder.missionSuccess.setTextColor(Color.parseColor("#05e614"));
        else
        holder.missionSuccess.setTextColor(Color.parseColor("#fc0313"));



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<LatLng> locationArrayList = new ArrayList<>();
                //add lat lang
                for(int i=0;i<model.getMission().points.size();i++){
                    locationArrayList.add(new LatLng(model.getMission().points.get(i).latitude, model.getMission().points.get(i).longitude));
                }
                Bundle bundle = new Bundle();
                bundle.putSerializable("locations", (Serializable) locationArrayList);
                bundle.putSerializable("status", (Serializable) model.getMissionSucess());

                Intent intent = new Intent(context, MapActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return courseModelArrayList.size();
    }

    // View holder class for initializing of
    // your views such as TextView and Imageview.
    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView missionNo, initialPos,finalPos, missionSuccess;
        private ProgressBar progress;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            missionNo = itemView.findViewById(R.id.missionNo);
            missionSuccess = itemView.findViewById(R.id.missionSuccess);
            initialPos = itemView.findViewById(R.id.initialPos);
            finalPos = itemView.findViewById(R.id.finalPos);
            progress = itemView.findViewById(R.id.progressBar);
        }
    }
}
