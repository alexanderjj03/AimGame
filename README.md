# Basic Mouse Aim Trainer

## By Alexander Jacobson (66453994)

### Made for CPSC 210 (UBC class)

As the title suggests, this application will help a user improve their aim
with a mouse. It will create a blank screen, and one or multiple "targets" 
(small circles) will appear at a random locations on the screen. When the 
user clicks a target, it will disappear and a new one will appear at a 
different random location. It is the user's job to click these targets as 
fast as possible as they appear. If the user "misses" the target, it will 
stay on the screen until it is clicked. The game will continue until a 
certain number of targets (100 by default) are clicked, or the user ends 
the game early.

Anyone who is interested in improving at video games (particularly, 
first-person shooters) may be interested in using this application. My main
motivations behind this project are my own interests in video games, and my
frustrations with existing aim trainers. Most aim trainers either have very
limited customizability, or put strain on a user's computer. My vision is to 
create an aim trainer that is straightforward to use, doesn't take much cpu 
or memory, and can easily be customized to suit a user's wants. Some of the 
ways this application can be customized (more to come) are listed in the 
user stories below.

As a user, I want to be able to:
- Start a new game with a specified number of targets on screen
- Remove a target from the screen by left clicking it, and have a new target
appear at a random location
- **Manually add or remove a target to the screen at any point in the game**
- Clear the screen and end the game early
- Restart the game by removing all present targets from the screen, and adding
one new one at a random location.
- Be reminded to save my game to file when ending the game and have the option
to do so or not.
- Have the option to load the saved game upon starting the application.
- See my target accuracy on the screen (targets hit/times clicked)
- Control the size of the targets that appear on the screen

### Instructions for Grader

- You can generate the first required event related to adding Xs to a Y by
clicking the spacebar when the game is active (after all the popups). This 
adds a target to the screen (more specifically, the game's target collection)
at a random location. 
- You can generate the second required event related to adding Xs to a Y by
clicking q when the game is active. This removes the most recently added
target from the game's target collection (and so it disappears off the screen).
- Not required, but you can generate a third event related to adding Xs to a Y
by left clicking any of the targets (purple circles) on the screen. That target
is removed from the game's target collection and a new one is added at a random
location.
- You can locate my visual component by clicking run on main. The first image
that appears is the visual component. It's more or less the game's introduction.
- You can save the state of my application by clicking x while the game is
active. A popup window should appear asking whether you want to save your game.
Select yes (or no if you don't).
- You can reload the state of my application right after the game's introduction.
A popup should appear, asking whether you want to load your saved game. If you
want to do this, say yes. If you select no, a new game will be created. You will
be prompted to enter how many targets you want on screen at the start of the game,
and how big you want the targets that appear to be.

### Phase 4: Task 2 (events that typically occur)

- New target added to screen at 122, 148.
- New target added to screen at 337, 435.
- Target at 337, 435 was clicked and removed from screen.
- New target added to screen at 340, 167.
- Target at 122, 148 was clicked and removed from screen.
- New target added to screen at 43, 105.
- Target at 43, 105 was manually removed from screen.
- Target at 340, 167 was clicked and removed from screen.
- New target added to screen at 493, 574.
- All targets removed from screen.
- New target added to screen at 289, 206.
- Target at 289, 206 was clicked and removed from screen.
- New target added to screen at 593, 101.

The eventlog printed after the program ends also prints the dates for 
when each event occurs. I removed these here, as they clutter the
eventlog, making it harder to understand what events actually happened.

### Phase 4: Task 3

If I had more time to work on my project, I would make two main changes.
For one, I would merge Main and AimFrame. The only purpose of Main is to 
create a new AimFrame. I tried to merge these two classes without success.

In addition, as is, AimFrame calls JsonReader and AimGame calls JsonWriter.
As JsonReader and JsonWriter go hand in hand, I would refactor my project so
that AimFrame calls both JsonReader and JsonWriter. This would require a lot
of work, however.
