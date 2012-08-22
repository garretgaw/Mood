package org.gg.mood.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.ui.ModelMap;

public class MainPageControllerTest {

	@Test
	public void testPrintWelcome() {
		MainPageController controller = new MainPageController();
		assertEquals("welcome", controller.printWelcome(new ModelMap()));
	}

}
