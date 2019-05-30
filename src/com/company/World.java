package com.company;
import java.util.ArrayList;
import java.util.List;

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
    public World(String roomFile, String itemFile){
        //map = myMap();
        map = ReadCSV.readRoomData(roomFile);
        items = ReadCSV.readItemData(itemFile);
        validateRoomConnections(map);
        placeItemsInRooms();
        startingRoom = getRoom("A");
    }



    private static void validateRoomConnections(List<Room> rooms) {
        for (Room room : rooms){
            validateConnection(room, "n");
            validateConnection(room, "e");
            validateConnection(room, "s");
            validateConnection(room, "w");
        }
    }

    private static void validateConnection(Room room, String direction){
        switch (direction){
            case "n":
                if(room.getConnectingRoomNum("n") != -1 && room.getRoomNorth() == null){
                    System.out.println("*** WARNING: ROOM #" + room.getRoomNumber() + " - Unable to find North room #" +room.getConnectingRoomNum("n") +".");
                }
                return;
            case "e":
                if(room.getConnectingRoomNum("e") != -1 && room.getRoomEast() == null){
                    System.out.println("*** WARNING: ROOM #" + room.getRoomNumber() + " - Unable to find East room #" +room.getConnectingRoomNum("e") +".");
                }
                return;
            case "s":
                if(room.getConnectingRoomNum("s") != -1 && room.getRoomSouth() == null){
                    System.out.println("*** WARNING: ROOM #" + room.getRoomNumber() + " - Unable to find South room #" +room.getConnectingRoomNum("s") +".");
                }
                return;
            case "w":
                if(room.getConnectingRoomNum("w") != -1 && room.getRoomWest() == null){
                    System.out.println("*** WARNING: ROOM #" + room.getRoomNumber() + " - Unable to find West room #" +room.getConnectingRoomNum("w") +".");
                }
        }
    }

    public void addRoom(Room room){
        map.add(room);
    }

    public void removeRoom(Room roomToRemove){
        int roomNum = roomToRemove.getRoomNumber();
        for(Room room : map){
            int[] rooms = room.getConnectingRooms();
            for(int i=0; i< rooms.length; i++){
                if(rooms[i] == roomNum){
                    rooms[i] = -1;
                }
            }
        }
        map.remove(roomToRemove);
    }

    public Room getStartingRoom(){
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
     * @param roomNum room number of the room that we're trying to find.
     * @return true if there is room at the indicated row and column.
     */

    public static Room getRoom(int roomNum) {
        for(Room room : map){
            if (room.getRoomNumber() ==  roomNum) {
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
        details += room.getDescription();
        details += "\n";
        details += "You may go";

        // Check each direction and add that direction to details if there's a room there.
        details = getDirectionDetails(room, details);

        return details;
    }

    private String getDirectionDetails(Room room, String details) {
        if (room.getRoomNorth() != null) {
            details +=" [North]";
        }
        if (room.getRoomSouth() != null) {
            details +=" [South]";
        }
        if (room.getRoomEast() != null) {
            details+=" [East]";
        }
        if (room.getRoomWest() != null) {
            details+=" [West]";
        }
        return details;
    }

    public void updateNearbyRoomsMap(Room room){
        resetNearbyRoomsMap();
        if(room.getRoomNorth() != null){
            addNearbyRoom("n");
        }
        if(room.getRoomEast() != null){
            addNearbyRoom("e");
        }
        if(room.getRoomSouth() != null){
            addNearbyRoom("s");
        }
        if(room.getRoomWest() != null){
            addNearbyRoom("w");
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
