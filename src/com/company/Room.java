package com.company;
import java.util.ArrayList;

/**
 * A Room is a "location" in the adventure game. A room has a name,
 * a description, a column, and a row.
 *
 * @author Jeremy Wolff
 * @version 1.0
 */
public class Room {

    private int column;
    private int row;
    private String name;
    private String description;
    private ArrayList<Item> items = new ArrayList<Item>();

    /**
     * Constructor for objects of class Room.
     * @param roomRow row number of the room.
     * @param roomColumn column number of the room.
     * @param roomName name of the room.
     * @param roomDescription a detailed description of what the player can see in the room.
     */
    public Room(int roomRow, int roomColumn, String roomName, String roomDescription){
        row = roomRow;
        column = roomColumn;
        name = roomName;
        description = roomDescription;
    }

    /**
     * Returns the column number of this room.
     * @return the column number of this room.
     */
    public int getColumn(){
        return column;
    }

    /**
     * Returns the row number of this room.
     * @return the row number of this room.
     */
    public int getRow(){
        return row;
    }

    /**
     * Returns the name of this room.
     * @return the name of this room.
     */
    public String getName(){
        return name;
    }

    /**
     * Returns the description of this room.
     * @return the description of this room.
     */
    public String getDescription(){
        String combinedDescription = "";
        combinedDescription += description;

        for(Item item : items){
            combinedDescription += " " + item.getDescription();
        }
        return combinedDescription;
    }

    /**
     * Adds an item to this room's item list.
     * @param item The item to be added.
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * Removes an item from this room's item list.
     * @param item The item to be removed.
     */
    public void removeItem(Item item) {
        items.remove(item);
    }

    /**
     * Retrives the items that are located in this room.
     * @return the list of items located in this room.
     */
    public ArrayList<Item> getItems() {
        return items;
    }
}
