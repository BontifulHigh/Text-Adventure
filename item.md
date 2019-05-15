---
title: Item Class
---

Items in the game
====

**Summary:** The item class allows the player to pick up and use items in the game. This can be helpful for utilizing keys, granting players special options, or generally allowing players to get more information from things in a room.

- An item in the game is located in an `initialroom`.
- Item's have a `name` and a `description` of the item.
- When a player attempts to use an item, the method `useItem(room)` will be called. This method that checks if there is an associated `event` in the current room.
- Items can be successfully triggered in rooms with an associated `event`.