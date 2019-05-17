package app;

import java.util.ArrayList;

import view.FirstWizard;

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
		FirstWizard fw = new FirstWizard();
		ArrayList<Object> obj = fw.createFrame();
	}
	

}
