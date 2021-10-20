package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.plaf.Border;

/**
 * This custom Button class creates buttons with a set defined Style constraints
 * 
 * @author Mickey Huang
 *
 */
public class ButtonWithStyle extends Button {

	/**
	 * Constructor to create Buttons with a set defined style.
	 * 
	 * @param name that represents the text label of the button.
	 */
	public ButtonWithStyle(String name) {
		setText(name);
		getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		getUnselectedStyle().setBgTransparency(235);
		getUnselectedStyle().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		getAllStyles().setPadding(3, 3, 2, 2);
	}

}
