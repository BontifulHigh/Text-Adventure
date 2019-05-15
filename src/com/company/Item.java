package com.company;

/**
 * The item class allows the player to pick up and use items in the game.
 * This can be helpful for utilizing keys, granting players special options,
 * or generally allowing players to get more information from things in a room
 *
 * @author Jeremy Wolff
 * @version 1.0
 */
public class Item {

    String name;
    String description;
    Room initialRoom;

    /**
     * Constructor for the Item object.
     * @param itemName The name of the item.
     * @param itemDescription The description of the item.
     * @param itemInitialRoom The initial room that the item is found in.
     */
    public Item(String itemName, String itemDescription, Room itemInitialRoom){
        name = itemName;
        description = itemDescription;
        initialRoom = itemInitialRoom;
    }

    /**
     * Returns the item name, which is the word used to refer to it.
     * @return The name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the minimal description of the item.
     * i.e. "A set of keys"
     * @return the description of the item.
     */
    public String getDescription(){
        return description;
    }

    /**
     * Returns the room object that the item was initially located in.
     * @return the room object that the item was initially located in.
     */
    public Room getInitialRoom(){
        return initialRoom;
    }

    /**
     * The player has attempted to use this item.
     * This method goes through our list of events and sees if one of the events' conditions are met.
     * The conditions of an event include an item and a specific room.
     * @param room The room that the player is currently in.
     */
    public void useItem(Room room){
        for(Event event : World.events){
            if(event.getRoom() == room && event.getItem() == this){
                event.triggerEvent();
                return;
            }
        }
        System.out.print("You can't use that item right now.\n");
    }


}
