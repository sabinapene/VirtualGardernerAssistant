package com.github.sabinapene.Models;

import com.github.sabinapene.ActivitySignIn;

public class Settings {
    String metricPreferences = "";
    String notificationsPeriod = "";
    //String userID = "";

    public Settings(){

    }

    public Settings(String metricPreferences, String notificationsPeriod){
        this.metricPreferences = metricPreferences;
        this.notificationsPeriod = notificationsPeriod;
        //userID = ActivitySignIn.getCurrentUserEmail();
    }

    public String getMetricPreferences(){
        return metricPreferences;
    }

    public String getNotificationsPeriod(){
        return notificationsPeriod;
    }

    public void setMetricPreferences(String str){
        metricPreferences = str;
    }

    public void setNotificationsPeriod(String str){
        notificationsPeriod = str;
    }

}
