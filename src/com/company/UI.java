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
    /**
     *
     */
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

        while (!done){

            // Describe the room to the player
            System.out.print(world.getRoomDetails(player.getCurrentRoom()));

            System.out.print("\n>\t");
            String commandString = scnr.nextLine();
            boolean validChoice = false; // TODO - We should replace this validChoice stuff with a validChoice function instead.

            while(!validChoice) {

                // Get the command and [optional] argument as lowercase strings
                StringTokenizer parser = new StringTokenizer(commandString, "\t,.;:?! ");
                String command = getCommand(parser);
                String argument = getArgument(parser);

                validChoice = true;

                // Call the appropriate command of the Player. If you add new commands,
                // add a test for the new command name here and make the appropriate
                // call(s) to the Player.
                switch (command) {
                    case "help":
                        System.out.print(help());
                        break;
                    case "take":
                        player.tryToTakeItem(argument);
                        break;
                    case "use":
                        player.tryToUseItem(argument);
                        break;
                    case "inventory":
                    case "i":
                        player.outputInventory();
                        break;
                    case "go":
                        player.tryToMove(argument.toLowerCase());
                        break;
                    case "n":
                    case "north":
                    case "e":
                    case "east":
                    case "s":
                    case "south":
                    case "w":
                    case "west":
                        player.tryToMove(command);
                        break;
                    case "map":
                        world.printNearbyRoomsMap();
                        break;
                    case "q":
                    case "quit":
                        done = true;
                        break;
                    default:
                        System.out.print("Invalid option. Use help if you're not sure what you can do.");
                        System.out.print("\n>\t");
                        validChoice = false;
                        commandString = scnr.nextLine().toLowerCase();
                        break;
                }
            }
        }
    }

    private String getCommand(StringTokenizer parser) {
        String command="";
        if(parser.hasMoreTokens()) {
            command = parser.nextToken().toLowerCase();
        }
        return command;
    }

    private String getArgument(StringTokenizer parser) {
        String argument = "";
        while(parser.hasMoreTokens()){
            argument += parser.nextToken();
            if(parser.hasMoreTokens()){
                argument +=" ";
            }
        }
        return argument;
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
