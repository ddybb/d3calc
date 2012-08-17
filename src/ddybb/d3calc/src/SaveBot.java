package ddybb.d3calc.src;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveBot {
	
	public static void save(Character c) {

	      try {
	    	  FileOutputStream fileOut = new FileOutputStream(c.name + ".cha");
	    	  ObjectOutputStream out = new ObjectOutputStream(fileOut);
	    	  out.writeObject(c);
	    	  out.close();
	    	  fileOut.close();
	      } catch (IOException e) {
	    	  e.printStackTrace();
	      }
	}
	
	public static void load(String path) {

	      try {
	    	  FileInputStream fileIn = new FileInputStream(path);
	    	  ObjectInputStream in = new ObjectInputStream(fileIn);
	    	  D3Calc.activeChar = (Character)in.readObject();
	    	  in.close();
	    	  fileIn.close();
	      } catch (IOException e) {
	    	  e.printStackTrace();
	    	  return;
	      } catch (ClassNotFoundException e) {
	          e.printStackTrace();
	          return;
	      }
	}
}
