---
title: Player Class
---

Player objects
====

**Summary:** A player object represents the person playing the game. The player can move around, pick up items, and use items.


## `findItem(String itemName)`
Search through the player's `inventory` and return the item if it's name matches the provided `itemName`

*return: `Item`* - The item, if found. If not, then null.


## `tryToTakeItem(String itemName)`
Loop through each item in the `currentRoom` and see if any of the items match the itemName provided.

If the item is found, it is added to the player's `inventory` and removed from the `room`. A message is then presented that the player grabbed the item.
 
If the item is not found, a message is displayed saying that the Item was not found.


## `tryToUseItem(String itemName)`
Find the item according to the itemName provided. If the item is found in the player's `inventory`, then the `useItem` method is called.

If the item is not found, then output a message that the player doesn't have that item.


## ```tryToMove(String direction)``` 
This function attempts to move the player in a direction that they have specified. It checks if the direction matches one of the valid direction strings:
- 'n' or 'north'
- 's' or 'south'
- 'w' or 'west'
- 'e' or 'east'

*return: `boolean`* - The ui object needs to know if he move was successful- so this function will return true if it was a success, or false if not.


## `outputInventory()`
Print out the list of `items` in the player's `inventory`
