package com.example.softwaredesignproject2android;//CM415026

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LegsDAO {

    //FOR LEGS
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLeg(LEGS leg); //add leg
    @Query("SELECT * FROM Legs WHERE username = :loggedInUser AND tripUID = :tripUID ORDER BY startDate DESC")
    List<LEGS> getTripLegs(String loggedInUser, int tripUID); //retrieve legs for user
    @Query("DELETE FROM Legs WHERE username = :loggedInUser AND startCity = :startCity AND startDate = :startDate")
    void deleteLeg(String loggedInUser, String startCity, int startDate); //delete leg by username && start city && start date
    @Query("DELETE FROM Legs")
    void deleteAll(); //DELETE ALL LOGGED LEGS ADMIN ONLY
}