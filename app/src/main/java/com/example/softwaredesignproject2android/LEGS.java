package com.example.softwaredesignproject2android;//CM0415026

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Legs",
        foreignKeys = {
                @ForeignKey(entity = TRIPS.class,
                        parentColumns = "uniqueID",
                        childColumns = "tripUID"),
                @ForeignKey(entity = USER.class,
                        parentColumns = "username",
                        childColumns = "username")
        }
)
public class LEGS{
    public LEGS(){
    }

    @PrimaryKey(autoGenerate = true)
    public int uniqueID;
    public int tripUID;
    public String username;
    public String startCity;
    public String destCity;
    public int startDate;
    public String transport;
    public String notes;
}