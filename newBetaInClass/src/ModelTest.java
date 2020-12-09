import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class ModelTest {
	
	@Test
	void removeOnScreenTest() {
		Model modelTest = new Model(1,2,3,4,0);
		Projectiles test = new Projectiles(1,2,3,4,5,6,ObjectType.FOOD1);
		modelTest.OnScreen.add(test);
		modelTest.removeOnScreen(test);
		assertEquals(modelTest.OnScreen.size(),1, "Array should include no objects");
	}
	
	@Test
	void instantiateOnScreenTest() {
		Model modelTest = new Model(1,2,3,4,0);
		assertEquals(modelTest.OnScreen.size(), 0, "Equals");
	}
	
	@Test
	void updateLocationandDirectionTest() {
		Model modelTest = new Model(1,2,3,4,0);
		modelTest.xloc = 1000;
		modelTest.updateLocationandDirection();
		assertEquals(modelTest.xloc, 1020, "Equals");
		}
	
	@Test
	void updateBassModeAndDirectionTest() {
		Model modelTest = new Model(1,2,3,4,0);
		modelTest.updateBassModeAndDirection(BassMode.ATTAC, Direction.EAST);
		assertEquals(modelTest.bm, BassMode.ATTAC, "Equals");
	}
	
	@Test
	void getDirectionTest() {
		Model modelTest = new Model(1,2,3,4,0);
		modelTest.direction = Direction.EAST;
		assertEquals(modelTest.getDirection(), Direction.EAST, "Equals");
	}
	
	@Test
	void getXTest() {
		Model modelTest = new Model(1,2,3,4,0);
		modelTest.xloc = 1050;
		assertEquals(modelTest.getX(), 1050, "Equals");
	}
	
	@Test
	void getYTest() {
		Model modelTest = new Model(1,2,3,4,0);
		modelTest.yloc = 500;
		assertEquals(modelTest.getY(), 500, "Equals");
	}
	
	@Test
	void moveObjectsTest() {
		Model modelTest = new Model(1,2,3,4,0);
		Projectiles test = new Projectiles(1,2,3,4,5,6,ObjectType.TRASH);
		modelTest.OnScreen.add(test);
		modelTest.moveObjects(test);
		assertEquals(test.xLoc, 4, "Equals");
		
	}
	
	@Test
	void testCollideTest() {
		Model modelTest = new Model(1,2,3,4,0);
		modelTest.xloc = 1000;
		modelTest.yloc = 450;
		modelTest.testCollide(modelTest.imgRadius);
		assertEquals(modelTest.OnScreen.size(), 0, "Equals");
	}
	
	@Test
	 void updateToolBar() {
	  Model modelTest = new Model(1,2,3,4,2);
	  modelTest.ToolBar.add(modelTest.blank);
	  modelTest.updateToolBar();
	  System.out.println(modelTest.ToolBar.size());
	  assertEquals(modelTest.ToolBar.size(), 1, "Equals");
	 
	}

}