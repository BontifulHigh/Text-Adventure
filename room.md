---
title: Room Class
---

Rooms in the game
====

The Text Adventure Game consists of a world populated by `Rooms`. 
A room is a "location" in the world. Each room has a:
1. Name
2. Description
3. Column
4. Row 
5. List of items.

The purpose of the Room class is to get and set each of these fields. 
One additional method the room has is `getDescription()`

## `getDescription()`
The `getDescription()` method creates a description of the room that *includes* all of the items' descriptions. 
It only describes items that are still in the room and can be *taken* by the player.