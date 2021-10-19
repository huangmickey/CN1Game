package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.Game;

/**
 * Custom command class which invokes a sound change of state
 * 
 * @author Mickey Huang
 *
 */
public class SoundCommand extends Command {
	/**
	 * Game variable storing the target
	 */
	private Game game;

	/**
	 * Constructor creates new instance of Command and sets target
	 * 
	 * @param gw representing the target
	 */
	public SoundCommand(Game game) {
		super("Sound");
		this.game = game;
	}

	/**
	 * This method calls a corresponding method in target when custom command is
	 * called
	 */
	@Override
	public void actionPerformed(ActionEvent ev) {
		game.getGw().sound();
		((Form) game).getToolbar().closeSideMenu();
	}
}