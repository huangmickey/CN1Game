package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.a2.gameobjects.Ant;

/**
 * ScoreView serves as a "View" which displays game state information about the
 * player.
 * 
 * @author Mickey Huang
 *
 */
public class ScoreView extends Container implements Observer {

	/**
	 * Label variable which holds state information to be displayed as text
	 */
	private Label scoreViewLabel;

	/**
	 * Constructor to initialize the layout of the ScoreView component and also to
	 * initialize & instantiate the scoreViewLabel.
	 * 
	 * @param gameModel represents the target
	 */
	public ScoreView(Observable gameModel) {
		gameModel.addObserver(this); // self registration with Observable object gameModel
		this.setLayout(new FlowLayout());
		scoreViewLabel = new Label();
		scoreViewLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		add(scoreViewLabel);

	}

	/**
	 * Changes made to the model will be reflected here into scoreViewLabel.
	 */
	@Override
	public void update(Observable observable, Object data) {

		GameWorld gameWorld = (GameWorld) observable;
		scoreViewLabel.setText("Time:   " + gameWorld.getTime() + "  Lives Left:   " + gameWorld.getLivesRemaining()
				+ "  Last Flag Reached:   " + Ant.getAnt().getLastFlagReached() + "  Food Level:   "
				+ Ant.getAnt().getFoodLevel() + "  Health Level:   " + Ant.getAnt().getHealthLevel() + "  Sound:   "
				+ gameWorld.getSound());
		revalidate();
	}

}
