import java.util.ArrayList;
import java.util.Collection;


public class Model {
	//ArrayList of onscreen game objects meant to store the objects currently involved with the game
	//HAD TO MAKE THIS STATIC SO I CAN ACESS IT FROM MODEL
	public static ArrayList <GameObject> OnScreen = new ArrayList <GameObject>();
	public static ArrayList <GameObject> ToolBar = new ArrayList <GameObject>();

	//Default Player Variables
	static int xloc;
    static int yloc;
    static double xIncr = 0;//20
    static double yIncr = 0;//14
    Direction direction = Direction.EAST;
    //Default Canvas
    static int canvasWidth;
    static int canvasHeight;
    static int imgWidth;
    static int imgHeight;
    static int imgRadius;
    //Toolbar Booleans
    static boolean hasNet = false;
    static boolean hasSkimmer = false;
    static boolean hasScissors = false;
    static boolean netSelected = false;
    static boolean skimmerSelected = false;
    static boolean scissorsSelected = false;
    static boolean forkSelected = false;
    //Toolbar Array
    static int toolBarheight = 100;
	static int toolBarwidth = 200;
	static int toolBarX = 0;
	static int toolBary = 600   ;
	static GameObject tutorialBar = new Projectiles(toolBarX, toolBary, 0, 0, toolBarheight, toolBarwidth, ObjectType.TUTORIALBAR);
	static GameObject tutorialBarSelected = new Projectiles(toolBarX, toolBary, 0, 0, toolBarheight, toolBarwidth, ObjectType.TUTORIALSELECTED);
	static GameObject blank = new Projectiles(toolBarX, toolBary, 0, 0, toolBarheight, toolBarwidth, ObjectType.EMPTY);
	static GameObject netOnly = new Projectiles(toolBarX, toolBary, 0, 0, toolBarheight, toolBarwidth, ObjectType.NETONLY);
	static GameObject netSelected1 = new Projectiles(toolBarX, toolBary, 0, 0, toolBarheight, toolBarwidth, ObjectType.NETSELECTED1);
	static GameObject netSelected2 = new Projectiles(toolBarX, toolBary, 0, 0, toolBarheight, toolBarwidth, ObjectType.NETSELECTED2);
	static GameObject skimmerSelected2 = new Projectiles(toolBarX, toolBary, 0, 0, toolBarheight, toolBarwidth, ObjectType.SKIMMERSELECTED2);
	static GameObject netSelected3 = new Projectiles(toolBarX, toolBary, 0, 0, toolBarheight, toolBarwidth, ObjectType.NETSELECTED3);
	static GameObject skimmerSelected3 = new Projectiles(toolBarX, toolBary, 0, 0, toolBarheight, toolBarwidth, ObjectType.SKIMMERSELECTED3);
	static GameObject scissorsSelected3 = new Projectiles(toolBarX, toolBary, 0, 0, toolBarheight, toolBarwidth, ObjectType.SCISSORSSELECTED3);
    //Trash Variables
	static int trashXSpeed = 3;
	static int trashYSpeed = 2;
	static int trashWidth = 50;
	static int trashHeight = 50;
	static ObjectType trash = ObjectType.TRASH;
	static ObjectType trash2 = ObjectType.PURPLECAN;
	static ObjectType trash3 = ObjectType.ORANGECAN;
	static ObjectType trash4 = ObjectType.SHOE;
	static ObjectType food1 = ObjectType.FOOD1;
	static ObjectType food2 = ObjectType.FOOD2;
	//Oil Variables
	static int oilXSpeed = 5;
	static int oilYSpeed = 3;
	static int oilWidth = 100;
	static int oilHeight = 100;
	static ObjectType oil = ObjectType.OIL;
	//Crab Trapped and Free
	ObjectType player = ObjectType.PLAYER;
	static ObjectType hostage = ObjectType.HOSTAGE;
	//Character Choice
    BassMode bm;
    //Score
    static int score = 0;
	
    /**
     * Constructor for our model class. Sets the width, height, imageWidth, and ImageHeight for 
     * the model. You can change the levelCode parameter to start the game from any level you choose
     * @param width			an int used to set the canvas width
     * @param height		an int used to set the canvas height
     * @param imageWidth	an int used to set the width of images in the model
     * @param imageHeight	an int used to set the height of images in the model
     * @param levelCode		an int that corresponds to the level of the game
     */
	public Model(int width, int height, int imageWidth, int imageHeight, int levelCode) {
		canvasWidth = width;
		canvasHeight = height;
		imgWidth = imageWidth;
		imgHeight = imageHeight;
		instantiateOnScreen(levelCode);
		this.imgRadius = (imgHeight + imgWidth)/2;
	}
	//function to remove object from OnScreen Array
	/**
	 * used to remove an object from the screen (trash, oil)
	 * 
	 * @param e	the gameobject that is to be removed from the screen
	 */
	public void removeOnScreen(GameObject e) {
		OnScreen.remove(e);
		if(e.type == ObjectType.FOOD1) {
			OnScreen.clear();
		}
		if(e.type == ObjectType.FOOD2) {
			OnScreen.clear();
		}
		if(OnScreen.isEmpty()) {
			Controller.lvl++;
			instantiateOnScreen(Controller.lvl);
		}
	}

	/**
	 * updates the location and direction of the user and sets the boundaries for which
	 * they cannot move while in each level
	 */
	public void updateLocationandDirection() {
		
		if(Controller.lvl>=0) {
			if(bm == BassMode.ATTAC) {
				xIncr = 10;
				yIncr =10;
			}
			else if(bm == BassMode.CONFUSE) {
				xIncr = 10;
				yIncr = 10;
				
			}
			else if(bm == BassMode.DEFAULT) {
				xIncr = 10;
				yIncr = 10;
			}
		}
		
		//get direction xIncr, yIncr correct
				switch(direction) {
				case NORTH:
					xIncr = 0;
					yIncr = -(yIncr);
					break;
				case SOUTH:
					xIncr = 0;
					break;
				case EAST:
					yIncr = 0;
					break;
				case WEST:
					yIncr = 0;
					xIncr = -(xIncr);
				case NORTHEAST:
					yIncr = -(yIncr);
					break;
				case NORTHWEST:
					yIncr = -(yIncr);
					xIncr = -(xIncr);
					break;
				case SOUTHWEST:
					xIncr = -(xIncr);
					break;
				}
				//Level Boundaries
				//Level Select 1
				if(Controller.lvl==1) {
					if(xloc >= 1175) {
			    		xloc=1165;
			    	}
			    	if(xloc <= 785) {
			    		xloc=795;
			    	}
			    	if(yloc >= 830) {
			    		yloc=820;
			    	} 
			    	if(yloc <= 375) {
			    		yloc=385;
			    	}
				}
				//Level Select 2
				if(Controller.lvl==3) {
					if(xloc >= 1150) {
			    		xloc=1140;
			    	}
			    	if(xloc <= 250) {
			    		xloc=240;
			    	}
			    	if(yloc <= 350) {
			    		yloc=340;
			    	} 
			    	if(yloc >= 525) {
			    		yloc=535;
			    	}
				}
				//Level Select 3
				if(Controller.lvl==5) {
					if(xloc >= 540) {
			    		xloc=530;
			    	}
			    	if(xloc <= 190) {
			    		xloc=200;
			    	}
			    	if(yloc >= 560) {
			    		yloc=550;
			    	} 
			    	if(yloc <= -15) {
			    		yloc=-5;
			    	}
				}
				//Actual Levels
				if(Controller.lvl==2||Controller.lvl==4||Controller.lvl==6||Controller.lvl==0) {
				if(xloc >= 1050) {
		    		xloc=1025;
		    	}
		    	if(xloc <= 325) {
		    		xloc=350;
		    	}
		    	if(yloc >= canvasHeight-205) {
		    		yloc=canvasHeight-215;
		    	} 
		    	if(yloc <= -25) {
		    		yloc=0;
		    	}
				}
				xloc += xIncr;
				yloc += yIncr;
				
				getDirection();
	}
	
	
	/**
	 * used to update the bass mode and direction of 
	 * @param b	the bassmode to be updated
	 * @param d	the direction to be updated
	 */
	public void updateBassModeAndDirection(BassMode b, Direction d) {
		bm = b;
		direction = d;
	}
	
	public Direction getDirection() {
		
		return direction;
	}

	
	public int getX() {
		return xloc;
	}
	
	public int getY() {
		return yloc;
	}
	
	/**
	 * function used to move the on screen objects by changing their x and y locations
	 * in the update() function
	 * @param o	the gameobject that is being updated and moved
	 */
	public static void moveObjects(GameObject o) {
		if(o.type==ObjectType.TRASH||o.type==ObjectType.SHOE||o.type==ObjectType.PURPLECAN||o.type==ObjectType.ORANGECAN) {
			o.xLoc+=o.xIncr;
			if(o.xLoc>=1050) {
				o.xIncr=-(o.xIncr);
			}
			if(o.xLoc<=325) {
				o.xIncr=-(o.xIncr);
			}
		}
	}
	
	/**
	 * function that uses the OnScreen array to actually put objects on the screen for each level. 
	 * Each level has different objects
	 * @param levelCode	an int that corresponds to the current level. objects added vary depending
	 * 			on the level
	 */
	public static void instantiateOnScreen(int levelCode) {
		if(Controller.lvl==-1) {
			xloc=900;
			yloc=550;
			xIncr=0;
			yIncr=0;
		}
		if(Controller.lvl==0) {
			xloc=1000;
			yloc=800;
		}
		if (Controller.lvl == 1) {
			xloc=1000;
			yloc=800;
		}
		if (Controller.lvl == 2) {
			xloc=1000;
			yloc=800;

		}
		if (Controller.lvl == 3) {
			xloc=1000;
			yloc=800;

		}
		if (Controller.lvl == 4) {
			xloc=1000;
			yloc=800;

		}
		if (Controller.lvl == 5) {
			xloc=1000;
			yloc=800;

		}
		if (Controller.lvl == 6) {
			xloc=1000;
			yloc=800;

		}
		if (Controller.lvl == 7) {
			xloc=1000;
			yloc=800;

		}
		
		/*if (Controller.lvl == 2) {
			OnScreen.add(new Projectiles(800, 200, trashXSpeed, trashYSpeed, trashWidth, trashHeight, food1));
			OnScreen.add(new Projectiles(900, 300, -trashXSpeed, trashYSpeed, trashWidth, trashHeight, trash));
			OnScreen.add(new Projectiles(800, 550, trashXSpeed, trashYSpeed, trashWidth, trashHeight, trash2));
			OnScreen.add(new Projectiles(500, 500, -trashXSpeed, trashYSpeed, trashWidth, trashHeight, trash2));
			OnScreen.add(new Projectiles(600, 350, trashXSpeed, trashYSpeed, trashWidth, trashHeight, trash3));
			OnScreen.add(new Projectiles(350, 250, -trashXSpeed, trashYSpeed, trashWidth, trashHeight, trash3));
			OnScreen.add(new Projectiles(650, 650, 0, 0, oilHeight, oilWidth, ObjectType.NET));
			xloc=700;
			yloc=0;
		}
		if (Controller.lvl == 3) {
			xloc=1000;
			yloc=450;
			OnScreen.add(new Projectiles(375, 500, 0, 0, oilWidth, oilHeight,ObjectType.FOOD2));
		}
		if (Controller.lvl == 4) {
			xloc=700;
			yloc=0;
			hasNet=true;
			OnScreen.add(new Projectiles(700, 400, -trashXSpeed, trashYSpeed, trashWidth, trashHeight, trash));
			OnScreen.add(new Projectiles(1000, 200, trashXSpeed, trashYSpeed, trashWidth, trashHeight, trash2));
			OnScreen.add(new Projectiles(450, 450, -trashXSpeed, trashYSpeed, trashWidth, trashHeight, trash3));
			OnScreen.add(new Projectiles(400, 100, trashXSpeed, trashYSpeed, trashWidth, trashHeight, trash4));
			OnScreen.add(new Projectiles(500, 200, -trashXSpeed, trashYSpeed, trashWidth, trashHeight, trash2));
			OnScreen.add(new Projectiles(1000, 400, -trashXSpeed, trashYSpeed, trashWidth, trashHeight, trash));
			OnScreen.add(new Projectiles(1000, 300, oilXSpeed, oilYSpeed, oilWidth, oilHeight, oil));
			OnScreen.add(new Projectiles(600, 500, oilXSpeed, oilYSpeed, oilWidth, oilHeight, oil));
			OnScreen.add(new Projectiles(950, 500, oilXSpeed, oilYSpeed, oilWidth, oilHeight, oil));
			OnScreen.add(new Projectiles(950, 700, oilXSpeed, oilYSpeed, oilWidth, oilHeight, oil));
			OnScreen.add(new Projectiles(850, 400, oilXSpeed, oilYSpeed, oilWidth, oilHeight, oil));
			OnScreen.add(new Projectiles(400, 400, oilXSpeed, oilYSpeed, oilWidth, oilHeight, oil));
			OnScreen.add(new Projectiles(350, 650, oilXSpeed, oilYSpeed, oilWidth, oilHeight, oil));
			OnScreen.add(new Projectiles(700, 200, oilXSpeed, oilYSpeed, oilWidth, oilHeight, oil));
			OnScreen.add(new Projectiles(650, 700, 0, 0, oilWidth, oilHeight, ObjectType.SKIMMER));
		}
		if (Controller.lvl == 5) {
			OnScreen.add(new Projectiles(375, 100, 0, 0, oilWidth, oilHeight,ObjectType.FOOD3));
			xloc=375;
			yloc=500;
		}
		if (Controller.lvl == 6) {
			xloc=700;
			yloc=0;
			hasSkimmer=true;
			hasNet=true;
			OnScreen.add(new Projectiles(350, 350, trashXSpeed, trashYSpeed, trashWidth, trashHeight, trash));
			OnScreen.add(new Projectiles(600, 600, trashXSpeed, trashYSpeed, trashWidth, trashHeight, trash2));
			OnScreen.add(new Projectiles(800, 450, trashXSpeed, trashYSpeed, trashWidth, trashHeight, trash3));
			OnScreen.add(new Projectiles(400, 100, trashXSpeed, trashYSpeed, trashWidth, trashHeight, trash));
			OnScreen.add(new Projectiles(1000, 700, trashXSpeed, trashYSpeed, trashWidth, trashHeight, trash2));
			OnScreen.add(new Projectiles(600, 350, trashXSpeed, trashYSpeed, trashWidth, trashHeight, trash4));
			OnScreen.add(new Projectiles(1000, 550, oilXSpeed, oilYSpeed, oilWidth, oilHeight, oil));
			OnScreen.add(new Projectiles(600, 400, oilXSpeed, oilYSpeed, oilWidth, oilHeight, oil));
			OnScreen.add(new Projectiles(950, 400, oilXSpeed, oilYSpeed, oilWidth, oilHeight, oil));
			OnScreen.add(new Projectiles(900, 100, oilXSpeed, oilYSpeed, oilWidth, oilHeight, oil));
			OnScreen.add(new Projectiles(500, 700, oilXSpeed, oilYSpeed, oilWidth, oilHeight, oil));
			OnScreen.add(new Projectiles(350, 400, oilXSpeed, oilYSpeed, oilWidth, oilHeight, oil));
			OnScreen.add(new Projectiles(500, 200, oilXSpeed, oilYSpeed, oilWidth, oilHeight, oil));
			OnScreen.add(new Projectiles(500, 500, oilXSpeed, oilYSpeed, oilWidth, oilHeight, oil));
			OnScreen.add(new Projectiles(900, 100, oilXSpeed, oilYSpeed, oilWidth, oilHeight, oil));
			OnScreen.add(new Projectiles(700, 200, oilXSpeed, oilYSpeed, oilWidth, oilHeight, oil));
			OnScreen.add(new Projectiles(650, 700, 0, 0, oilWidth, oilHeight, ObjectType.SCISSORS));
			OnScreen.add(new Projectiles(900, 200, 0, 0, oilWidth, oilHeight, ObjectType.HOSTAGE));
		}
	*/
	

}
	public static void removeFish() {
		xloc=10000;
		xloc=10000;
	}
	
	/**
	 * updates the tool bar corresponding to which tools the user currently has picked up using
	 * the booleans hasNet, hasSkimmer, and hasScissors
	 */
	public static void updateToolBar() {
		//Level 1
		if(Controller.lvl==2 && !ToolBar.contains(blank)) {
			ToolBar.add(blank);
		}
		if(hasNet==true && ToolBar.contains(blank)) {
			ToolBar.remove(blank);
			ToolBar.add(netOnly);
		}
		if(ToolBar.contains(netOnly) && netSelected==true) {
			ToolBar.remove(netOnly);
			ToolBar.add(netSelected1);
		}
		
		//Level 2
		if(Controller.lvl==4 && !ToolBar.contains(netSelected1)) {
			ToolBar.add(netOnly);
		}
		if(hasSkimmer==true && ToolBar.contains(netSelected1)) {
			ToolBar.remove(netSelected1);
			ToolBar.add(netSelected2);
		}
		if(ToolBar.contains(netSelected2) && skimmerSelected==true) {
			ToolBar.remove(netSelected2);
			ToolBar.add(skimmerSelected2);
		}
		if(ToolBar.contains(skimmerSelected2) && netSelected==true) {
			ToolBar.remove(skimmerSelected2);
			ToolBar.add(netSelected2);
		}
		
		//Level 3
		if(Controller.lvl==6 &&(!ToolBar.contains(netSelected2)&&!ToolBar.contains(skimmerSelected2))) {
			ToolBar.add(netSelected3);
		}
		if(hasScissors==true && ToolBar.contains(netSelected2)) {
			ToolBar.remove(netSelected2);
			ToolBar.add(netSelected3);
		}
		if(hasScissors==true && ToolBar.contains(skimmerSelected2)) {
			ToolBar.remove(skimmerSelected2);
			ToolBar.add(skimmerSelected3);
		}
		if(ToolBar.contains(netSelected3) && skimmerSelected==true) {
			ToolBar.remove(netSelected3);
			ToolBar.add(skimmerSelected3);
		}
		if(ToolBar.contains(skimmerSelected3) && netSelected==true) {
			ToolBar.remove(skimmerSelected3);
			ToolBar.add(netSelected3);
		}
		if(ToolBar.contains(netSelected3) && scissorsSelected==true) {
			ToolBar.remove(netSelected3);
			ToolBar.add(scissorsSelected3);
		}
		if(ToolBar.contains(skimmerSelected3) && scissorsSelected==true) {
			ToolBar.remove(skimmerSelected3);
			ToolBar.add(scissorsSelected3);
		}
		if(ToolBar.contains(scissorsSelected3) && netSelected==true) {
			ToolBar.remove(scissorsSelected3);
			ToolBar.add(netSelected3);
		}
		if(ToolBar.contains(scissorsSelected3) && skimmerSelected==true) {
			ToolBar.remove(scissorsSelected3);
			ToolBar.add(skimmerSelected3);
		}
		
		//BASE TOOLBAR PICKUP CHANGES^^^
		//NOW WE GO ON TO SELECTION BASED IMAGES

	}
	
	/**
	 * this is our collision handling function. Sets the collision boundary around an object using
	 * imgRadius to set the size of the boundary, also handles which objects can be picked up by 
	 * the user depending on whether the user has the right tool selected
	 * @param imgRadius	an int that sets the size of the collision area
	 */
	public void testCollide(int imgRadius) {
		for (GameObject o: OnScreen) {
			System.out.println(score);
			if(!OnScreen.isEmpty()) {
				
				double distance = Math.sqrt((Math.pow(Math.abs(yloc - o.yLoc), 2)) + Math.pow(Math.abs(xloc - o.xLoc), 2));
				double radiusSum = imgRadius + o.radius -50;
				
				if (o.type == ObjectType.FOOD1||o.type == ObjectType.FOOD2||o.type == ObjectType.FOOD3) {
					if(radiusSum > distance) {
						removeOnScreen(o);
					}
				}
				if (o.type==ObjectType.FISHFOOD) {
					if(Model.forkSelected==true) {
						if(radiusSum > distance) {
							removeOnScreen(o);
						}
					}
				}
				if (o.type == ObjectType.NET) {
					if(radiusSum > distance) {
						removeOnScreen(o);
						hasNet = true;
						ToolBar.remove(blank);
						ToolBar.add(netOnly);
					}
				}
				if (o.type == ObjectType.SKIMMER) {
					if(radiusSum > distance) {
						removeOnScreen(o);
						hasSkimmer = true;
						ToolBar.remove(netOnly);
					}
				}
				if (o.type == ObjectType.SCISSORS) {
					if(radiusSum > distance) {
						removeOnScreen(o);
						hasScissors = true;
					}
				}
				if (skimmerSelected == true && o.type==oil) {
					if(radiusSum > distance) {
						removeOnScreen(o);
					}
				}
				if (scissorsSelected == true && o.type==hostage) {
					if(radiusSum > distance) {
						removeOnScreen(o);
						ToolBar.add(new Projectiles(855, 155, 0, 0, 100, 100, ObjectType.PLAYER));
					}
				}
				if ((netSelected == true) && (o.type==trash||o.type==trash2||o.type==trash3||o.type==trash4)) {
					if(radiusSum > distance) {
						removeOnScreen(o);
					}
				}
				if (skimmerSelected!= true && o.type==oil) {
					if(radiusSum > distance) {
						xloc=700;
						yloc=0;
						score -= 3;
					}
				}
				if ((netSelected != true) && (o.type==trash||o.type==trash2||o.type==trash3||o.type==trash4)) {
					if(radiusSum > distance) {
						xloc=700;
						yloc=0;
						score -= 3;
					}
				}
			}
		}
	}
}
	
