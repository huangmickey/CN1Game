package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

/**
 * Custom command class representing an event of exiting the game
 * 
 * @author Mickey Huang
 *
 */
public class ExitCommand extends Command {
	/**
	 * GameWorld variable storing the target
	 */
	private GameWorld gw;

	/**
	 * Constructor creates new instance of Command and sets target
	 * 
	 * @param gw representing the target
	 */
	public ExitCommand(GameWorld gw) {
		super("Exit");
		this.gw = gw;
	}

	/**
	 * This method calls a corresponding method in target when custom command is
	 * called
	 */
	@Override
	public void actionPerformed(ActionEvent ev) {
		gw.exit();
	}
}