package com.github.sabinapene;

import static org.junit.Assert.assertEquals;

import com.github.sabinapene.Models.Garden;

import org.junit.Test;

import java.lang.reflect.Field;

public class GardenTest {


    //setters testing
    @Test
    public void testSetUserID_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        //given
        Garden garden = new Garden();

        //when
        garden.setUserID("123");
        //then
        final Field field = garden.getClass().getDeclaredField("userID");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(garden), "123");
    }

    @Test
    public void testSetDate_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        //given
        Garden garden = new Garden();

        //when
        garden.setDate("08/09/2023");
        //then
        final Field field = garden.getClass().getDeclaredField("date");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(garden), "08/09/2023");
    }

    @Test
    public void testSetWindowOpen_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        //given
        Garden garden = new Garden();

        //when
        garden.setWindowOpen(true);
        //then
        final Field field = garden.getClass().getDeclaredField("windowOpen");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(garden), true);
    }

    @Test
    public void testSetCO2_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        //given
        Garden garden = new Garden();

        //when
        garden.setCO2(10);
        //then
        final Field field = garden.getClass().getDeclaredField("CO2");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(garden), 10);
    }

    @Test
    public void testSetHumidty_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        //given
        Garden garden = new Garden();

        //when
        garden.setHumidity(40);
        //then
        final Field field = garden.getClass().getDeclaredField("humidity");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(garden), 40);
    }

    @Test
    public void testSetTemp_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        //given
        Garden garden = new Garden();

        //when
        garden.setTemp(20);
        //then
        final Field field = garden.getClass().getDeclaredField("temp");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(garden), 20);
    }

    @Test
    public void testSetGardenID_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        //given
        Garden garden = new Garden();

        //when
        garden.setGardenID("garden1");
        //then
        final Field field = garden.getClass().getDeclaredField("gardenID");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(garden), "garden1");
    }

    //getters testing
    @Test
    public void testGetTemp() throws NoSuchFieldException, IllegalAccessException {
        //given
        Garden garden = new Garden();
        garden.setTemp(20);
        final int result = garden.getTemp();

        //then
        assertEquals(20, result);
    }

    @Test
    public void testGetHumidity() {
        //given
        Garden garden = new Garden();
        garden.setHumidity(40);
        final int result = garden.getHumidity();

        //then
        assertEquals(40, result);
    }

    @Test
    public void testGetCO2() {
        //given
        Garden garden = new Garden();
        garden.setCO2(430);
        final int result = garden.getCO2();

        //then
        assertEquals(430, result);
    }

    @Test
    public void testGetEntryUserID() throws NoSuchFieldException, IllegalAccessException {
        //given
        Garden garden = new Garden();
        garden.setUserID("123");
        final Field field = garden.getClass().getDeclaredField("userID");
        field.setAccessible(true);
        field.set(garden, "123");

        //when
        final String result = garden.getUserID();

        //then
        assertEquals("field wasn't retrieved properly", result, "123");
    }
}