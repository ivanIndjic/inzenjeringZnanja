package app;

import javax.swing.UIManager;



public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			 UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
			MyApp m = MyApp.getInstance();
		

			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		
		
	}

}
