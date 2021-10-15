package com.mycompany.a1;

import java.util.ArrayList;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

/**
 * This class is the model of the Game. The GameWorld class holds a collection
 * of game objects, state variables, and game methods. This class is also
 * responsible for the creation of the initial state of the game world. This
 * class's methods will interact with game objects and states such as updating
 * all of the object's location on the game, health, and etc. GameWorld class
 * also handles the game state values such as the number of lives the player has
 * and the game clock time.
 * 
 * @author Mickey
 */
public class GameWorld {
	/**
	 * An ArrayList which hold objects of type GameObject
	 */
	private ArrayList<GameObject> gameObjects;
	/**
	 * An int variable that keeps track of the game time.
	 */
	private int gameClockTime;
	/**
	 * An int variable that keeps track of the lives remaining of the Ant object.
	 */
	private int livesRemaining = 3;

	/**
	 * This method creates the initial state of the game world by instantiating and
	 * initializing GameObject types and adding them into an array list.
	 */
	public void init() {
		gameObjects = new ArrayList<>();
		// instantiate and add all gameObject objects. Size for flag can be constant but
		// not needed right now
		gameObjects.add(new Flag(10, new Point(100, 100)));
		gameObjects.add(new Flag(10, new Point(400, 400)));
		gameObjects.add(new Flag(10, new Point(700, 700)));
		gameObjects.add(new Flag(10, new Point(900, 900)));

		// Ant point location is same as the first flag's (x,y) location
		gameObjects.add(new Ant(new Point(100, 100), 15, 10));
		gameObjects.add(new Spider());
		gameObjects.add(new Spider());
		gameObjects.add(new FoodStation());
		gameObjects.add(new FoodStation());

	}

	/**
	 * This method corresponds with the user input of a character "a". This method
	 * increments the speed value of the Ant object (player) by 1.
	 */
	public void accelerate() {
		Ant ant = (Ant) findAnt();
		// validate if speed is greater than max speed before incrementing
		double antHealthToDecimal = (double) ant.getHealthLevel() / 10;
		int antMaxSpeedHealth = (int) (ant.getMaximumSpeed() * antHealthToDecimal);
		if (ant.getSpeed() >= antMaxSpeedHealth) {
			System.out.println("You cannot accelerate because you are at the maximum speed of: " + ant.getSpeed());
		} else {
			ant.setSpeed(ant.getSpeed() + 1);
			System.out.println("You have accelerated, your speed is now: " + ant.getSpeed());
		}

	}

	/**
	 * This method corresponds with the user input of a character "b". This method
	 * decreases the speed value of the Ant object (player) by 1.
	 */
	public void brake() {
		Ant ant = (Ant) findAnt();
		// validate speed is greater than 0 before decrementing
		if (ant.getSpeed() > 0) {
			ant.setSpeed(ant.getSpeed() - 1);
			System.out.println("You have applied brakes. Your speed has decreased to: " + ant.getSpeed());
		} else {
			System.out.println("You cannot brake because your speed is at: " + ant.getSpeed());
		}
	}

	/**
	 * This method corresponds with the user input of a character "l". This method
	 * changes the heading (angle/direction) of the Ant object by 5 degrees to the
	 * left.
	 */
	public void changeHeadingLeft() {

		((Ant) findAnt()).changeHeading(-5);
		System.out.println("Heading has changed by 5 degrees to the left, your heading is now: ");

	}

	/**
	 * This method corresponds with the user input of a character "r". This method
	 * changes the heading (angle/direction) of the Ant object by 5 degrees to the
	 * right.
	 */
	public void changeHeadingRight() {
		((Ant) findAnt()).changeHeading(5);
		System.out.println("Heading has changed by 5 degrees to the right");

	}

	/**
	 * This method corresponds with the user input of a character from "1" to "9".
	 * This method creates a collision between the Ant and Flag objects which
	 * represents a checkpoint reached if the flag is indeed the next sequential
	 * number.
	 * 
	 * @param charCommand represents the flag number that the user has chosen
	 */
	public void collideFlag(char charCommand) {
		Ant ant = (Ant) findAnt();
		// takes in the character input of "1" to "9" from user and parses it into an
		// integer value
		int flagSelected = Integer.parseInt(String.valueOf(charCommand));
		if (ant.getLastFlagReached() + 1 == flagSelected) {
			for (int i = 0; i < gameObjects.size(); i++) {
				// search for instances of Flag and compares the parsed integer flagSelected to
				// make
				// sure such a flag number exists before setting new checkpoint
				if (gameObjects.get(i) instanceof Flag
						&& ((Flag) gameObjects.get(i)).getSequenceNumber() == flagSelected) {
					ant.setLastFlagReached(flagSelected);
					// if the flag reached is the last one then game condition is met and player
					// wins
					if (flagSelected == 4) {
						winGame(true);
					}

					System.out.println("You have reached a flag checkpoint! " + "You are now at flag #"
							+ ant.getLastFlagReached());
					break;
				}
			}
		} else {
			System.out.println("This is not the next flag in sequence, you are " + "currently at flag #"
					+ ant.getLastFlagReached());
		}

	}

	/**
	 * This method corresponds with the user input of a character "f". This method
	 * creates a collision between the Ant and FoodStation object which depletes the
	 * capacity value of the FoodStation and replenishes the foodLevel value of the
	 * Ant object.
	 */
	public void collideFoodStation() {
		for (int i = 0; i < gameObjects.size(); i++) {
			if (gameObjects.get(i) instanceof FoodStation) {
				FoodStation foodStation = (FoodStation) gameObjects.get(i);
				if (foodStation.getCapacity() > 0) {
					Ant ant = (Ant) findAnt();
					ant.setFoodLevel(ant.getFoodLevel() + foodStation.getCapacity());
					foodStation.setCapacity(0);
					foodStation.setColor(ColorUtil.rgb(0, 255, 0));
					gameObjects.add(new FoodStation());
					System.out.println("You have repleneshed your food supplies, " + "you now have a food level of: "
							+ ant.getFoodLevel());
					break;
				}
			}
		}
	}

	/**
	 * This method corresponds with the user input of a character "g". This method
	 * creates a collision between the Ant and Spider object which decreases the
	 * healthLevel of the Ant object.
	 */
	public void collideSpider() {
		Ant ant = (Ant) findAnt();
		// Check Ant object healthLevel for lose condition
		if (ant.getHealthLevel() == 1) {
			System.out.println("You died because your health level dropped to 0");
			lostRound();
		} else {
			ant.setHealthLevel(ant.getHealthLevel() - 1);
			System.out.println("You have been hit by a spider! You lost 1 health, you are now at a health level of: "
					+ ant.getHealthLevel());
			// Ant object color turning lighter in red after every collision
			ant.setColor(ColorUtil.rgb(ColorUtil.red(ant.getColor()) - 15, 0, 0));

			// Check Ant speed limitation after health has dropped and make necessary
			// changes if needed
			double antHealthToDecimal = (double) ant.getHealthLevel() / 10;
			int antMaxSpeedHealth = (int) (ant.getMaximumSpeed() * antHealthToDecimal);
			if (ant.getSpeed() > antMaxSpeedHealth) {
				ant.setSpeed(antMaxSpeedHealth);
				System.out.println("Your speed has changed because your health level" + " dropped, your speed is now: "
						+ ant.getSpeed());
			}
		}
	}

	/**
	 * This method corresponds with the user input of a character "t". This method
	 * increments the gameClockTime by 1 which represents that a unit of time has
	 * passed within the game world. When a unit of time has passed within the game
	 * world the Ant object's foodLevel decreases and all Movable objects will
	 * change their location in the game world dependent on their speed and heading.
	 */
	public void advanceClock() {
		gameClockTime++;
		Ant ant = (Ant) findAnt();
		// Check lose condition - Food Level
		if (ant.getFoodLevel() - ant.getFoodConsumptionRate() <= 0) {
			System.out.println("Your food level has been depleted. You died of hunger!");
			lostRound();
		} else {
			ant.setFoodLevel(ant.getFoodLevel() - ant.getFoodConsumptionRate());

			// Update Movable object's location on map
			for (int i = 0; i < gameObjects.size(); i++) {
				if (gameObjects.get(i) instanceof Movable) {
					((Movable) gameObjects.get(i)).move();
				}
			}

			System.out.println("The game clock has ticked, the time is now at: " + this.getGameClockTime());
			System.out.println("You have consumed food supply of: " + ant.getFoodConsumptionRate()
					+ ". You are now at a food level of: " + ant.getFoodLevel());
		}
	}

	/**
	 * This method corresponds with the user input of a character "d". This method
	 * prints out text to the console displaying relevant game information such as
	 * lives remaining, game clock time, food level, health level, and current flag
	 * number.
	 */
	public void displayStatus() {
		Ant ant = (Ant) findAnt();
		System.out.println("You have " + this.getLivesRemaining() + " lives remaining");
		System.out.println("The current game clock time is: " + this.getGameClockTime());
		System.out.println("The highest flag you have reached currently is: " + ant.getLastFlagReached());
		System.out.println("Your current food level is: " + ant.getFoodLevel());
		System.out.println("Your current health level is: " + ant.getHealthLevel());
	}

	/**
	 * This method corresponds with the user input of a character "m". This method
	 * prints out text to the console displaying map information which contains the
	 * location, color, and more of each GameWorld object.
	 */
	public void displayMap() {
		for (int i = 0; i < gameObjects.size(); i++) {
			System.out.println(gameObjects.get(i));
		}
	}

	/**
	 * This method corresponds with the user input of a character "x". This method
	 * controlls the exiting of the game and also asks the player to confirm their
	 * decision
	 * 
	 * @param answer represents a char that symbolizes whether or not a player has
	 *               confirmed to exit the game
	 * @return whether or not a player has confirmed to exit or not
	 */
	public boolean exit(char answer) {
		if (answer == 'y') {
			System.out.println("Goodbye, you have exited the game!");
			System.exit(0);
		} else if (answer == 'n') {
			System.out.println("Let's keep playing! You have not exited the game");
			return false;
		}
		System.out.println("Do you want to exit? Enter y or n");
		return true;
	}

	/**
	 * This method returns an int value that represents the number of lives
	 * remaining that the player has.
	 * 
	 * @return the amount of lives remaining.
	 */
	public int getLivesRemaining() {
		return this.livesRemaining;
	}

	/**
	 * This method returns an int value that represents the current game clock time.
	 * 
	 * @return the game time.
	 */
	public int getGameClockTime() {
		return gameClockTime;
	}

	/**
	 * This is a helper method that exits the game and displays information when the
	 * player loses or wins the game.
	 * 
	 * @param winFlag is a boolean value that represents if a player has won or
	 *                lost.
	 */
	private void winGame(boolean winFlag) {
		if (winFlag) {
			System.out.println("Game over, you win! Total time: " + this.getGameClockTime());
		} else {
			System.out.println("Game over, you failed! You lost all your lives!");
		}
		System.exit(0);
	}

	/**
	 * This is a helper method that keeps track of the lives remaining that a player
	 * has and also restart the game when a player has lost the round.
	 */
	private void lostRound() {
		if (livesRemaining == 1) {
			winGame(false);
		} else {
			((Flag) gameObjects.get(0)).resetSequenceCounter();
			livesRemaining--;
			System.out
					.println("You have lost the round! You now have: " + this.getLivesRemaining() + " lives remaining");
			init();
		}

	}

	/**
	 * This is a helper method that searches through the gameObject ArrayList and
	 * returns the Ant object.
	 * 
	 * @return Ant object from gameObjects ArrayList.
	 */
	private GameObject findAnt() {
		for (int i = 0; i < gameObjects.size(); i++) {
			if (gameObjects.get(i) instanceof Ant) {
				return gameObjects.get(i);
			}
		}
		return null;
	}
}
