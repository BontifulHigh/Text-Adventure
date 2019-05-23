## Your Adventure Awaits... 

#### **Purpose of this assignment:**

*   Learn how to read other software developers' code.
*   Learn how to ask questions TO a developer about how their code works.
*   Practice Object Oriented Programming techniques by studying an example.
*   Modify other people's code to suit YOUR needs.
*   Be creative!

The initial program code that you are given has basic functionality. However, as you work on this project you will be tasked with making changes and implementing additional features into the game.

#### **Getting Started:**

1. Click on the Download .zip button at the top of this page, extract the folder, and open the project using an IDE such as intellij.

2. Run the program a few times and play around with the game. 

3. Come back to this website and peruse around to gain more information about the code.

#### **Assignment Details:**

**Part 1: Making Your Map**
- Navigate the information on this website and learn about how the program works. 
- Plan out the story of your adventure game. 
  - What rooms will be in your adventure? 
  - What items will be there? 
  - Are there any additional features you might want to implement?
- Create your game map on paper first- what rooms will there be and how will they connect?
- Make changes to the csv files so that the game has the rooms, items, and events that you came up with.

**Part 2: Adding a Feature**
- Identify a feature that you'd like to add to the game. Work on implementing your feature into the game by working with other classmates and Mr. Wolff. Don't hesitate to ask Mr. Wolff questions about what direction to go for the feature you choose to implement.
- It may help to start with some ideas for features to work on:
  - Adding a turn tracker. Maybe the game ends after some # of turns.
  - Making paths only work if you have a specific item.
  - Adding monsters that you can attack!
  - Making the UI neater and more interesting for the player.
- For this part, you will be evaluated on your effort, communication, and how well you challenge yourself during this project.
- You WILL NOT be evaluated on whether or not the feature is successfully implemented. Some features will be more difficult than others, and that's great!

**Part 3: Write-up Report**

Being able to describe code and write about how it should work is an important aspect of being a software developer or working with software developers. 
For part 3, you will describe the feature that you attempted to add to the game, whether or not the feature was successfully implemented. 
You will talk about the process of development, which files you edited and whether or not the feature was successful. 
 
Here is a template that you can work with for this write-up report.

*Feature Description:* 

*Process:* 

*Files edited:*

*Analysis:*


The following is a sample of what this write-up report might look like:

&nbsp;&nbsp;&nbsp;&nbsp;*Feature Description:* 
The feature that I tried to implement was a monster combat system. 
The idea was to make it so the player can encounter a monster when they enter a specific room and then they could chose to fight the monster.
If they chose to fight the monster, then they would have a 50% chance of winning based on a generated random number.

&nbsp;&nbsp;&nbsp;&nbsp;*Process:* I attempted to add this feature using the following steps:
- Identify which room to encounter the monster- in my case it was the "Treasure" room.
- Identify where in the code I could check if the player was in the "Treasure" room. I found this to be in the menu method of the UI class, specifically when the player moves to a room.
- Add an if statement to see if the player's currentRoom's name was the same as "Treasure".
  - If it is, then prompt the user to see if they want to fight the monster.
    -  If they want to fight, then generate a random number between 1 and 2. If it's 1, then they die.
    - If they die, print out "Game Over"
    - If they don't die, print out "You win!"
- Add the preceding if logic to each movement command.


&nbsp;&nbsp;&nbsp;&nbsp;*Files edited:* UI.java

&nbsp;&nbsp;&nbsp;&nbsp;*Analysis:* Although I was able to get this fight to trigger in the "Treasure" room, I wasn't able to figure out how to end the game.
Instead, my program just said "You win!" and then continued to play. If I had more time for this assignment, I would have tried to find out how to end the game.

**Submission**
Prepare the following items for submission:
1. roomData.csv
2. itemData.csv
3. ONE of the .java files that you edited to added
4. Your written report

[Link to submission form](https://forms.gle/StD8fMAqJyVV2q576)