package com.example.softwaredesignproject2android; //CM415026

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.content.Context;

<<<<<<< HEAD
@Database(entities = {USER.class, TRIPS.class, LEGS.class}, version = 1, exportSchema = false)
public abstract class TravelDatabase extends RoomDatabase {

=======
import java.util.concurrent.Executors;

@Database(entities = {USER.class, TRIPS.class, LEGS.class}, version = 1)
public abstract class TravelDatabase extends RoomDatabase{
>>>>>>> 45b8647cb15609e56de911b9bf799d011e6eb6b0
    public abstract UserDAO userDAO();
    public abstract TripsDAO tripsDAO();
    public abstract LegsDAO legsDAO();

    private static TravelDatabase INSTANCE;

    public static synchronized TravelDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
<<<<<<< HEAD
                            context.getApplicationContext(),
                            TravelDatabase.class,
                            "travel_database"
                    )
                    .allowMainThreadQueries() // OK for class (not production)
                    .fallbackToDestructiveMigration() // prevents crashes on schema changes
                    .build();
        }
        return INSTANCE;
    }
}
=======
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
                UserDAO userDAO = INSTANCE.userDAO();

                USER test = new USER("testuser1", "testuser1", 0);
                USER admin = new USER("admin2", "admin2", 1);

                userDAO.insertUser(test);
                userDAO.insertUser(admin);
            });
        }
    };
}
>>>>>>> 45b8647cb15609e56de911b9bf799d011e6eb6b0
