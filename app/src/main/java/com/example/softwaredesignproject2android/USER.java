package com.example.softwaredesignproject2android;//CM0415026

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;



@Entity(tableName = "Users")
public class USER{
    public USER(String username, String password, int isAdmin){
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }
    public USER(){
    }

    @PrimaryKey
    @NonNull
    public String username;

    public String password;
    public int isAdmin; //0 == FALSE && 1 == TRUE
}