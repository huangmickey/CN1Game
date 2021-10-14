package com.mycompany.a1;

import com.codename1.ui.events.ActionListener;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;

/**
 * The Game class manages the flow of control and displays information about the
 * state of the game. The Game class has the method play() that accepts user
 * input in the form of single letter characters which invoke certain game
 * methods in the game model. The idea of the game is that you are an ant and
 * your goal is to reach the last flag in the game before you deplete your food
 * and health levels. Health levels can be depleted from getting attacked by
 * other GameWorld objects such as the Spider. Your health levels can be
 * replenished by going to GameWorld objects of FoodStation.
 * 
 * @author Mickey Huang
 */
public class Game extends Form {
	/**
	 * The GameWorld object that holds a collection of game objects, state
	 * variables, and methods
	 */
	private GameWorld gw;

	/**
	 * This Game Constructor will instantiate a GameWorld object and calls the
	 * GameWorld method init() to set the initial state of the game, and finally
	 * calls the Game method play().
	 */
	public Game() {
		gw = new GameWorld();
		gw.init();
		play();
	}

	/**
	 * This method is used to accept and execute user commands in the form of single
	 * letter characters.
	 */
	@SuppressWarnings("rawtypes")
	private void play() {
		Label myLabel = new Label("Enter a Command:");
		this.addComponent(myLabel);
		final TextField myTextField = new TextField();
		this.addComponent(myTextField);
		this.show();

		myTextField.addActionListener(new ActionListener() {
			boolean exitFlag = false;

			public void actionPerformed(ActionEvent evt) {

				String sCommand = myTextField.getText().toString();
				myTextField.clear();
				// if statement to handle logic of users pressing "x" to quit game
				if (exitFlag) {
					// if exitFlag is true, then confirm user's next input and verify that it is
					// either "y" or "n"
					exitFlag = gw.exit(sCommand.charAt(0));
				} else if (sCommand.length() == 1) {
					char charCommand = sCommand.charAt(0);
					// if statement to handle characters "1" to "9"
					if (charCommand >= '1' && charCommand <= '9') {
						gw.collideFlag(charCommand);
					} else {
						switch (charCommand) {
						case 'a':
							gw.accelerate();
							break;
						case 'b':
							gw.brake();
							break;
						case 'l':
							gw.changeHeadingLeft();
							break;
						case 'r':
							gw.changeHeadingRight();
							break;
						case 'f':
							gw.collideFoodStation();
							break;
						case 'g':
							gw.collideSpider();
							break;
						case 't':
							gw.advanceClock();
							break;
						case 'd':
							gw.displayStatus();
							break;
						case 'm':
							gw.displayMap();
							break;
						case 'x':
							exitFlag = true;
							gw.exit(charCommand);
							break;
						default:
							System.out.println("Please enter a valid character");
						}
					}
				} else if (sCommand.length() == 0) {
					System.out.println("Please enter in a character");
				} else {
					System.out.println("Please enter only a single character");
				}
			}
		});
	}
}
