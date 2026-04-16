package com.example.softwaredesignproject2android;//CM415026

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TripsDAO {

    //FOR TRIPS
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTrip(TRIPS trip); //add trip
    @Query("SELECT * FROM Trips WHERE username = :loggedInUser ORDER BY startDate DESC")
    List<TRIPS> getUserTrips(String loggedInUser); //retrieve trips for user
    @Query("DELETE FROM Trips WHERE username = :loggedInUser AND tripName = :tripName")
    void deleteTrip(String loggedInUser, String tripName); //delete trip by username and trip name
    @Query("DELETE FROM Trips")
    void deleteAll(); //DELETE ALL LOGGED TRIPS ADMIN ONLY
}