
package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The ReadCSV class allows us to store all room and item data into CSV files.
 * This data is interpretted by the program, and generates the appropriate objects.
 */
public class ReadCSV {

    /**
     * Read the roomData.csv file and create room objects for each line in the CSV file.
     * Each room is then added to a rooms list.
     * @return The list of rooms that have been created.
     */
    public static ArrayList<Room> readRoomData() {
        ArrayList<Room> rooms = new ArrayList<Room>();

        String csvFile = "src/com/company/roomData.csv";
        String line = "";

        /*
         * Using regular expressions to help split the text line.
         * Code supplied by user Bart Kiers
         * Link to thread: https://stackoverflow.com/questions/1757065/java-splitting-a-comma-separated-string-but-ignoring-commas-in-quotes
         */
        String cvsSplitBy = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] roomData = line.split(cvsSplitBy);
                Room newRoom = new Room(Integer.parseInt(roomData[0]), Integer.parseInt(roomData[1]), roomData[2].replace("\"",""), roomData[3].replace("\"",""));
                rooms.add(newRoom);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return rooms;

    }

    /**
     * Read the itemData.csv file and create objects based on this data
     * <ol>
     * <li>Item Objects - Created objects for each item, and then each item is added to an items list.</li>
     * <li>Event Objects - Created objects for each event. Each event is added to the World.events list.</li>
     * </ol>
     * @return List of Items created.
     */
    public static ArrayList<Item> readItemData(){
        ArrayList<Item> items = new ArrayList<Item>();

        String csvFile = "src/com/company/itemData.csv";
        String line = "";
        String cvsSplitBy = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] itemData = line.split(cvsSplitBy);
                Room room = World.getRoom(itemData[2].replace("\"",""));
                Room triggerRoom = World.getRoom(itemData[3].replace("\"",""));

                Item newItem = new Item(itemData[0].replace("\"",""),itemData[1].replace("\"",""),room);
                items.add(newItem);

                Event event = new Event(triggerRoom, newItem, itemData[4].replace("\"",""));
                World.events.add(event);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return items;

    }
}
