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
    Player player;
    final static Room startingRoom = getRoom(0,0);

    /**
     * Constructor for objects of class World.
     * This constructor calls methods to:
     * <ol>
     * <li>populate the map in the world</li>
     * <li>populate the items and place them into their appropriate rooms</li>
     * </ol>
     * @param gamePlayer The player in the game.
     */
    public World(Player gamePlayer){
        //map = myMap();
        map = ReadCSV.readRoomData();
        items = ReadCSV.readItemData();
        player = gamePlayer;
        player.setCurrentRoom(getRoom(0,0));
        placeItemsInRooms();
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
     * @param row row of the room that we're trying to find.
     * @param column column of the room that we're trying to find.
     * @return true if there is room at the indicated row and column.
     */
    public static boolean roomExists(int row, int column){
        return getRoom(row, column) != null;
    }

    /**
     * Retrive a room in the map at a specified location
     * @param row row of the room being queried
     * @param column column of the room being queried
     * @return the room that exists at this row and column.
     * returns null if there's no room at this location.
     */
    public static Room getRoom(int row, int column){
        for(int i=0; i < map.size(); i++){
            if(map.get(i).getRow() == row && map.get(i).getColumn() == column){
                return map.get(i);
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
        resetNearbyRoomsMap();

        // Check each direction and add that direction to details if there's a room there.
        if (roomExists(room.getRow()-1, room.getColumn())) {
            details +=" [North]";
            addNearbyRoom("North");
        }
        if (roomExists(room.getRow()+1, room.getColumn())) {
            details +=" [South]";
            addNearbyRoom("South");
        }
        if (roomExists(room.getRow(), room.getColumn()+1)) {
            details+=" [East]";
            addNearbyRoom("East");
        }
        if (roomExists(room.getRow(), room.getColumn()-1)) {
            details+=" [West]";
            addNearbyRoom("West");
        }

        details +=".";

        return details;
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
        if(direction.equals("East")){
            nearbyRoomsMap[2] = nearbyRoomsMap[2].substring(0,8) + "****\n";
            nearbyRoomsMap[3] = nearbyRoomsMap[3].substring(0,8) + "    \n";
            nearbyRoomsMap[4] = nearbyRoomsMap[4].substring(0,8) + "    \n";
            nearbyRoomsMap[5] = nearbyRoomsMap[5].substring(0,8) + "    \n";
            nearbyRoomsMap[6] = nearbyRoomsMap[6].substring(0,8) + "****\n";
        }
        if(direction.equals("West")){
            nearbyRoomsMap[2] = "****" + nearbyRoomsMap[2].substring(4);
            nearbyRoomsMap[3] = "    " + nearbyRoomsMap[3].substring(4);
            nearbyRoomsMap[4] = "    " + nearbyRoomsMap[4].substring(4);
            nearbyRoomsMap[5] = "    " + nearbyRoomsMap[5].substring(4);
            nearbyRoomsMap[6] = "****" + nearbyRoomsMap[6].substring(4);
        }
        if(direction.equals("South")){
            nearbyRoomsMap[6] = nearbyRoomsMap[2].substring(0,3) + "*    *" + nearbyRoomsMap[2].substring(9);
            nearbyRoomsMap[7] = "****    ****\n";
            nearbyRoomsMap[8] = "****    ****\n";
        }
        if(direction.equals("North")){
            nearbyRoomsMap[0] = "****    ****\n";
            nearbyRoomsMap[1] = "****    ****\n";
            nearbyRoomsMap[2] = nearbyRoomsMap[2].substring(0,3) + "*    *" + nearbyRoomsMap[2].substring(9);
        }
    }

}
