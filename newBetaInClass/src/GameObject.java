import java.awt.Image;
import java.util.ArrayList;

public class GameObject {
	int xLoc;
	int yLoc;
	int xIncr;
	int yIncr;
	
	//radius to be used for collisions only
	int radius;
	ObjectType type;
	
	//to be set in child classes
	boolean isPlayer;
	
	//height and width variables to be used for drawing images
	int width;
	int height;
	
	//Constructor for GameObject
	/**
	 * Constructor for GameObject
	 * @param xLoc	the current x location of the gameobject
	 * @param yLoc	the current y location of the gameobject
	 * @param xIncr	how much the x value is increasing or decreasing
	 * @param yIncr	how much the y value is increasing or decreasing
	 * @param width	the width of the gameobject
	 * @param height	the height of the gameobject
	 */
	public GameObject(int xLoc, int yLoc, int xIncr, int yIncr, int width, int height){
		this.xLoc = xLoc;
		this.yLoc = yLoc;
		this.xIncr = xIncr;
		this.yIncr = yIncr;
		this.width = width;
		this.height = height;
		//radius is equal to the average of the width and the height
		this.radius = (width + height)/2;
	}
	
	
	//Getters and Setters for GameObject locations
	public int getXLoc() {
		return this.xLoc;
	}
	public void setXLoc(int xLoc) {
		this.xLoc = xLoc;
	}
	public int getYLoc() {
		return this.yLoc;
	}
	public void setYLoc(int yLoc) {
		this.yLoc = yLoc;
	}
	
	//Getters and Setters for GameObject increments
	public int getXIncr() {
		return this.xIncr;
	}
	public void setXIncr(int xIncr) {
		this.xIncr = xIncr;
	}
	public int getYIncr() {
		return this.yIncr;
	}
	public void setYIncr(int yIncr) {
		this.yIncr = yIncr;
	}
	
	//isPlayer method that will test if the GameObject is the player
	/**
	 * method that tests if a gameobject is a player
	 * @return	a boolean of whether or not the gameobject being tested is a player
	 */
	public boolean isPlayer() {
		return this.isPlayer;
	}
	
	
	/**
	 * move method to be implemented by all children classes - this method checks for collisions
	 * between the object and all objects in the OnScreen array - collision handling will be
	 * implemented in the child classes as different objects will be handled differently
	 * possibly have to override this method in order to include different collision handlers
	 */
	public void Move() {
		xLoc += xIncr;
		yLoc += yIncr;
		
	}
	
	
}
	