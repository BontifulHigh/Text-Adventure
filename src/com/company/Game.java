package com.company;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * The Game class creates the World and a Player in the world.
 * It also handles the menu User Interaction via command line.
 *
 * @author Jeremy Wolff
 * @version 1.0
 */
public class Game {

    static Player player;
    static World world;
    static UI ui;

    /**
     * Starts and runs the game.
     * A main program executes the menu method.
     * @param args Arguments.
     */
    public static void main(String[] args) {
        world = new World("src/com/company/newRoomData.txt", "src/com/company/itemData.csv");
        player = new Player(world.getStartingRoom());
        ui = new UI(player, world);
        ui.menu();
    }
}
