## Your Adventure Awaits...

* * *

#### **Purpose of this assignment:**

*   Learn how to read other software developers' code.
*   Learn how to ask questions TO a developer about how their code works.
*   Practice Object Oriented Programming techniques by studying an example.
*   Modify other people's code to suit YOUR needs.
*   Be creative!

The initial program code that you are given has basic functionality. However, as you work on this project you will be tasked with making changes and implementing additional features into the game.

#### **Getting Started:**

To get started 

#### **Taking a look at the User Interface:**

The most important thing to do right now is to get familiar with how the User Interface operates. This means playing around with the program, navigating around the map, and testing out different commands. Your goal right now is to learn how the **`menu()`** method works in the UI class.

Players can do the following things in the game:

<table style="border-collapse: collapse; width: 59.9815%; height: 296px;" border="1">

<tbody>

<tr style="height: 24px;">

<td style="width: 25.68%; height: 24px;">**Command**</td>

<td style="width: 74.32%; height: 24px;">**Description**</td>

</tr>

<tr style="height: 24px;">

<td style="width: 25.68%; height: 24px;">"help"</td>

<td style="width: 74.32%; height: 24px;">Asking for help gives all available commands</td>

</tr>

<tr>

<td style="width: 25.68%;">"go [DIRECTION]" or "[DIRECTION]"</td>

<td style="width: 74.32%;">Attempt to move in the [DIRECTION] the player specified.</td>

</tr>

<tr style="height: 24px;">

<td style="width: 25.68%; height: 24px;">"map"</td>

<td style="width: 74.32%; height: 24px;">Print out the map to show which directions the player can move.</td>

</tr>

<tr style="height: 24px;">

<td style="width: 25.68%; height: 24px;">"take [ITEM]"</td>

<td style="width: 74.32%; height: 24px;">Take from the room the [ITEM] that the player specified. the [ITEM] will be placed in the player's inventory.</td>

</tr>

<tr style="height: 24px;">

<td style="width: 25.68%; height: 24px;">"use [ITEM]"</td>

<td style="width: 74.32%; height: 24px;">Use the [ITEM] that the player specified. The [ITEM] will only work if they are in the correct room to activate an EVENT.</td>

</tr>

<tr style="height: 24px;">

<td style="width: 25.68%; height: 24px;">"inventory" or "i"</td>

<td style="width: 74.32%; height: 24px;">Prints out every item in the player's inventory.</td>

</tr>

<tr style="height: 24px;">

<td style="width: 25.68%; height: 24px;">"quit" or "q"</td>

<td style="width: 74.32%; height: 24px;">Quit the game.</td>

</tr>

</tbody>

</table>

First, it is important to analyze what happens when each command is executed. 

**help**

When this command is entered, the program executes the method **`help()`** in the UI class.  **NOTE:** If you add any additional commands to your game, you should also add the command to the help list so that players know they can use it.

**go [DIRECTION] or [DIRECTION]**

*   This command allows the player to _try to move_ in one of the cardinal directions (North, East, South, West).
*   When either of these commands are recognized, the direction is passed into the `player.tryToMove(direction)` method.
*   If the move was successful, then the player's `currentRoom` is updated. 
*   If the move was unsuccessful, then the program outputs

    <pre>Unable to move that direction.</pre>

**map**

*   This commands outputs a small map for the player to see which directions they can go.
*   The map code is generated in the following way:
    *   First, each time the player enters a room, `resetNearbyRoomsMap()` is called in the World class..
    *   Then, for each direction that the room allows the player to move, call the function `addNearbyRoom(direction)` in the World class to add that direction to the `nearbyRoomsMap` text.

**take [ITEM]**

*   Executes the `player.takeItem()` method detailed below:
    *   Find out what <span style="color: #ff0000;">[ITEM]</span> the player is trying to take by looking for the remaining words in the input.  
        The following example shows that <span style="color: #ff0000;">RED KEY</span> ends up being the name of the <span style="color: #ff0000;">[ITEM]</span> that the player attempts to take:

<pre style="padding-left: 80px;">This is where the Player starts! There is a RED KEY that has been meticulously carved to be shaped like an alligator.  
You may go [South] [East].  
> take <span style="color: #ff0000;">RED KEY  
</span></pre>

*   *   Check if that item actually exists in the room (That way the player can't grab items that don't exist or are in other rooms). 
    *   Add it to the player's inventory, `player.addItemToInventory(item)` , and remove it from the room, `player.getCurrentRoom().removeItem(itemToRemove)`.

**use <span style="color: #ff0000;">[ITEM]</span>**

*   Executes the player.useItem() method detailed below:

**inventory or i**

*   Executes the `player.outputInventory()` function detailed  blow:
    *   Loop through each item in the player's inventory and add the item name to a temporary variable `inventoryText`.
    *   Then print out the `inventoryText` to show to the player.

**Quit or q**

*   Set `done` to true to end the menu loop
*   Stops the program from running.
