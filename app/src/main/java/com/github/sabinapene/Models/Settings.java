package com.github.sabinapene.Models;

import com.github.sabinapene.ActivitySignIn;

public class Settings {
    String metricPreferences = "";
    String notificationsPeriod = "";
    String userID = "";

    public Settings(){

    }

    public Settings(String metricPreferences, String notificationsPeriod){
        this.metricPreferences = metricPreferences;
        this.notificationsPeriod = notificationsPeriod;
        userID = ActivitySignIn.getCurrentUserEmail();
    }
}
