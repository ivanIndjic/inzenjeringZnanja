package example;

import java.sql.SQLException;

import view.mainFrame;

public class MyApp {
	private static MyApp instance = null;
	
	public static MyApp getInstance() {
    	if (instance == null) {
			instance = new MyApp();
			instance.initialise();
		}
		return instance;
	}
	public void initialise() {
		try {
			mainFrame mn = new mainFrame();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
