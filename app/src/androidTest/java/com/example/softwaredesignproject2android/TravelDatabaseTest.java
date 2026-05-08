package com.example.softwaredesignproject2android;
//CM507026

import static org.junit.Assert.*;
import android.content.Context;
import androidx.room.Room;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import androidx.test.core.app.ApplicationProvider;
import java.io.IOException;
import java.util.List;

public class TravelDatabaseTest {
    private TravelDatabase db;
    private UserDAO userDAO;
    private TripsDAO tripsDAO;
    private LegsDAO legsDAO;

    @Before //creating temp test db
    public void createDatabase(){
        Context context = ApplicationProvider.getApplicationContext();

        db = Room.inMemoryDatabaseBuilder(
                context,
                TravelDatabase.class
        ).allowMainThreadQueries().build();
        userDAO = db.userDAO();
        tripsDAO = db.tripsDAO();
        legsDAO = db.legsDAO();
    }

    @After //close temp db
    public void closeDatabase() throws IOException{
        db.close();
    }

    //user table tests
    @Test
    public void insertUser_and_retrieveUser(){
        USER user = new USER("chase500", "password", 0);
        userDAO.insertUser(user);
        USER retrievedUser = userDAO.getLoggedInUser("chase500");

        assertNotNull(retrievedUser);
        assertEquals("chase500", retrievedUser.username);
        assertEquals("password", retrievedUser.password);
        assertEquals(0, retrievedUser.isAdmin);
    }
    @Test
    public void deleteUser(){
        USER user = new USER("delete", "delete", 0);
        userDAO.insertUser(user);
        userDAO.deleteUser("delete");
        USER deletedUser = userDAO.getLoggedInUser("delete");

        assertNull(deletedUser);
    }
    @Test
    public void getAllUsers(){
        userDAO.insertUser(new USER("user1", "pass1", 0));
        userDAO.insertUser(new USER("user2", "pass2", 0));
        List<USER> users = userDAO.getAllUsers();

        assertEquals(2, users.size());
    }

    //trips table tests
    @Test
    public void insertTrip_and_retrieveTrip(){
        USER user = new USER("tripUser", "pass", 0);
        userDAO.insertUser(user);

        TRIPS trip = new TRIPS();
        trip.username = "tripUser";
        trip.tripName = "Europe 2026";
        trip.startDate = "startdatestring";
        trip.endDate = "enddatestring";
        tripsDAO.insertTrip(trip);
        List<TRIPS> trips = tripsDAO.getUserTrips("tripUser");

        assertEquals(1, trips.size());
        assertEquals("Europe 2026", trips.get(0).tripName);
    }
    @Test
    public void deleteTrip(){
        USER user = new USER("tripDelete", "pass", 0);
        userDAO.insertUser(user);
        TRIPS trip = new TRIPS();
        trip.username = "tripDelete";
        trip.tripName = "Delete Me!";
        tripsDAO.insertTrip(trip);
        tripsDAO.deleteTrip("tripDelete", "Delete Me!");
        List<TRIPS> trips = tripsDAO.getUserTrips("tripDelete");

        assertEquals(0, trips.size());
    }

    //legs table tests
    @Test
    public void insertLeg_and_retrieveLeg(){
        USER user = new USER("legUser", "pass", 0);
        userDAO.insertUser(user);
        TRIPS trip = new TRIPS();
        trip.username = "legUser";
        trip.tripName = "California 2022";
        tripsDAO.insertTrip(trip);
        List<TRIPS> trips = tripsDAO.getUserTrips("legUser");
        int tripID = trips.get(0).uniqueID;
        LEGS leg = new LEGS();
        leg.username = "legUser";
        leg.tripUID = tripID;
        leg.startCity = "Monterey";
        leg.destCity = "Salinas";
        leg.startDate = 20260507;
        leg.transport = "car";
        leg.notes = "drive to Salinas";
        legsDAO.insertLeg(leg);
        List<LEGS> legs = legsDAO.getTripLegs("legUser", tripID);

        assertEquals(1, legs.size());
        assertEquals("Monterey", legs.get(0).startCity);
        assertEquals("Salinas", legs.get(0).destCity);
    }
    @Test
    public void deleteLeg(){
        USER user = new USER("legDelete", "pass", 0);
        userDAO.insertUser(user);
        TRIPS trip = new TRIPS();
        trip.username = "legDelete";
        trip.tripName = "Delete Leg Trip";
        tripsDAO.insertTrip(trip);
        int tripID = tripsDAO.getUserTrips("legDelete").get(0).uniqueID;
        LEGS leg = new LEGS();
        leg.username = "legDelete";
        leg.tripUID = tripID;
        leg.startCity = "Los Angeles";
        leg.destCity = "San Francisco";
        leg.startDate = 20260507;
        legsDAO.insertLeg(leg);
        legsDAO.deleteLeg("legDelete", "Los Angeles", 20260507);
        List<LEGS> legs = legsDAO.getTripLegs("legDelete", tripID);

        assertEquals(0, legs.size());
    }

}
