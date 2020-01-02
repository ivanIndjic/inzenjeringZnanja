package model;

import java.util.ArrayList;

public class Osoba {
	private int godine=0;
	private String pol="Male";
	private String rasa="White";
	private ArrayList<String> simptomi=new ArrayList<String>();
	public int getGodine() {
		return godine;
	}
	public void setGodine(int godine) {
		this.godine = godine;
	}
	public String getPol() {
		return pol;
	}
	public void setPol(String pol) {
		this.pol = pol;
	}
	public String getRasa() {
		return rasa;
	}
	@Override
	public String toString() {
		return "Osoba [godine=" + godine + ", pol=" + pol + ", rasa=" + rasa + ", simptomi=" + simptomi + "]";
	}
	public void setRasa(String rasa) {
		this.rasa = rasa;
	}
	public ArrayList<String> getSimptomi() {
		return simptomi;
	}
	public void setSimptomi(ArrayList<String> simptomi) {
		this.simptomi = simptomi;
	}
}
