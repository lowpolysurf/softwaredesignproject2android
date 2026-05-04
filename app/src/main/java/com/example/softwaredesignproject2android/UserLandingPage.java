package com.example.softwaredesignproject2android;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserLandingPage extends AppCompatActivity {

    /* Couple of errors, right now the RecycleView might need some more wiring
    up and it's own xml file, Also i think we should use strings instead of ints
    for our trips entity, I did so in TRIPS.java, but I'm sure it will mess with
    TripsDAO so that will need to be adjusted too if not properly done so*/
    // Just wanna say sorry for barely getting to this, I just was so busy during
    // the weekend as I had work, work for another group project, and study for
    // a quiz i have on Tuesday.
    RecyclerView tripRecyclerView;
    TripAdapter adapter;
    List<TRIPS> tripList;

    TravelDatabase db;
    String username;

    Button newTripBtn, saveTripBtn;
    LinearLayout addTripPanel;

    EditText inputDate, inputLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_landing_page);

        // DB + user
        db = TravelDatabase.getInstance(this);
        username = getIntent().getStringExtra("username");

        // Views
        tripRecyclerView = findViewById(R.id.tripRecyclerView);
        newTripBtn = findViewById(R.id.newTripBtn);
        saveTripBtn = findViewById(R.id.saveTripBtn);

        addTripPanel = findViewById(R.id.addTripPanel);

        inputDate = findViewById(R.id.inputDate);
        inputLocation = findViewById(R.id.inputLocation);

        // RecyclerView setup
        tripRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadTrips();

        // toggle form
        newTripBtn.setOnClickListener(v -> {
            addTripPanel.setVisibility(
                    addTripPanel.getVisibility() == View.VISIBLE
                            ? View.GONE
                            : View.VISIBLE
            );
        });

        // save trip
        saveTripBtn.setOnClickListener(v -> {

            TRIPS trip = new TRIPS();
            trip.username = username;
            trip.tripName = inputLocation.getText().toString();
            trip.startDate = inputDate.getText().toString();
            trip.endDate = inputDate.getText().toString();

            db.tripsDAO().insertTrip(trip);

            loadTrips();

            addTripPanel.setVisibility(View.GONE);
        });
    }

    private void loadTrips() {
        tripList = db.tripsDAO().getUserTrips(username);

        if (tripList == null) {
            tripList = new ArrayList<>();
        }

        adapter = new TripAdapter(tripList);
        tripRecyclerView.setAdapter(adapter);
    }
}