package example;

import javax.swing.JFrame;
import javax.swing.UIManager;

import model.Osoba;
import view.DaljaIspitivanja;


public class Main extends JFrame{

	public static void main(String[] args) throws Exception {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
			MyApp m = MyApp.getInstance();
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
