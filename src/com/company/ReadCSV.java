
package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
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
        int lineNum = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {
                lineNum++; // Keeps track of line num in case of any errors.

                // use comma as separator
                String[] roomData = line.split(cvsSplitBy);
                checkColumns(lineNum, roomData, "roomData", "roomData.csv");
                try {
                    Integer.parseInt(roomData[0].trim());
                    Integer.parseInt(roomData[1].trim());
                } catch(NumberFormatException e){
                    System.out.println("*** roomData - Invalid input for ROWS and COLUMNS.");
                    System.out.println("*** roomData - Line " + lineNum + " Input for ROWS or COLUMNS must be an integer.");
                    System.out.println("*** roomData - Line " + lineNum + " ROW: " + roomData[0].trim() + " COLUMN: " + roomData[1].trim());
                }
                int roomRow = Integer.parseInt(roomData[0].trim());
                int roomColumn = Integer.parseInt(roomData[1].trim());
                String roomName = roomData[2].replace("\"","").trim();
                String roomDescription = roomData[3].replace("\"","").replace("\\n", "\n").trim();


                Room newRoom = new Room(roomRow, roomColumn, roomName, roomDescription);
                rooms.add(newRoom);

            }
        } catch (Exception e) {
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
        String csvSplitBy = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
        int lineNum = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {
                lineNum++; // Keeps track of line num in case of any errors.

                // use comma as separator
                String[] itemData = line.split(csvSplitBy);

                checkColumns(lineNum, itemData, "itemData", "itemData.csv");

                String itemName = itemData[0].replace("\"","").trim();
                String itemDescription = itemData[1].replace("\"","").trim();
                String itemInitialLocation = itemData[2].replace("\"","").trim();
                String eventTriggerLocation = itemData[3].replace("\"","").trim();
                String eventTriggerDescription = itemData[4].replace("\"","").trim();

                Room itemInitialRoom = World.getRoom(itemInitialLocation);
                Room triggerRoom = World.getRoom(eventTriggerLocation);

                if(itemInitialRoom == null){
                    System.out.println("*** Error on line " + lineNum + " of itemData.csv. Cannot find the initial room \"" + itemInitialLocation+"\"");
                }
                if(triggerRoom == null){
                    System.out.println("*** Error on line " + lineNum + " of itemData.csv. Cannot find the trigger room \"" + eventTriggerLocation+"\"");
                }

                Item newItem = new Item(itemName, itemDescription, itemInitialRoom);
                items.add(newItem);

                Event event = new Event(triggerRoom, newItem, eventTriggerDescription);
                World.events.add(event);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;

    }

    private static void checkColumns(int lineNum, String[] data, String dataName, String filename) {
        int requiredLengthOfData = 0;
        if(dataName.equals("itemData")){ requiredLengthOfData = 5; }
        if(dataName.equals("roomData")){ requiredLengthOfData = 4; }

        String dataText = "";
        for(int i=0; i< data.length; i++){
            dataText+=data[i];
            if(i<data.length-1){
                dataText+=",";
            }
        }


        int expectedNumQuotes = 0;
        if(dataName.equals("itemData")){ expectedNumQuotes = 10; }
        if(dataName.equals("roomData")){ expectedNumQuotes = 4; }


        if(!validQuoteCount(dataText, expectedNumQuotes)){
            System.out.println("*** " + dataName + " - ERROR ON Line " + lineNum + ".");
            System.out.println("*** " + dataName + " - ERROR INVALID NUMBER OF QUOTES");
            System.out.println("*** " + dataName + " - Num Quotes expected: " + expectedNumQuotes + ". Num Quotes found: " + getNumQuotes(dataText));
            System.out.println("*** " + dataName + " - Line " + lineNum + " contents: " + dataText);
        }
        else {
            if(data.length != requiredLengthOfData){
                System.out.println("*** " + dataName + " - ERROR ON Line " + lineNum + ".");
                System.out.println("*** " + dataName + " - Line " + lineNum + " contents: " + dataText);
                System.out.println("*** " + dataName + " - WARNING on line " + lineNum + ". You have supplied " + data.length + " columns of data. The file parser was expecting " +requiredLengthOfData + " columns.");
            }
        }
    }

    private static boolean validQuoteCount(String data, int expectedNumQuotes) {
        int numQuotes = getNumQuotes(data);
        if(numQuotes!= expectedNumQuotes) {
            return false;
        }
        return true;
    }

    private static int getNumQuotes(String data) {
        int numQuotes=0;
        int index = data.indexOf("\"");
        while (index >= 0){
            numQuotes++;
            index = data.indexOf("\"", index + 1);
        }
        return numQuotes;
    }
}
