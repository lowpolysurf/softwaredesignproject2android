package com.example.softwaredesignproject2android;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.ViewHolder> {

    private List<TRIPS> tripList;

    public TripAdapter(List<TRIPS> tripList) {
        this.tripList = tripList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tripNameText;
        TextView dateText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tripNameText = itemView.findViewById(R.id.tripNameText);
            dateText = itemView.findViewById(R.id.dateText);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trip_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TRIPS trip = tripList.get(position);
        android.util.Log.d("TripAdapter", "Binding trip: " + trip.tripName + " at position: " + position);

        holder.tripNameText.setText(trip.tripName);
        holder.dateText.setText(
                trip.startDate + " - " + trip.endDate
        );
    }

    @Override
    public int getItemCount() {
        return tripList.size();
    }
}