package com.example.softwaredesignproject2android; //CM415026

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executors;

@Database(
        entities = {USER.class, TRIPS.class, LEGS.class},
        version = 3,
        exportSchema = false
)
public abstract class TravelDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "travel_database";

    private static volatile TravelDatabase INSTANCE;

    public abstract UserDAO userDAO();

    public abstract TripsDAO tripsDAO();

    public abstract LegsDAO legsDAO();

    public static TravelDatabase getInstance(Context context) {

        if (INSTANCE == null) {

            synchronized (TravelDatabase.class) {

                if (INSTANCE == null) {

                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    TravelDatabase.class,
                                    DATABASE_NAME
                            )
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }

        return INSTANCE;
    }
}
