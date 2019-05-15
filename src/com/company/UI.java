package com.company;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * The UI class provides the User Interface in which the player will
 * interact with while playing the game game via input and output.
 *
 * @author Jeremy Wolff
 * @version 1.0
 */
public class UI {
    public static boolean done = false;
    public static Scanner scnr = new Scanner(System.in);
    static World world;
    static Player player;

    /**
     * Constructor for a UI object.
     * @param gamePlayer the player made in the Game class is passed into this object- mostly for convenience.
     * @param gameWorld the world made in the Game class is passed into this object- mostly for convenience.
     */
    public UI (Player gamePlayer, World gameWorld){
        player = gamePlayer;
        world = gameWorld;
    }

    /**
     * Menu in which the player interacts with. Handles a few different aspects of the game:
     * <ol>
     * <li>Prints out introduction text.</li>
     * <li>Prints out the details of the room.</li>
     * <li>Interprets the player's input text and executes the associated commands.</li>
     * </ol>
     */
    public void menu(){
        System.out.print(intro());
        boolean isDescribingRoom = true; // determines whether or not we describe the room as the first action in the validChoice loop.

        while (!done){
            String commandString = null;
            String command = ""; // Command ends up being the first word they type.
            String argument = null; // Argument is the second word, if it exists.

            // Describe the room to the player
            if(isDescribingRoom) {
                System.out.print(world.getRoomDetails(player.getCurrentRoom()));
                isDescribingRoom = false; // Set this back to false - It may change later on after the player decides to move or otherwise describe the room.
            }

            System.out.print("\n>\t");
            commandString = scnr.nextLine();
            boolean validChoice = false; // TODO - We should replace this validChoice stuff with a validChoice function instead.

            while(!validChoice) {


                // Get the command and [optional] argument as lowercase strings
                StringTokenizer parser = new StringTokenizer(commandString, "\t,.;:?! ");
                if(parser.hasMoreTokens()) {
                    command = parser.nextToken().toLowerCase();
                }
                else {
                    System.out.print("\nSorry I couldn't hear you.");
                }
                if(parser.hasMoreTokens()){
                    argument = "";
                }
                while(parser.hasMoreTokens()){
                    argument += parser.nextToken();
                    if(parser.hasMoreTokens()){
                        argument +=" ";
                    }
                }

                // Call the appropriate command of the Player. If you add new commands,
                // add a test for the new command name here and make the appropriate
                // call(s) to the Player.
                switch (command) {
                    case "help":
                        System.out.print(help());
                        validChoice = true;
                        break;
                    case "take":
                        player.tryToTakeItem(argument);
                        validChoice = true;
                        break;
                    case "use":
                        player.tryToUseItem(argument);
                        validChoice = true;
                        break;
                    case "inventory":
                    case "i":
                        player.outputInventory();
                        validChoice = true;
                        break;
                    case "go":
                        if(player.tryToMove(argument.toLowerCase())) {
                            isDescribingRoom = true;
                        }
                        else {
                            System.out.print("\nUnable to move that direction.");
                        }
                        validChoice = true;
                        break;
                    case "n":
                    case "north":
                        if(player.tryToMove("n")) {
                            isDescribingRoom = true;
                        }
                        else {
                            System.out.print("\nUnable to move that direction.");
                        }
                        validChoice = true;
                        break;
                    case "e":
                    case "east":
                        if(player.tryToMove("e")) {
                            isDescribingRoom = true;
                        }
                        else {
                            System.out.print("\nUnable to move that direction.");
                        }
                        validChoice = true;
                        break;
                    case "s":
                    case "south":
                        if(player.tryToMove("s")) {
                            isDescribingRoom = true;
                        }
                        else {
                            System.out.print("\nUnable to move that direction.");
                        }
                        validChoice = true;
                        break;
                    case "w":
                    case "west":
                        if(player.tryToMove("w")) {
                            isDescribingRoom = true;
                        }
                        else {
                            System.out.print("\nUnable to move that direction.");
                        }
                        validChoice = true;
                        break;
                    case "map":
                        world.printNearbyRoomsMap();
                        validChoice = true;
                        break;
                    case "q":
                    case "quit":
                        done = true;
                        validChoice = true;
                        break;
                    default:
                        System.out.print("Invalid option. Use help if you're not sure what you can do.");
                        System.out.print("\n>\t");
                        commandString = scnr.nextLine().toLowerCase();
                        break;
                }
            }
        }
    }

    /**
     * Retrieve the introductory text shown to the player at the beginning of the game.
     * @return The introductor text as a string.
     */
    public static String intro(){
        String introText = "";
        // ASCII Font found courtesy of http://patorjk.com/software/taag
        // NOTE: If your font has any \'s in them, they will need to be "escaped".
        introText += "  ____  ___    __ __    ___  ____   ______  __ __  ____     ___ \n";
        introText += " /    ||   \\  |  |  |  /  _]|    \\ |      ||  |  ||    \\   /  _]\n";
        introText += "|  o  ||    \\ |  |  | /  [_ |  _  ||      ||  |  ||  D  ) /  [_ \n";
        introText += "|     ||  D  ||  |  ||    _]|  |  ||_|  |_||  |  ||    / |    _]\n";
        introText += "|  _  ||     ||  :  ||   [_ |  |  |  |  |  |  :  ||    \\ |   [_ \n";
        introText += "|  |  ||     | \\   / |     ||  |  |  |  |  |     ||  .  \\|     |\n";
        introText += "|__|__||_____|  \\_/  |_____||__|__|  |__|   \\__,_||__|\\_||_____|\n";

        return introText;
    }

    /**
     * Returns a list of available commands.
     * @return The list of available commands.
     */
    public static String help(){
        String commands = "\nAVAILABLE COMMANDS: \n";
        commands += " - help\n";
        commands += " - go <DIRECTION>, n, s, e, w\n";
        commands += " - map\n";
        commands += " - take <ITEM>\n";
        commands += " - use <ITEM>\n";
        commands += " - inventory or i\n";
        commands += " - quit or q";
        return commands;
    }
}
