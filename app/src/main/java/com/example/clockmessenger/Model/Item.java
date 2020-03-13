package com.example.clockmessenger.Model;

import java.util.ArrayList;

public class Item
{
    private String eventName, eventDate, eventMessage;

    public Item(String eventName, String eventDate, String eventMessage) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventMessage = eventMessage;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventMessage() {
        return eventMessage;
    }

    public void setEventMessage(String eventMessage) {
        this.eventMessage = eventMessage;
    }

    public static ArrayList<Item> createContactsList(int numContacts) {
        ArrayList<Item> items = new ArrayList<Item>();

        for (int i = 1; i <= numContacts; i++) {
            items.add(new Item("Event"+i, "20 06 2023","Happy birthday"));
        }

        return items;
    }
}
