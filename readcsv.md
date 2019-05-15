---
title: ReadCSV Class
---

Reading files into the game
====

This class has three responsibilities:
1. Read the **roomData.csv** to generate `rooms` for the `world`.
2. Read the **itemData.csv** to generate `items`
3. Read the **itemData.csv** to generate `events`

To know how this class works, you must first understand how the **itemData.csv** and **roomData.csv** files are constructed.

### roomData.csv
The rooms in the world are constructed in a 2-dimensional plane. 
Each room has a row and a column number which determines it's placement in the world. 

The **roomData.csv** file consists of 4 columns of data: 
1. room Row
2. room Column
3. room Name
4. room Description

This is laid out in the file as a COMMA-SEPERATED-VALUE, meaning that a comma is what seperates each column from another.

| room Row | room Column | room Name | room Description |
|:---:|:---:|:---:|:---:|
|0|1|Library|You are in the library! Wow there are so many books in here! Hmm it looks like a book is missing though.|

The example in the table above will look like this in the actual .csv file:

`0,1,"Library","You are in the library! Wow there are so many books in here! Hmm it looks like a book is missing though."`

### itemData.csv
The **itemData**` file functions in the same way, except it has different columns for it's data:
1. item Name
2. item Description
3. initial Location
4. event Trigger Location
5. event Trigger Description

| item Name | item Description | initial Location | event Trigger Location | event Trigger Description |
|:---:|:---:|:---:|:---:|:---:|
| RED KEY | There is a RED KEY that has been meticulously carved to be shaped like an alligator.| Main | Office | You opened the lock |

The example in the table above will look like this in the actual .csv file:

`"RED KEY","There is a RED KEY that has been meticulously carved to be shaped like an alligator.","Main","Office","You opened the lock"`

## `readRoomData()`
This method parses the *roomData.csv* file, generate rooms, and add them to the `rooms` variable. 

*NOTE:* I wouldn't recommend making changes to this method unless you needed additional data to be pulled from the **roomData.csv** file.

## `readItemData()`
This method parses the *itemData.csv* file. It generates items and adds them to the `items` variable. It also generates events and adds them to the `events` variable.

*NOTE:* I wouldn't recommend making changes to this method unless you needed additional data to be pulled from the **itemData.csv** file.
