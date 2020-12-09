import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.animation.AnimationTimer;
import javafx.scene.shape.Rectangle;
import javafx.scene.*;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;

//Will Koenig, Kyle Oak, Ryan Bonacquisti

public class Controller extends Application implements Serializable {
	//data fields hold Model and View
	private Model model;
	//private Model2 levelmodel2;
	private View view;
	//private View2 levelview2;
	static int lvl=-2;
	
	/*public int IncrementLvl() {
		if(model.OnScreen.isEmpty()) {
			lvl ++;
		}
		return lvl;
	}*/
	

	
	
	public static void main(String[] args) throws Exception{
		launch(args); 
	}
	
	@Override
	public void start(Stage theStage) {
		view = new View(theStage);
		model = new Model(view.getWidth(), view.getHeight(),view.getImageWidth(), view.getImageHeight(), lvl);
		
		new AnimationTimer() {
			public void handle(long currentNanoTime){             
				if(Controller.lvl>-2) {
					try {
						Serial.saveGame();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				model.updateBassModeAndDirection(view.getBassMode(), view.getCurrentDirection());
                model.updateLocationandDirection();
				view.update(model.getX(), model.getY(), model.getDirection());
				model.testCollide(Model.imgRadius);
				
				try {
					Thread.sleep(10);                
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}.start();
			theStage.show();
		}
}
