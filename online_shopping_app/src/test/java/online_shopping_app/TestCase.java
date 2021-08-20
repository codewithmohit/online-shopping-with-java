package online_shopping_app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.app.Main;

class TestCase {

	@Test
	void checkChoice() {
		assertEquals(true, Main.checkChoice(2) ,"Not Valid Choice");
		assertEquals(true, Main.checkChoice(5) ,"Not Valid Choice");
		assertEquals(true, Main.checkChoice(1) ,"Not Valid Choice");
		assertEquals(true, Main.checkChoice(3) ,"Not Valid Choice");
		assertEquals(true, Main.checkChoice(4) ,"Not Valid Choice");
		assertEquals(false, Main.checkChoice(11) ,"Not Valid Choice");
		assertEquals(false, Main.checkChoice(16) ,"Not Valid Choice");
		assertEquals(false, Main.checkChoice(9) ,"Not Valid Choice");
		assertEquals(false, Main.checkChoice(6) ,"Not Valid Choice");
		assertEquals(false, Main.checkChoice(8) ,"Not Valid Choice");
	}
	
	

}
