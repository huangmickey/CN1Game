package com.mycompany.a2;

import com.mycompany.a2.commands.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;

/**
 * The Game class is the controller of the MVC architecture where the flow of
 * control and creation of models and views. Game class is also responsible for
 * the creation and registration of corresponding Components and Commands. Also
 * serves as the top level component, Form. The idea of the game is that you are
 * an ant and your goal is to reach the last flag in the game before you deplete
 * your food and health levels. Health levels can be depleted from getting
 * attacked by other GameWorld objects such as the Spider. Your health levels
 * can be replenished by colliding x and y coordinates of your character to
 * GameWorld objects of type FoodStation.
 * 
 * @author Mickey Huang
 */
public class Game extends Form {

	/**
	 * GameWorld variable which holds the "Model" of the MVC architecture.
	 */
	private GameWorld gw;
	/**
	 * ScoreView variable which holds a "View"
	 */
	private ScoreView sv;
	/**
	 * MapView variable which holds a "View"
	 */
	private MapView mv;

	/**
	 * Constructor which instantiates and initializes the models, views, commands,
	 * and components of the game.
	 */
	public Game() {

		//Instantiate and initialize all needed models and views
		gw = new GameWorld();
		mv = new MapView();
		gw.addObserver(mv);
		sv = new ScoreView(gw);
		this.setLayout(new BorderLayout());
		this.setTitle("OnTarget Game");

		//Create custom Command objects with target as gw. Sound command target is this (Game)
		AccelerateCommand accelerateCommand = new AccelerateCommand(gw);
		BrakeCommand brakeCommand = new BrakeCommand(gw);
		LeftTurnCommand leftTurnCommand = new LeftTurnCommand(gw);
		RightTurnCommand rightTurnCommand = new RightTurnCommand(gw);
		CollideFlagCommand collideFlagCommand = new CollideFlagCommand(gw);
		CollideFoodStationCommand collideFoodStationCommand = new CollideFoodStationCommand(gw);
		CollideSpiderCommand collideSpiderCommand = new CollideSpiderCommand(gw);
		TickCommand tickCommand = new TickCommand(gw);
		ExitCommand exitCommand = new ExitCommand(gw);
		SoundCommand soundCommand = new SoundCommand(this);
		AboutCommand aboutCommand = new AboutCommand(gw);
		HelpCommand helpCommand = new HelpCommand(gw);


		//Create and add Toolbar. Add all components to toolbar and add commands for sidemenu components
		Toolbar toolbar = new Toolbar();
		this.setToolbar(toolbar);
		CheckBox soundCheckBox = new CheckBox("Sound");
		soundCheckBox.setCommand(soundCommand);
		soundCheckBox.getAllStyles().setBgTransparency(255);
		soundCheckBox.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		toolbar.addComponentToSideMenu(soundCheckBox);
		toolbar.addCommandToSideMenu(accelerateCommand);
		toolbar.addCommandToSideMenu(aboutCommand);
		toolbar.addCommandToSideMenu(exitCommand);
		toolbar.addCommandToRightBar(helpCommand);
		//Add key listeners "key binds" for this form
		addKeyListener('a', accelerateCommand);
		addKeyListener('b', brakeCommand);
		addKeyListener('l', leftTurnCommand);
		addKeyListener('r', rightTurnCommand);
		addKeyListener('f', collideFoodStationCommand);
		addKeyListener('g', collideSpiderCommand);
		addKeyListener('t', tickCommand);
		addKeyListener('x', exitCommand);

		//Create control containers for each region of BorderLayout of this (Game) => Form container
		Container eastContainer = new Container();
		Container southContainer = new Container(new FlowLayout(Component.CENTER));
		Container westContainer = new Container();
		Container centerContainer = new Container();
		Container northContainer = new Container(new FlowLayout(Component.CENTER));
		//Create custom Buttons with defined styles in ButtonWithStyle class
		ButtonWithStyle brakeButton = new ButtonWithStyle("Brake");
		ButtonWithStyle rightTurnButton = new ButtonWithStyle("Right");
		ButtonWithStyle collideWithFlagButton = new ButtonWithStyle("Collide with Flag");
		ButtonWithStyle collideWithSpiderButton = new ButtonWithStyle("Collide with Spider");
		ButtonWithStyle collideWithFoodStationButton = new ButtonWithStyle("Collide with Food Stations");
		ButtonWithStyle tickButton = new ButtonWithStyle("Tick");
		ButtonWithStyle accelerateButton = new ButtonWithStyle("Accelerate");
		ButtonWithStyle leftTurnButton = new ButtonWithStyle("Left");
		//Add buttons to BorderLayout region control containers
		eastContainer.add(brakeButton);
		eastContainer.add(rightTurnButton);
		southContainer.add(collideWithFlagButton);
		southContainer.add(collideWithSpiderButton);
		southContainer.add(collideWithFoodStationButton);
		southContainer.add(tickButton);
		westContainer.add(accelerateButton);
		westContainer.add(leftTurnButton);
		centerContainer.add(mv);
		northContainer.add(sv);
		//Set commands for buttons 
		brakeButton.setCommand(brakeCommand);
		rightTurnButton.setCommand(rightTurnCommand);
		collideWithFlagButton.setCommand(collideFlagCommand);
		collideWithFoodStationButton.setCommand(collideFoodStationCommand);
		collideWithSpiderButton.setCommand(collideSpiderCommand);
		tickButton.setCommand(tickCommand);
		accelerateButton.setCommand(accelerateCommand);
		leftTurnButton.setCommand(leftTurnCommand);
	

		//Styling for BorderLayout control containers
		eastContainer.getUnselectedStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
		eastContainer.getAllStyles().setPadding(80, 0, 0, 0);
		southContainer.getUnselectedStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
		westContainer.getUnselectedStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
		westContainer.getAllStyles().setPadding(80, 0, 0, 0);
		eastContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		westContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		centerContainer.getAllStyles().setBorder(Border.createLineBorder(3, ColorUtil.rgb(255, 0, 0)));

		//Add containers to this BorderLayout layout's regions
		this.add(BorderLayout.NORTH, northContainer);
		this.add(BorderLayout.CENTER, centerContainer);
		this.add(BorderLayout.SOUTH, southContainer);
		this.add(BorderLayout.EAST, eastContainer);
		this.add(BorderLayout.WEST, westContainer);

		//Show this, Form, and print out width/height of center container
		this.show();
		System.out.println("Center container width/height (printed AFTER show()): " + centerContainer.getWidth() + " "
				+ centerContainer.getHeight());
		gw.init();
	}

	/**
	 * This method returns the "model"
	 * 
	 * @return GameWorld type
	 */
	public GameWorld getGw() {
		return gw;
	}
}
