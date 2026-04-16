package com.example.softwaredesignproject2android;//CM0415026

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Trips",
        foreignKeys = {
            @ForeignKey(entity = USER.class,
                    parentColumns = "username",
                    childColumns = "username")
            }
        )
public class TRIPS{
    public TRIPS(){
    }

    @PrimaryKey(autoGenerate = true)
    public int uniqueID;
    @NonNull
    public String username;
    @NonNull
    public String tripName;

    public int startDate;
    public int endDate;
}