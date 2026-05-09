package com.example.softwaredesignproject2android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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

    Button newTripBtn, saveTripBtn, toolsBtn;
    LinearLayout addTripPanel;

    EditText inputStartDate, inputEndDate, inputLocation;

    // admin variables
    boolean isAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_landing_page);
        isAdmin = getIntent().getBooleanExtra("isAdmin", false); // for admin

        // DB + user
        db = TravelDatabase.getInstance(this);
        username = getIntent().getStringExtra("username");

        // Views
        tripRecyclerView = findViewById(R.id.tripRecyclerView);
        newTripBtn = findViewById(R.id.newTripBtn);
        saveTripBtn = findViewById(R.id.saveTripBtn);
        toolsBtn = findViewById(R.id.toolsBtn); // admin

        addTripPanel = findViewById(R.id.addTripPanel);

        inputStartDate = findViewById(R.id.inputStartDate);
        inputEndDate = findViewById(R.id.inputEndDate);
        inputLocation = findViewById(R.id.inputLocation);

        TextView welcomeText = findViewById(R.id.editText);
        welcomeText.setText("Welcome " + username);

        if (isAdmin) {
            toolsBtn.setVisibility(View.VISIBLE);
        } else {
            toolsBtn.setVisibility(View.GONE);
        }

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
            Log.d("UserLandingPage", "Save Trip button clicked");

            String location = inputLocation.getText().toString().trim();
            String startDate = inputStartDate.getText().toString().trim();
            String endDate = inputEndDate.getText().toString().trim();

            if (location.isEmpty() || startDate.isEmpty() || endDate.isEmpty()) {
                Log.w("UserLandingPage", "One or more fields are empty");
                android.widget.Toast.makeText(this, "Please fill in all fields", android.widget.Toast.LENGTH_SHORT).show();
                return;
            }

            TRIPS trip = new TRIPS();
            trip.username = username;
            trip.tripName = location;
            trip.startDate = startDate;
            trip.endDate = endDate;

            Log.d("UserLandingPage", "Saving trip: " + trip.tripName + " for user: " + trip.username);

            new Thread(() -> {
                try {
                    db.tripsDAO().insertTrip(trip);
                    Log.d("UserLandingPage", "Trip inserted successfully");

                    runOnUiThread(() -> {
                        android.widget.Toast.makeText(this, "Trip Saved!", android.widget.Toast.LENGTH_SHORT).show();
                        loadTrips();
                        inputLocation.setText("");
                        inputStartDate.setText("");
                        inputEndDate.setText("");
                        addTripPanel.setVisibility(View.GONE);
                    });
                } catch (Exception e) {
                    Log.e("UserLandingPage", "Error inserting trip", e);
                    runOnUiThread(() -> {
                        android.widget.Toast.makeText(this, "Error saving trip", android.widget.Toast.LENGTH_SHORT).show();
                    });
                }
            }).start();
        });

        // admin
        toolsBtn.setOnClickListener(v -> {
            // This will take admin to tool page
            Intent intent = new Intent(UserLandingPage.this, ToolsActivity.class);
            startActivity(intent);
        });
    }

    private void loadTrips() {
        Log.d("UserLandingPage", "Loading trips for user: " + username);
        new Thread(() -> {
            tripList = db.tripsDAO().getUserTrips(username);

            if (tripList == null) {
                tripList = new ArrayList<>();
            }
            Log.d("UserLandingPage", "Loaded " + tripList.size() + " trips");

            runOnUiThread(() -> {
                adapter = new TripAdapter(tripList);
                tripRecyclerView.setAdapter(adapter);
            });
        }).start();
    }
}