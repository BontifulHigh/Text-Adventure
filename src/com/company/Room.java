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

    private int roomNumber;
    private String name;
    private String description;
    private ArrayList<Item> items = new ArrayList<Item>();
    private int[] surroundingRooms = new int[4];

    /**
     * Constructor for objects of class Room.
     * @param roomNum
     * @param roomName name of the room.
     * @param roomDescription a detailed description of what the player can see in the room.
     */
    public Room(int roomNum, String roomName, String roomDescription){
        roomNumber = roomNum;
        name = roomName;
        description = roomDescription;
    }

    public void addSurroundingRoom(String direction, int roomNum) {
        switch(direction){
            case "n":
                surroundingRooms[0] = roomNum;
                break;
            case "e":
                surroundingRooms[1] = roomNum;
                break;
            case "s":
                surroundingRooms[2] = roomNum;
                break;
            case "w":
                surroundingRooms[3] = roomNum;
                break;
            default:
                System.out.println("Unable to recognize that direction.");
                break;
        }
    }

    public int getRoomNumber(){
        return roomNumber;
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

    public Room getRoomNorth(){
        return World.getRoom(surroundingRooms[0]);
    }

    public Room getRoomEast(){
        return World.getRoom(surroundingRooms[1]);
    }

    public Room getRoomSouth(){
        return World.getRoom(surroundingRooms[2]);
    }

    public Room getRoomWest(){
        return World.getRoom(surroundingRooms[3]);
    }

    public void removeSurroundingRoom(String direction){

        switch(direction){
            case "n":
                surroundingRooms[0] = -1;
                break;
            case "e":
                surroundingRooms[1] = -1;
                break;
            case "s":
                surroundingRooms[2] = -1;
                break;
            case "w":
                surroundingRooms[3] = -1;
                break;
            default:
                System.out.println("Unable to recognize that direction.");
                break;
        }
    }

    public int[] getSurroundingRooms() {
        return surroundingRooms;
    }
}
