package com.github.sabinapene.Models;
import java.time.LocalDate;

public class Garden {
    private String userID = "-1";
    private String date = "";
    private String time="";
    private boolean windowOpen = false;
    private int CO2=0;
    private int temp=0;
    private String gardenID;
    // LocalDate lt = LocalDate.now();
    private int humidity=0;
    public Garden(String userID, String date, String time, boolean windowOpen, int CO2, int temp, int humidity){
        this.userID = userID;
        this.date = date;
        this.time = time;
        this.windowOpen = windowOpen;
        this.CO2 = CO2;
        this.humidity = humidity;
    }

    public Garden(String userID, boolean windowOpen, int CO2, int temp, int humidity){
        this.userID = userID;
        this.windowOpen = windowOpen;
        this.CO2 = CO2;
        this.humidity = humidity;
    }

    public Garden(){}


    public String getUserID() {
        return userID;
    }

    public String getTime() {
        return time;
    }

    public boolean isWindowOpen() {
        return windowOpen;
    }

    public int getCO2() {
        return CO2;
    }

    public int getTemp() {
        return temp;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setWindowOpen(boolean windowOpen) {
        this.windowOpen = windowOpen;
    }

    public void setCO2(int CO2) {
        this.CO2 = CO2;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public String getDate() {
        return date;
    }

    public String getGardenID() {
        return gardenID;
    }

    public void setGardenID(String gardenID) {
        this.gardenID = gardenID;
    }

    public int calculateTempImperial(int c){
        //C to F
        return 9/5*c+32;
    }
}
