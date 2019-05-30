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
    private int[] connectingRooms = new int[4];

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
        addConnectingRoom("n", -1);
        addConnectingRoom("e", -1);
        addConnectingRoom("s", -1);
        addConnectingRoom("w", -1);
    }

    public Room(int roomNum, String roomName, String roomDescription, int north, int east, int south, int west){
        roomNumber = roomNum;
        name = roomName;
        description = roomDescription;
        addConnectingRoom("n", north);
        addConnectingRoom("e", east);
        addConnectingRoom("s", south);
        addConnectingRoom("w", west);
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
        return World.getRoom(connectingRooms[0]);
    }

    public Room getRoomEast(){
        return World.getRoom(connectingRooms[1]);
    }

    public Room getRoomSouth(){
        return World.getRoom(connectingRooms[2]);
    }

    public Room getRoomWest(){
        return World.getRoom(connectingRooms[3]);
    }

    public void addConnectingRoom(String direction, int roomNum) {
        connectingRooms[getDirectionNum(direction)] = roomNum;
    }

    public void removeConnectingRoom(String direction){
        connectingRooms[getDirectionNum(direction)] = -1;
    }

    public int getConnectingRoomNum(String direction) {
        return connectingRooms[getDirectionNum(direction)];
    }

    public int[] getConnectingRooms() {
        return connectingRooms;
    }

    public int getDirectionNum(String direction){
        switch(direction){
            case "n":
                return 0;
            case "e":
                return 1;
            case "s":
                return 2;
            case "w":
                return 3;
            default:
                System.out.println("Unable to recognize that direction.");
                return -1;
        }
    }
}
