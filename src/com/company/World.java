package com.company;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents the whole world. It constructs the rooms and gives us the ability
 * to get details of a room, check if the player can move into another room, find out if a room exists, retrieve a room.
 * <p>
 * This class also allows us to construct any map for the game world.
 * <p>
 * The World class has functions to display the nearby rooms in printed format.
 *
 * @author Jeremy Wolff
 * @version 1.0
 */
public class World {

    static List<Room> map = new ArrayList<Room>(); // map has a list of all the rooms in the world.
    static List<Item> items = new ArrayList<Item>();
    static List<Event> events = new ArrayList<Event>();
    static String[] nearbyRoomsMap = new String[9];
    static Room startingRoom;

    /**
     * Constructor for objects of class World.
     * This constructor calls methods to:
     * <ol>
     * <li>populate the map in the world</li>
     * <li>populate the items and place them into their appropriate rooms</li>
     * </ol>
     */
    public World(){
        //map = myMap();
        map = ReadCSV.readRoomData();
        items = ReadCSV.readItemData();
        placeItemsInRooms();
        generateConnectingRooms(map);
        startingRoom = getRoom("Living Room");
    }

    public static void generateConnectingRooms(List<Room> rooms) {
        for(Room room : rooms) {
            room.addConnectingRoom("n", room.getRoomNorth(room.getRow(), room.getColumn()));
            room.addConnectingRoom("e", room.getRoomEast(room.getRow(), room.getColumn()));
            room.addConnectingRoom("s", room.getRoomSouth(room.getRow(), room.getColumn()));
            room.addConnectingRoom("w", room.getRoomWest(room.getRow(), room.getColumn()));
            room.addConnectingRoom("north", room.getRoomNorth(room.getRow(), room.getColumn()));
            room.addConnectingRoom("east", room.getRoomEast(room.getRow(), room.getColumn()));
            room.addConnectingRoom("south", room.getRoomSouth(room.getRow(), room.getColumn()));
            room.addConnectingRoom("west", room.getRoomWest(room.getRow(), room.getColumn()));
        }
    }

    public Room getStartingRoom(){
        System.out.println(startingRoom);
        return startingRoom;
    }

    /**
     * Allows us to get the total number of rooms in the map.
     * @return the number of rooms in the map.
     */
    public int getNumRooms(){
        return map.size();
    }

    /**
     * Search through our map to find a specific room
     * @param currentRow row of the room that we're trying to find.
     * @param currentColumn column of the room that we're trying to find.
     * @return true if there is room at the indicated row and column.
     */

    public static Room getRoom(int currentRow, int currentColumn) {
        for(Room room : map){
            if (room.getRow() == currentRow && room.getColumn() == currentColumn) {
                return room;
            }
        }
        return null;
    }

    /**
     * Retrive a room in the map by its name
     * @param name the name of the room being searched for.
     * @return the room object, if the room exists.
     */
    public static Room getRoom(String name){
        for(int i=0; i < map.size(); i++){
            if (map.get(i).getName().equals(name)) {
                return map.get(i);
            }
        }
        return null;
    }

    /**
     * Gives the details about a room. This function also adds directions to the nearybyRoomMap.
     * @param room a reference to the room that the player is seeking details about.
     * @return a full description of the room and all possible actions directions the player can move
     */
    public String getRoomDetails(Room room){
        String details="";
        details += "\n----------------------------------------------------------\n";
        details += room.getDescription();
        details += "\n";
        details += "You may go";

        // Check each direction and add that direction to details if there's a room there.
        if (room.getConnectingRoom("n") != null) {
            details +=" [North]";
        }
        if (room.getConnectingRoom("s") != null) {
            details +=" [South]";
        }
        if (room.getConnectingRoom("e") != null) {
            details+=" [East]";
        }
        if (room.getConnectingRoom("w") != null) {
            details+=" [West]";
        }

        details +=".";

        return details;
    }

    public void updateNearbyRoomsMap(Room room){
        resetNearbyRoomsMap();
        for(String direction : room.getConnectingDirections()){
            addNearbyRoom(direction);
        }
    }

    /**
     * Go through the items list and place each item into the room associated with that item.
     */
    public static void placeItemsInRooms(){
        for(int i=0; i < items.size(); i++){
            // Find the room
            try {
                Room room = getRoom(items.get(i).getInitialRoom().getName());
                room.addItem(items.get(i));
            }
            catch (NullPointerException e) {
                System.out.print("Cannot place " + items.get(i).getName() + " in " + items.get(i).getInitialRoom().getName() + " because room was null.");
            }

        }
    }

    /**
     * Sets the mearbyRoomsMap back to its default value so rooms can be added to its sides.
     */
    private void resetNearbyRoomsMap() {
        nearbyRoomsMap[0] = "************\n";
        nearbyRoomsMap[1] = "************\n";
        nearbyRoomsMap[2] = "************\n";
        nearbyRoomsMap[3] = "****    ****\n";
        nearbyRoomsMap[4] = "**** @@ ****\n";
        nearbyRoomsMap[5] = "****    ****\n";
        nearbyRoomsMap[6] = "************\n";
        nearbyRoomsMap[7] = "************\n";
        nearbyRoomsMap[8] = "************\n";
    }

    /**
     * Print out the nearbyRoomsMap, line by line.
     */
    public void printNearbyRoomsMap(){
        for(String line : nearbyRoomsMap){
            System.out.print(line);
        }
    }

    /**
     * When the description of a room is provided, the addNearbyRoom function is called for each
     * direction that is available to the player. This allows the player to have the most current
     * nearbyRoomMap available.
     * @param direction The direction that needs to be added to the map.
     */
    private void addNearbyRoom(String direction){
        if(direction.equals("e")){
            nearbyRoomsMap[2] = nearbyRoomsMap[2].substring(0,8) + "****\n";
            nearbyRoomsMap[3] = nearbyRoomsMap[3].substring(0,8) + "    \n";
            nearbyRoomsMap[4] = nearbyRoomsMap[4].substring(0,8) + "    \n";
            nearbyRoomsMap[5] = nearbyRoomsMap[5].substring(0,8) + "    \n";
            nearbyRoomsMap[6] = nearbyRoomsMap[6].substring(0,8) + "****\n";
        }
        if(direction.equals("w")){
            nearbyRoomsMap[2] = "****" + nearbyRoomsMap[2].substring(4);
            nearbyRoomsMap[3] = "    " + nearbyRoomsMap[3].substring(4);
            nearbyRoomsMap[4] = "    " + nearbyRoomsMap[4].substring(4);
            nearbyRoomsMap[5] = "    " + nearbyRoomsMap[5].substring(4);
            nearbyRoomsMap[6] = "****" + nearbyRoomsMap[6].substring(4);
        }
        if(direction.equals("s")){
            nearbyRoomsMap[6] = nearbyRoomsMap[2].substring(0,3) + "*    *" + nearbyRoomsMap[2].substring(9);
            nearbyRoomsMap[7] = "****    ****\n";
            nearbyRoomsMap[8] = "****    ****\n";
        }
        if(direction.equals("n")){
            nearbyRoomsMap[0] = "****    ****\n";
            nearbyRoomsMap[1] = "****    ****\n";
            nearbyRoomsMap[2] = nearbyRoomsMap[2].substring(0,3) + "*    *" + nearbyRoomsMap[2].substring(9);
        }
    }

}
