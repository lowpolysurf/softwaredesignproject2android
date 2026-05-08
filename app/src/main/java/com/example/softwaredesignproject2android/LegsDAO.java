package com.example.softwaredesignproject2android;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LegsDAO {

    // INSERT
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLeg(LEGS leg);

    // GET legs for user + trip
    @Query("SELECT * FROM LEGS WHERE username = :loggedInUser AND tripUID = :tripUID ORDER BY startDate DESC")
    List<LEGS> getTripLegs(String loggedInUser, int tripUID);

    // DELETE specific leg
    @Query("DELETE FROM LEGS WHERE username = :loggedInUser AND startCity = :startCity AND startDate = :startDate")
    void deleteLeg(String loggedInUser, String startCity, int startDate);

    // DELETE ALL
    @Query("DELETE FROM LEGS")
    void deleteAll();
}