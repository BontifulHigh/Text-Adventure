package com.company;

import java.util.ArrayList;

/**
 * The Event class creates an event in the world.
 * Events can be triggered when an item is USED in a specific room that the event specifies.
 *
 * @author Jeremy Wolff
 * @version 1.0
 */
public class Event {
    private Item item;
    private Room room;
    private String triggerText;

    /**
     * Constructor for an Event object.
     * @param eventRoom The room that the event can be triggered in.
     * @param eventItem The item that triggers the event.
     * @param eventTriggerText The text to be shown to the player when they trigger the event via USE command.
     */
    public Event(Room eventRoom, Item eventItem, String eventTriggerText){
        room = eventRoom;
        item = eventItem;
        triggerText = eventTriggerText;
    }

    /**
     * retrive the room object associated with an event.
     * @return the room object associated with an event.
     */
    public Room getRoom(){
        return room;
    }

    /**
     * retrive the item object associated with an event.
     * @return the item object associated with an event.
     */
    public Item getItem(){
        return item;
    }

    /**
     * When the player correclty triggers an event, output of the triggerText will be displayed to the player.
     */
    public void triggerEvent(){
        System.out.print(triggerText + "\n");
    }
}
