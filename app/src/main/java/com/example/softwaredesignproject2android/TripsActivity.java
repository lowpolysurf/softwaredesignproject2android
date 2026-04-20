package com.example.softwaredesignproject2android;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class TripsActivity extends AppCompatActivity {

    TravelDatabase db;
    String username;
    TextView tripsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips);


        db = TravelDatabase.getInstance(this);

        username = getIntent().getStringExtra("username");

        tripsText = findViewById(R.id.tripsText);

        loadTrips();
    }

    private void loadTrips() {
        List<TRIPS> trips = db.tripsDAO().getUserTrips(username);

        StringBuilder builder = new StringBuilder();
        builder.append("Logged in as: ").append(username).append("\n\n");

        if (trips == null || trips.isEmpty()) {
            builder.append("No trips yet.");
        } else {
            for (TRIPS trip : trips) {
                builder.append("Trip: ").append(trip.tripName).append("\n");
                builder.append("Start Date: ").append(trip.startDate).append("\n");
                builder.append("End Date: ").append(trip.endDate).append("\n\n");
            }
        }

        tripsText.setText(builder.toString());
    }
}