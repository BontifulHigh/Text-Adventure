package com.company;

import java.util.ArrayList;

/**
 * A player object represents the person playing the game. The player can move around
 *
 * @author Jeremy Wolff
 * @version 1.0
 */
public class Player {

    private String name="Player";
    private int hp = 100;
    private Room currentRoom;
    private ArrayList<Item> inventory = new ArrayList<Item>();

    /**
     * Constructor for the (single) object of class player.
     * @param startingRoom
     */
    public Player(Room startingRoom){
        setCurrentRoom(startingRoom);
    }

    /**
     * Returns the Player's name.
     * @return The player's name.
     */
    public String getName(){
        return name;
    }

    /**
     * Sets the Player's name.
     * @param newName The new name for the player.
     */
    public void setName(String newName){
        name = newName;
    }

    /**
     * Returns the Player's HP.
     * @return The HP of the player.
     */
    public int getHp(){
        return hp;
    }

    /**
     * Sets the Player's HP.
     * @param newHp the new HP amount.
     */
    public void setHp(int newHp){
        hp = newHp;
    }

    /**
     * Returns the room that the player is currently in.
     * @return The room that the player is currently in.
     */
    public Room getCurrentRoom(){
        return currentRoom;
    }

    /**
     * Sets the room that the player is currently in.
     * @param room the new location that the player has entered.
     */
    public void setCurrentRoom(Room room){
        currentRoom = room;
    }

    /**
     * Adds an item to the player's inventory.
     * @param item Item to be added.
     */
    public void addItemToInventory(Item item){
        inventory.add(item);
    }

    /**
     * retrives the entire inventory of the player.
     * @return Inventory of items.
     */
    public ArrayList<Item> getInventory(){
        return inventory;
    }

    /**
     * Finds a specific item in the player's inventory.
     * @param itemName item to find.
     * @return Item, if found. If not found, then null.
     */
    public Item findItem(String itemName, ArrayList<Item> itemList){
        itemName = itemName.toUpperCase();
        String invItemName;
        for(Item item : itemList){
            invItemName = item.getName().toUpperCase();
            if(itemName.equals(invItemName)){
                return item;
            }
        }
        return null;
    }

    /**
     * The player is attempting to take an item in the room.
     * This method iterates through all items in the room to check if any of their names matches that of the name of the item the player tried to pick up.
     * If the item is found, it is added to the player inventory and removed from the room.
     * If the item is not found, a message is displayed saying that The item was not found.
     * @param itemName the name of the item the player is trying to take.
     */
    public void tryToTakeItem(String itemName) {
        itemName = itemName.toUpperCase();
        Item itemToRemove = null;

        // Loop through each item in the current room's item list to find the item specified.
        Item takenItem = findItem(itemName, getCurrentRoom().getItems());
        if(takenItem != null) {
            addItemToInventory(takenItem);
            getCurrentRoom().removeItem(takenItem);
            System.out.print("You grabbed the " + itemName + "\n");
        }
        else {
            System.out.print("The item " + itemName + " was not found.\n");
        }
    }

    /**
     * If the item actually exists, then we attempt to activate the item event via the item's useItem function.
     * @param itemName The name of the item that the player tried to use.
     */
    public void tryToUseItem(String itemName) {
        Item item = findItem(itemName, getInventory());
        if(!(item == null)){
            item.useItem(getCurrentRoom());
        }
        else {
            System.out.print("Sorry you don't have " + itemName);
        }
    }

    /**
     * Loop through each item in the player's inventory and add the item name to a temporary variable, inventoryText. Then print out the inventoryText.
     */
    public void outputInventory() {
        String inventoryText = "";
        inventoryText += "ITEMS IN YOUR INVENTORY\n";
        for(Item item : getInventory()){
            inventoryText += " " + item.getName() +"\n";
        }
        System.out.print(inventoryText);
    }

    /**
     * Try to move the player in the direction that the player has indicated.
     * @param direction A reference to the direction that the user is moving
     *
     * @return  true if the move was successful.
     */
    public void tryToMove(String direction){
        int currentRow = getCurrentRoom().getRow();
        int currentColumn = getCurrentRoom().getColumn();
        Room newRoom = getCurrentRoom().getConnectingRoom(direction);

        if(newRoom != null){
            setCurrentRoom(newRoom);
        }
        else {
            System.out.print("\nUnable to move " + direction);
        }
    }
}
