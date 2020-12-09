
public class Projectiles extends GameObject {
	//Projectiles represent all of the different things on the scene aside from the player
	//they can be either tools(net, oil skimmer) or obstacles(trash, oil) or food(1,2,3)
	boolean isPlayer = false; 
	
	/**
	 * constructor for a Projectile object
	 * @param xLoc	x location of the projectile object
	 * @param yLoc	y location of the projectile object
	 * @param xIncr	how much the x value is increasing or decreasing
	 * @param yIncr how much the y value is increasing or decreasing
	 * @param width	the width of the projectile object
	 * @param height the height of the projectile object
	 * @param type what type the projectile is
	 */
	public Projectiles(int xLoc, int yLoc, int xIncr, int yIncr, int width, int height, 
			ObjectType type) {
		super(xLoc, yLoc, xIncr, yIncr, width, height);
		this.radius = (width + height)/8;
		this.isPlayer = false;
		this.type = type;
		// TODO Auto-generated constructor stub
	}
	

}
