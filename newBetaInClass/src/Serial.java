import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serial {
	
	static Save obj = new Save();
	static File f = new File("save.txt");
	
	/**
	 * function that saves the current state of the game. It does this by writing the current level
	 * and bassmode to a file
	 * @throws Exception
	 */
	public static void saveGame() throws Exception{
		obj.i=Controller.lvl;
		obj.j=View.bassMode;
		FileOutputStream fos = new FileOutputStream(f);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(obj);

	}
	
	/**
	 * function that reads a 'save' of a game created by the saveGame() function. From the 'save' file
	 * it reads the level and the bassmode which can then be used to start a game from that state
	 * @throws Exception
	 */
	public static void loadGame() throws Exception{
		FileInputStream fis = new FileInputStream(f);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Save obj1 = (Save) ois.readObject();
		Controller.lvl= obj1.i;
		View.bassMode=obj1.j;
	}
}

