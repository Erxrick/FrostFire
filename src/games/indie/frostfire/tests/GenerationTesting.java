package games.indie.frostfire.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import games.indie.frostfire.world.ProceduralGeneration;

public class GenerationTesting {

	@Test
	public void testGenerateWorld() {
		int value = ProceduralGeneration.seedValidation("a");
		
		assertEquals(value, 97);
	}
	

	@Test
	public void testGenerateWorld2() {
		int expected = 632;
		
		int actual = ProceduralGeneration.seedValidation("Purple");
		
		assertEquals(expected, actual);
	}


}
