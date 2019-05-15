---
title: World Class
---

The game world
====

This class represents the game world itself. 
It has several static variables which maintain the state of variables properties of the world.

### Variables
- `List<Room> map` - contains a list of all the rooms in the world.
- `List<Item> items` - contains a list of all the items in the world.
- `List<Event> events` - contains a list of all the events in the world.
- `String[] nearbyRoomsMap` - an array of text that stores the printedMap.
- `Player player` - a reference to the player object in the game.
- `Room startingRoom` - a reference to the room that the player starts in.

## `World(Player gamePlayer)`
The constructor method of the world. It requires a parameter for the player reference. 
This method populates the `map`, `items`, `player`, and places the `items` into their associated roms.

## `roomExists(int row, int column)`
This method returns a `boolean` that represents whether or not a room exists at the given row and column.

If a `room` is found, it returns `true`.

If no `room` is found, it returns `false`.

## `getRoom(int row, int column)`
This method retrives the room that is associated with a specific row and column. 

If the `room` was found, the method returns it.

If the `room` wasn't found then the method returns `null`.

## `getRoom(String name`
This method retrives the room that is associated with a specific String. 

If the `room` was found, the method returns it.

If the `room` wasn't found then the method returns `null`.

## `getRoomDetails(Room room)`
This method has two responsibilities:
1. Give details to the player about the room they are in- print the details.
2. Set the `nearbyRoomsMap` so that a player can visually see what paths are available to them.

## `placeItemsInRooms()`
Loop through the list of `items` and place each item into their associated `room`'s `item` list.

## `resetNearbyRoomsMap()`
The `nearbyRoomsMap` needs to be reset each time the player moves into a new room. 
This method sets it back to a default square so that other directions can be added.

## `printNearbyRoomsMap()`
Print out the `nearbyRoomsMap`, line by line.

## `addNearbyRoom(String direction)`
When the `description` of a `room` is provided, the `addNearbyRoom` function is called for each `direction` that is available to the player. 
This allows the player to have the most current `nearbyRoomMap` available.