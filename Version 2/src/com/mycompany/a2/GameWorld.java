package com.mycompany.a2;

import java.util.Observable;
import com.mycompany.a2.gameobjects.*;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;


/**
 * This class is the model of the Game. The GameWorld class holds an object
 * which contains a collection of game objects. GameWorld also contains state
 * variables and game methods. This class is also responsible for the creation
 * of the initial state of the game world. This class's methods will interact
 * with game objects and states such as updating all of the object's location on
 * the game, health, and etc. GameWorld class also handles the game state values
 * such as the number of lives the player has and the game clock time.
 * 
 * @author Mickey Huang
 */
public class GameWorld extends Observable {
	/**
	 * A GameObjectCollection object which represents the collection structure of
	 * GameObject type
	 */
	private GameObjectCollection gameObjectCollection;

	/**
	 * A boolean variable that keeps track of sound state (default to false ("off"))
	 */
	private boolean sound = false;

	/**
	 * An int variable that keeps track of the game time.
	 */
	private int gameClockTime;
	/**
	 * An int variable that keeps track of the lives remaining of the Ant object
	 * (default 3 lives).
	 */
	private int livesRemaining = 3;

	/**
	 * This method initializes the GameWorld with the default game objects and state
	 * variables. Also notify observers to set a default view of the default values
	 * of the game world.
	 */
	public void init() {

		gameObjectCollection = new GameObjectCollection();
		gameObjectCollection.add(new Flag(10, new Point(100, 100)));
		gameObjectCollection.add(new Flag(10, new Point(400, 400)));
		gameObjectCollection.add(new Flag(10, new Point(700, 700)));
		gameObjectCollection.add(new Flag(10, new Point(900, 900)));
		gameObjectCollection.add(Ant.getAnt());
		gameObjectCollection.add(new Spider());
		gameObjectCollection.add(new Spider());
		gameObjectCollection.add(new FoodStation());
		gameObjectCollection.add(new FoodStation());
		// To notify observers (ScoreView and MapView with default values right after
		// init() call)
		setChanged();
		notifyObservers();
	}

	/**
	 * This method corresponds with the user key press of a character "a" and
	 * button(s). This method increments the speed value of the Ant object (player)
	 * by 1.
	 */
	public void accelerate() {
		Ant ant = Ant.getAnt();
		// validate if speed is greater than max speed before incrementing
		double antHealthToDecimal = (double) ant.getHealthLevel() / 10;
		int antMaxSpeedHealth = (int) (ant.getMaximumSpeed() * antHealthToDecimal);
		if (ant.getSpeed() >= antMaxSpeedHealth) {
			System.out.println("You cannot accelerate because you are at the maximum speed of: " + ant.getSpeed());
		} else {
			ant.setSpeed(ant.getSpeed() + 1);
			System.out.println("You have accelerated, your speed is now: " + ant.getSpeed());
			setChanged();
			notifyObservers();
		}

	}

	/**
	 * This method corresponds with the user key press of a character "b" and
	 * button(s). This method decreases the speed value of the Ant object (player)
	 * by 1.
	 */
	public void brake() {
		Ant ant = Ant.getAnt();
		// validate speed is greater than 0 before decrementing
		if (ant.getSpeed() > 0) {
			ant.setSpeed(ant.getSpeed() - 1);
			System.out.println("You have applied brakes. Your speed has decreased to: " + ant.getSpeed());
		} else {
			System.out.println("You cannot brake because your speed is at: " + ant.getSpeed());
		}
	}

	/**
	 * This method corresponds with the user key press of a character "l" and
	 * button(s). This method changes the heading (angle/direction) of the Ant
	 * object by 5 degrees to the left.
	 */
	public void changeHeadingLeft() {
		Ant.getAnt().changeHeading(-5);
		System.out.println("Heading has changed by 5 degrees to the left, your heading is now: ");

	}

	/**
	 * This method corresponds with the user key press of a character "r" and
	 * button(s). This method changes the heading (angle/direction) of the Ant
	 * object by 5 degrees to the right.
	 */
	public void changeHeadingRight() {
		Ant.getAnt().changeHeading(5);
		System.out.println("Heading has changed by 5 degrees to the right");

	}

	/**
	 * This method corresponds with a corresponding button(s). This method allows a
	 * player to select a flag number to collide with.
	 */
	public void collideFlag() {
		//Create Commands for Dialog box
		Command confirm = new Command("Confirm");
		Command cancel = new Command("Cancel");
		Command tryAgain = new Command("Try Again");
		Command[] cmds = new Command[] { confirm, cancel };
		TextField flagInput = new TextField();
		Command c = Dialog.show("Enter a flag #", flagInput, cmds);
		//Check if player selected "confirm"
		if (c == confirm) {
			Ant ant = Ant.getAnt();
			while (true) {
				//Check if input is exactly 1 character
				if (flagInput.getText().length() == 1) {
					char ch = flagInput.getText().charAt(0);
					//Check if the exactly 1 character is equal to range '0' - '9'
					if (ch >= '0' && ch <= '9') {
						int flagNum = Integer.parseInt(flagInput.getText());
						if (ant.getLastFlagReached() + 1 == flagNum) {
							//Check if character is equal to '4' => Win Condition
							if (flagNum == 4) {
								winGame(true);
							}
							Dialog.show("Success", "You have reached the next checkpoint!", confirm);
							ant.setLastFlagReached(flagNum);
							setChanged();
							notifyObservers();
							break; //break out of while loop after player goes through branch of "Confirm" on dialog box
						}
					}
				}
				Command cNext = Dialog.show("Fail",
						"This input is not the next flag number in sequence. Would you like to retry?",
						new Command[] { tryAgain, cancel });
				//if player decides to "try again" on dialog box => recall same method to loop player
				if (cNext == tryAgain) {
					collideFlag();
				}
				break; //break out of while loop if player decides not to "try again" on dialog box
			}//while loop
		}//if statement
	}//end of method

	/**
	 * This method corresponds with the user key press of a character "f" and
	 * button(s). This method allows a Player to collide with a food station which
	 * replenishes the player's food level and randomly generates a new FoodStation
	 * in the game.
	 */
	public void collideFoodStation() {

		IIterator iterator = gameObjectCollection.getIterator();
		//Loop through collection via custom iterator and find food stations
		while (iterator.hasNext()) {
			GameObject gameObj = iterator.getNext();
			if (gameObj instanceof FoodStation) {
				FoodStation foodStation = (FoodStation) gameObj;
				if (foodStation.getCapacity() > 0) {
					Ant ant = Ant.getAnt();
					ant.setFoodLevel(ant.getFoodLevel() + foodStation.getCapacity());
					foodStation.setCapacity(0);
					foodStation.setColor(ColorUtil.rgb(0, 255, 0));
					gameObjectCollection.add(new FoodStation());
					System.out.println("You have repleneshed your food supplies, " + "you now have a food level of: "
							+ ant.getFoodLevel());
					setChanged();
					notifyObservers();
					break;
				}
			}
		}
	}

	/**
	 * This method corresponds with the user key press of a character "g" and
	 * button(s). This method creates a collision between the Ant and Spider object
	 * which decreases the healthLevel of the Ant object.
	 */
	public void collideSpider() {
		Ant ant = Ant.getAnt();
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
		setChanged();
		notifyObservers();
	}

	/**
	 * This method corresponds with the user key press of a character "t" and
	 * button(s). This method simulates one time unit advance in the Game World. The
	 * timer increases by 1, the player's food level decreases by the corresponding
	 * food consumption rate.
	 */
	public void advanceClock() {
		gameClockTime++;
		Ant ant = Ant.getAnt();
		// Check lose condition - Food Level
		if (ant.getFoodLevel() - ant.getFoodConsumptionRate() <= 0) {
			System.out.println("Your food level has been depleted. You died of hunger!");
			lostRound();
		} else {
			ant.setFoodLevel(ant.getFoodLevel() - ant.getFoodConsumptionRate());

			// Update Movable object's location on map
			IIterator iterator = gameObjectCollection.getIterator();
			while (iterator.hasNext()) {
				GameObject gameObj = iterator.getNext();
				if (gameObj instanceof Movable) {
					((Movable) gameObj).move();
				}
			}

			System.out.println("The game clock has ticked, the time is now at: " + gameClockTime);
			System.out.println("You have consumed food supply of: " + ant.getFoodConsumptionRate()
					+ ". You are now at a food level of: " + ant.getFoodLevel());
		}
		setChanged();
		notifyObservers();
	}

	/**
	 * This method corresponds with the user press of corresponding button(s). This
	 * method displays a Dialog box which gives information on the available key
	 * binds.
	 */
	public void displayHelp() {
		TextArea tf = new TextArea();
		tf.setText("Gameplay Keybinds\n\n" + "a - Accelerate\n" + "b - Brake\n" + "l - Turn left\n" + "r - Turn Right\n"
				+ "f - Collide Food Station\n" + "g - Collide Spider\n" + "t - Tick");
		tf.getAllStyles().setAlignment(0);
		tf.setEditable(false);
		Dialog.show("Help", tf, new Command("OK"));

	}

	/**
	 * This method corresponds with the user press of corresponding button(s). This
	 * method displays a Dialog box which gives general information about the
	 * program.
	 */
	public void displayAbout() {
		Dialog.show("About", "Mickey Huang, CSC133, Version 1.0", new Command("Ok"));
	}

	/**
	 * This method prints out text to the console displaying map information which
	 * contains the location, color, and more of each GameWorld object.
	 */
	public void displayMap() {
		IIterator iterator = gameObjectCollection.getIterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.getNext());
		}
	}

	/**
	 * This method corresponds with the user key press of a character "x" and
	 * button(s). This method displays a Dialog box confirming whether or not the
	 * player wants to exit the game.
	 */
	public void exit() {
		Command yes = new Command("Yes");
		Command no = new Command("No");
		if (Dialog.show("Goodbye!", "Do you really want to exit?", yes, no) == yes) {
			Display.getInstance().exitApplication();
		}
	}

	/**
	 * This method corresponds with the user press of corresponding button(s). This
	 * method changes the state of the sound variable.
	 */
	public void sound() {
		sound = !sound;
		setChanged();
		notifyObservers();
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
	 * This method returns a String value which corresponds with the state of the
	 * sound variable
	 * 
	 * @return String value that represents ON/OFF states of sound variable
	 */
	public String getSound() {
		if (sound)
			return "ON";
		return "OFF";
	}

	/**
	 * This method returns an int value that represents the current game clock time.
	 * 
	 * @return the game time.
	 */
	public int getTime() {
		return this.gameClockTime;
	}

	/**
	 * This is a helper method that exits the game and displays information when the
	 * player loses or wins the game.
	 * 
	 * @param winFlag is a boolean value that represents if a player has won or
	 *                lost.
	 * 
	 */
	private void winGame(boolean winFlag) {
		if (winFlag) {
			Dialog.show("Game over", "You have won the game! Total time: " + gameClockTime, new Command("Ok"));
			System.out.println("Game over, you win! Total time: " + gameClockTime);
		} else {
			Dialog.show("Game over", "You have lost the game!", new Command("Ok"));
			System.out.println("Game over, you failed! You lost all your lives!");
		}
		Display.getInstance().exitApplication();
	}

	/**
	 * This is a helper method that keeps track of the lives remaining that a player
	 * has and also restart the game when a player has lost the round.
	 */
	private void lostRound() {
		if (livesRemaining == 1) {
			winGame(false);
		} else {
			Flag.resetSequenceCounter();
			livesRemaining--;
			Dialog.show("Round Lost", "You have lost the round! You now have " + livesRemaining + " lives remaining",
					new Command("Ok"));
			System.out.println("You have lost the round! You now have: " + livesRemaining + " lives remaining");
			Ant.resetAnt();
			init();
		}

	}
}
