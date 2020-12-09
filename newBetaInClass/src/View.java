import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.event.ActionEvent;
import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Random;

public class View {
	int canvasHeight = 910;
	int canvasWidth = 1440;
	
	//ArrayList with OnScreen images
	ArrayList <Image> images = new ArrayList <Image>();
	int imgWidth = 100;
	int imgHeight = 100;
	Direction currentDirection, lastDirection;
    int modeInd = -1;
    // Used to index the animationSequence outter array. 
    static BassMode bassMode = BassMode.CONFUSE; 
    Direction dir = Direction.EAST;
    GraphicsContext gc;
    Image background;
	// array of wide png images
    Image[] animationSequence;
	static final int imgWidthOrig = 100;
	static final int imgHeightOrig = 100;
	//variables to determine the location of image
	int x = 50;
	int y = 50;
	int picNum = -1; // index for which picture to use
    int picCount = 4; // number of pics in animation 
    
    /**
     * This is our View class constructor that takes in a stage and sets up the view for the game.
     * This does all the work of actually creating what we see
     * 
     * @param theStage what is used to set the scene for the game
     */
	public View(Stage theStage) {
		 theStage.setTitle("Estuary Avengers");
	        Group root = new Group();
	        Scene theScene = new Scene(root);
	        theStage.setScene(theScene);
	        Canvas canvas = new Canvas(canvasWidth, canvasHeight);
	        root.getChildren().add(canvas);        
	        gc = canvas.getGraphicsContext2D();
			importImages();
			
			//KEYHANDLERS SHOULD BE MOVED TO CONTROLLER FOR INCREASED READABILITY??
			theScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent e) {
					switch(e.getCode()) {
					case UP:
						if(dir.equals(Direction.NORTHEAST)||dir.equals(Direction.NORTHWEST)
								||dir.equals(Direction.SOUTH)) dir = Direction.NORTH;
						else if(dir.equals(Direction.EAST)|| dir.equals(Direction.SOUTHEAST)) dir = Direction.NORTHEAST;
						else if(dir.equals(Direction.WEST)|| dir.equals(Direction.SOUTHWEST)) dir = Direction.NORTHWEST;
						break;
					case DOWN:
						if(dir.equals(Direction.SOUTHEAST)|| dir.equals(Direction.SOUTHWEST)
								|| dir.equals(Direction.NORTH)) dir = Direction.SOUTH;
						else if(dir.equals(Direction.WEST)|| dir.equals(Direction.NORTHWEST)) dir = Direction.SOUTHWEST;
						else if(dir.equals(Direction.NORTHEAST)|| dir.equals(Direction.EAST)) dir = Direction.SOUTHEAST;
						break;
					case RIGHT:
						if(dir.equals(Direction.NORTHEAST)|| dir.equals(Direction.SOUTHEAST)
								|| dir.equals(Direction.WEST)) dir = Direction.EAST;
						else if(dir.equals(Direction.NORTH)|| dir.equals(Direction.NORTHWEST)) dir = Direction.NORTHEAST;
						else if(dir.equals(Direction.SOUTH)|| dir.equals(Direction.SOUTHWEST)) dir = Direction.SOUTHEAST;
						break;
					case LEFT:
						if(dir.equals(Direction.NORTHWEST)|| dir.equals(Direction.SOUTHWEST)
								|| dir.equals(Direction.EAST)) dir = Direction.WEST;
						else if(dir.equals(Direction.NORTHEAST)|| dir.equals(Direction.NORTH)) dir = Direction.NORTHWEST;
						else if(dir.equals(Direction.SOUTHEAST)|| dir.equals(Direction.SOUTH)) dir = Direction.SOUTHWEST;
						break;
					case ENTER:
						if(Controller.lvl==-2) {
							try {
								
								Serial.loadGame();
								Model.instantiateOnScreen(Controller.lvl);
							} catch (Exception e1) {
								e1.printStackTrace();

							}
						}
						break;
					case SPACE:
						//System.out.println(overallLvl);
						Controller.lvl+=1;
						Model.OnScreen.clear();
						Model.instantiateOnScreen(Controller.lvl);
						break;
					case DIGIT1:
						if(Controller.lvl==-1) {
						bassMode = BassMode.CONFUSE;
						}//theStage.setScene(scene2);
						if(Controller.lvl==0) {
							Model.ToolBar.remove(Model.tutorialBar);
							Model.ToolBar.add(Model.tutorialBarSelected);
							Model.forkSelected=true;
						}
						if(Model.hasNet==true && (Controller.lvl==2 || Controller.lvl==4 || Controller.lvl==6)) {
						Model.netSelected=true;
						Model.skimmerSelected=false;
						Model.scissorsSelected=false;
						}
						if(Controller.lvl==7||Controller.lvl==9) {
							Controller.lvl++;
						}
						if(Controller.lvl==11) {
							if(bassMode==BassMode.DEFAULT) {
								Controller.lvl+=1;
							}
							if(bassMode==BassMode.CONFUSE) {
								Controller.lvl+=3;
							}
							if(bassMode==BassMode.ATTAC) {
								Controller.lvl+=2;
							}
						}
						break;
					case DIGIT2:
						if(Controller.lvl==-1) {
						bassMode = BassMode.DEFAULT;
						}
						if(Model.hasSkimmer==true && (Controller.lvl==4 || Controller.lvl==6)) {
							Model.netSelected=false;
							Model.skimmerSelected=true;
							Model.scissorsSelected=false;
						}
						if(Controller.lvl==7||Controller.lvl==9) {
							Controller.lvl++;
						}
						if(Controller.lvl==11) {
							if(bassMode==BassMode.DEFAULT) {
								Controller.lvl+=1;
							}
							if(bassMode==BassMode.CONFUSE) {
								Controller.lvl+=3;
							}
							if(bassMode==BassMode.ATTAC) {
								Controller.lvl+=2;
							}
						}
						break;
					case DIGIT4:
						if(Controller.lvl==9) {
							Controller.lvl++;
							break;
						}
					case DIGIT3:
						if(Controller.lvl==-1) {
						bassMode = BassMode.ATTAC;
						}
						if(Model.hasScissors==true && Controller.lvl==6) {
							Model.netSelected=false;
							Model.skimmerSelected=false;
							Model.scissorsSelected=true;
						}
						if(Controller.lvl==7||Controller.lvl==9) {
							Controller.lvl++;
						}
						if(Controller.lvl==11) {
							if(bassMode==BassMode.DEFAULT) {
								Controller.lvl+=1;
							}
							if(bassMode==BassMode.CONFUSE) {
								Controller.lvl+=3;
							}
							if(bassMode==BassMode.ATTAC) {
								Controller.lvl+=2;
							}
						}
						break;
					}
				}
			});
			
	}
	
	/**
	 * 
	 * 	gameobject refers to any object that appears on the screen (user fish, trash, oil). 
	 *  This function updates the x location, y location, and direction of all objects moving
	 *  on the screen every time this function is called. 
	 * 
	 * @param xLoc	the x location of the gameobject that's being updated
	 * @param yLoc	the y location of the gameobject that's being updated
	 * @param direction		the direction that the gameobject is moving
	 * 
	 */
	public void update(int xLoc, int yLoc, Direction direction) {
		//ADD FOR LOOP FOR UPDATING IMAGES HERE??
		x = xLoc;
		y = yLoc;
		currentDirection = direction;
    
        if (modeInd == -1 || currentDirection != lastDirection) {
            Random rand = new Random();
            modeInd = rand.nextInt(4);
            lastDirection = currentDirection;
        }
		Image pics = animationSequence[bassMode.ordinal()];
		picNum = (picNum + 1) % picCount;

        //gc.clearRect(0, 0, canvasWidth, canvasHeight);
        updateBackground();
        gc.drawImage(background, 0, 0, canvasWidth, canvasHeight);
        // Define the Rectangle to crop by (x,y,width,height)
        Rectangle2D croppedPortion = new Rectangle2D(imgWidthOrig*picNum, 0, 
                                                 imgWidthOrig, imgHeightOrig);
        // Define an ImageView with the wide png image 'pics'
        ImageView imageView = new ImageView(pics);
        imageView.setViewport(croppedPortion);
        imageView.setFitWidth(imgWidthOrig);
        imageView.setFitHeight(imgHeightOrig);
        imageView.setSmooth(false);
        // Crop!
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        Image croppedImage = imageView.snapshot(params, null);

        //HERE IS WHERE FISH IS DRAWN ON SCREEN
       /* if(Controller.lvl>=-1) {
        transformAndDraw(gc, croppedImage, Model.xloc, Model.yloc, direction);
        }
        */
        for (GameObject o: Model.OnScreen) {
        	//Move objects on screen, currently only trash moves
        	Model.moveObjects(o);
        	if(o.type==ObjectType.TRASH){
        		gc.drawImage((createImage(o.type.getName())), o.xLoc + (o.width/2),o.yLoc + (o.height/2), 
    				o.width, o.height);
        	}
        	else {
        		gc.drawImage((createImage(o.type.getName())), o.xLoc, o.yLoc,o.width,o.height);
        	}
        }
    	/*for(GameObject k: Model.ToolBar) {
    		gc.drawImage((createImage(k.type.getName())), k.xLoc + (k.width/2),k.yLoc + (k.height/2), 
    				k.width, k.height);
    	}*/
    	Model.updateToolBar();
    }
	
	/**
	 * 
	 * This function is used to import the images for the fish characters and put them into 
	 * an array for animation	 
	 */
	private void importImages() {
        String img_file_base = "images/";
        String bass_file_base = img_file_base + "drop-the-bass/";
        String ext = ".png";

		animationSequence = new Image[BassMode.values().length]; 
			
		for(BassMode mode : BassMode.values())
		{
			animationSequence[mode.ordinal()] = createImage(bass_file_base
                                                        + mode.getName() + ext);
		}		  	
	}
	
	/**		FIX THIS ONE
	 * this function is used to mirror/flip the user's fish image corresponding to its current
	 * direction
	 * 
	 * @param gc
	 * @param image
	 * @param x
	 * @param y
	 * @param d
	 */
	 private void transformAndDraw(GraphicsContext gc, Image image, 
	            double x, double y, Direction d) {
	        double theta = 0.0;
	        boolean isFlipped = false;
	        switch (d) {
	            case NORTH: {
	                theta = -60.0;
	                break;
	            } case NORTHEAST: {
	                theta = -30.0;
	                break;
	            } case EAST: {
	                theta = 0.0;
	                break;
	            } case SOUTHEAST: {
	                theta = 30.0;
	                break;
	            } case SOUTH: {
	                theta = 60.0;
	                break;
	            } case SOUTHWEST: {
	                isFlipped = true;
	                theta = -30.0;
	                break;
	            } case WEST: {
	                isFlipped = true;
	                break;
	            } case NORTHWEST: {
	                isFlipped = true;
	                theta = 30.0;
	                break;
	            }
	        }

	        // Setting x scale to -1 will flip it horizontally
	        if (isFlipped) {
	            ImageView iv = new ImageView(image);
	            iv.setScaleX(-1.0);   
	            SnapshotParameters params = new SnapshotParameters();
	            params.setFill(Color.TRANSPARENT);
	            image = iv.snapshot(params, null);
	        }
	        gc.save();
	        Rotate r = new Rotate(theta, x+imgWidth/2, y+imgWidth/2);
	        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
	        gc.drawImage(image, 0, 0, imgWidthOrig, imgHeightOrig,
	                            x, y, imgWidth, imgHeight);               
	        gc.restore();
	     }
	//Getters and Setters for Canvas Height and Width
	public int getCanvasHeight() {
		return canvasHeight;
	}
	
	public int getCanvasWidth() {
		return canvasWidth;
	}
	
	
	/**
	 * Returns an Image object that can then be painted on the screen. 
	 * The imgFile argument must specify a valid file path
	 * 
	 * @param imgFile a file path giving the base location of the image
	 * @return the image at the specified file path
	 */
	private Image createImage(String imgFile) {
		Image img = new Image(imgFile);
		return img;
	}
	public int getWidth() {
		return canvasWidth;
	}
	
	public int getHeight() {
		return canvasHeight;
	}
	
	public int getImageWidth() {
		return imgWidth;
	}
	
	public int getImageHeight() {
		return imgHeight;
	}
	public BassMode getBassMode() {
		return bassMode;
	}

	public Direction getCurrentDirection() {
		return dir;
	}
	

	/**
	 * function checks for the current level(lvl), an int, and sets the background to the corresponding
	 * background for that level. This gets called in the update() function
	 */
	public void updateBackground() {
		 	if(Controller.lvl==-2) {
	        	background=createImage("images/homescreen.png");
		 	}
		 	else if(Controller.lvl==-1) {
	        	background=createImage("images/characterSelect.png");
	        	///Enter Places
	        }
		 	else if(Controller.lvl==0) {
	        	background = createImage("images/controls.png");
	        	///If they need suggestions, suggestions window
	        }
		 	else if(Controller.lvl==1) {
	        	background = createImage("images/BracketPart1.png");
	        	// 1 v 2
	        }
	        else if(Controller.lvl==2) {
	        	background =createImage("images/BracketPart2.png");
	        	// 5v6
	        }
	        else if(Controller.lvl==3) {
	        	background =createImage("images/BracketPart3.png");
	        	/// w1 vs w2
	        }
	        else if(Controller.lvl==4) {
	        	background = createImage("images/BracketPart4.png");
	        	// 3v4 
	        }
	        else if(Controller.lvl==5) {
	        	background = createImage("images/BracketPart5.png");
	        	// 3v4 , 7v8 , w3 v w4
	        }
	        else if(Controller.lvl==6) {
	        	background = createImage("images/BracketPart6.png");
	        	//w3 v w4
	        }
		  //FROM HERE ON ARE THE QUIZ SLIDES
	        else if(Controller.lvl==7) {
	        	for(GameObject k: Model.ToolBar) {
	        		Model.ToolBar.remove(k);
	        	}
	        	background = createImage("images/BracketPart7.png");
	        	Model.removeFish();
	        }
	        else if(Controller.lvl==8) {
	        	background = createImage("images/BracketPart8.png");
	        }
	        else if(Controller.lvl==9) {
	        	background = createImage("images/BracketPart9.png");
	        }
	        else if(Controller.lvl==10) {
	        	background = createImage("images/BracketPart10.png");
	        }
	        else if(Controller.lvl==11) {
	        	background = createImage("images/BracketPart11.png");
	        }
	        else if(Controller.lvl==12) {
	        	background = createImage("images/BracketPart12.png");
	        }
	        else if(Controller.lvl==13) {
	        	background = createImage("images/BracketPart13.png");
	        }
	        else if(Controller.lvl==14) {
	        	background = createImage("images/BracketPart14.png");
	        }
	        else if(Controller.lvl==15) {
	        	background = createImage("images/BracketPart15.png");
	        }
	}
	
}
