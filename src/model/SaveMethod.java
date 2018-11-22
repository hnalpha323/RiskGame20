/**
 * 
 */
package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 * <p>
 * This class converts the state of the game into a file
 * and also deals with converting the saved game state file into model state such thet user can
 * resume the game where he stopped previously.
 * </p>
 * <i>To do to this this class uses JAVA Serialization</i>
 * @author Waleed
 */
public class SaveMethod {

	/**
	 * @param GameDriver is instance {@link GameDriver} we want to save
	 * @throws IOException if unable to save state of Object to a file
	 */
	public void saveState(GameDriver gameDriver) throws IOException{
		 
		 FileOutputStream fos = new FileOutputStream("tempdata.tiger");
         ObjectOutputStream oos = new ObjectOutputStream(fos);
         //save GameDriver Object
         oos.writeObject(gameDriver);
         fos = new FileOutputStream("tempdata1.tiger");
         oos = new ObjectOutputStream(fos);
         //saves continents
         oos.writeObject(MapDatabase.continents);
         fos = new FileOutputStream("tempdata2.tiger");
         //saves continent values
         oos = new ObjectOutputStream(fos);
         oos.writeObject(MapDatabase.continentValues);
         oos.close();
         fos.close();
	}

	/**
	 * used to save the passed UI state
	 * @param uiState is the state of UI have to be saved
	 * @throws IOException if unable to save state of Object to a file
	 */
	public void saveUIState(HashMap<String, String> uiState) throws IOException {
		FileOutputStream fos = new FileOutputStream("uidate.tiger");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(uiState);
        fos.close();
	}

	
	/**
	 * @return {@link GameDriver} which was saved before
	 */
	public GameDriver getPreviousState() {
		
		FileInputStream fis;
		try {
			fis = new FileInputStream("tempdata.tiger");
			ObjectInputStream ois = new ObjectInputStream(fis);
	        GameDriver gm = (GameDriver) ois.readObject();
	        fis = new FileInputStream("tempdata1.tiger");
			ois = new ObjectInputStream(fis);
	        MapDatabase.continents = (HashMap<String, HashMap<String, Territories>>)ois.readObject();
	        fis = new FileInputStream("tempdata2.tiger");
			ois = new ObjectInputStream(fis);
	        MapDatabase.continentValues = (HashMap<String, Integer>)ois.readObject();
	        ois.close();
	        return gm;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	
	/**
	 * @return the previously saved UI state
	 */
	public HashMap<String, String> getUIState() {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream("uidate.tiger");
			ois = new ObjectInputStream(fis);
	        return (HashMap<String, String>)ois.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally{
	        try {
				ois.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}	        
		}
		
		return null;
	}
	
}
