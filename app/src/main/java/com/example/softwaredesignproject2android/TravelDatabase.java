package com.example.softwaredesignproject2android;//CM415026

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.content.Context;

import java.util.concurrent.Executors;

@Database(entities = {USER.class, TRIPS.class, LEGS.class}, version = 1)
public abstract class TravelDatabase extends RoomDatabase{
    public abstract UserDAO userDAO();
    public abstract TripsDAO tripsDAO();
    public abstract LegsDAO legsDAO();

    private static TravelDatabase INSTANCE;

    public static TravelDatabase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    TravelDatabase.class,
                    "travel_database"
            ).allowMainThreadQueries().addCallback(databaseCallback).build();
        }
        return INSTANCE;
    }

    private static final Callback databaseCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);

            Executors.newSingleThreadExecutor().execute(() -> {

                TravelDatabase database = INSTANCE;

                if (database != null){
                    UserDAO userDAO = database.userDAO();

                    USER test = new USER("testuser1", "testuser1", 0);
                    USER admin = new USER("admin2", "admin2", 1);

                    userDAO.insertUser(test);
                    userDAO.insertUser(admin);
                }
            });
        }
    };
}
