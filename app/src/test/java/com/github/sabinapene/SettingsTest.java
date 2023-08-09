package com.github.sabinapene;

import static org.junit.Assert.assertEquals;

import com.github.sabinapene.Models.Garden;
import com.github.sabinapene.Models.Settings;

import org.junit.Test;

import java.lang.reflect.Field;

public class SettingsTest {

    //setters testing
    @Test
    public void testSetUserID_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        //given
        Settings settings = new Settings();

        //when
        settings.setUserID("123");
        //then
        final Field field = settings.getClass().getDeclaredField("userID");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(settings), "123");
    }

    @Test
    public void testSetMetricPreferences_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        //given
        Settings settings = new Settings();

        //when
        settings.setMetricPreferences("true");
        //then
        final Field field = settings.getClass().getDeclaredField("metricPreferences");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(settings), "true");
    }

    @Test
    public void testSetNotificationsPeriod_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        //given
        Settings settings = new Settings();

        //when
        settings.setNotificationsPeriod("1 hour");
        //then
        final Field field = settings.getClass().getDeclaredField("notificationsPeriod");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(settings), "1 hour");
    }

    //getters
    @Test
    public void testGetNotificationsPeriod() throws NoSuchFieldException, IllegalAccessException {
        //given
        Settings settings = new Settings();
        settings.setNotificationsPeriod("1 hour");
        final Field field = settings.getClass().getDeclaredField("notificationsPeriod");
        field.setAccessible(true);
        field.set(settings, "1 hour");

        //when
        final String result = settings.getNotificationsPeriod();

        //then
        assertEquals("field wasn't retrieved properly", result, "1 hour");
    }

    @Test
    public void testGetEntryUserID() throws NoSuchFieldException, IllegalAccessException {
        //given
        Settings settings = new Settings();
        settings.setUserID("123");
        final Field field = settings.getClass().getDeclaredField("userID");
        field.setAccessible(true);
        field.set(settings, "123");

        //when
        final String result = settings.getUserID();

        //then
        assertEquals("field wasn't retrieved properly", result, "123");
    }

    @Test
    public void testGetMetricPreferences() throws NoSuchFieldException, IllegalAccessException {
        //given
        Settings settings = new Settings();
        settings.setMetricPreferences("true");
        final Field field = settings.getClass().getDeclaredField("metricPreferences");
        field.setAccessible(true);
        field.set(settings, "true");

        //when
        final String result = settings.getMetricPreferences();

        //then
        assertEquals("field wasn't retrieved properly", result, "true");
    }
}
