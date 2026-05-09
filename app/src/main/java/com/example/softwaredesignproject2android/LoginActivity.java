package com.example.softwaredesignproject2android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    TravelDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        db = TravelDatabase.getInstance(this);
        try { // hardcoded admin
            USER admin = db.userDAO().getLoggedInUser("admin2");

            if (admin == null) {
                USER newAdmin = new USER("admin2", "admin2", 1);
                db.userDAO().insertUser(newAdmin);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try { // hardcoded admin
            USER admin = db.userDAO().getLoggedInUser("testuser1");

            if (admin == null) {
                USER newAdmin = new USER("testuser1", "testuser1", 0);
                db.userDAO().insertUser(newAdmin);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void loginUser(View view) {
        EditText usernameInput = findViewById(R.id.editTextText);
        EditText passwordInput = findViewById(R.id.editTextTextPassword);

        String username = usernameInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        USER user = db.userDAO().getLoggedInUser(username);

        if (user != null && user.password.equals(password)) {
            Intent intent = new Intent(this, UserLandingPage.class);
            intent.putExtra("username", username);
            // convert int → boolean
            boolean isAdmin = user.isAdmin == 1;
            intent.putExtra("isAdmin", isAdmin);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Invalid Login", Toast.LENGTH_SHORT).show();
        }
    }
}