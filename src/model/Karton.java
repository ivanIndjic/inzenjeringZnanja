package model;

import java.util.ArrayList;

public class Karton {
	
	private String ime;
	private String mail;
	private String prezime;
	private String JMBG;
	private String datumRodjenja;
	private String adresa;
	private String telefon;
	private String zaduzenLekar;
	private String pol;
	private String rasa;
	private ArrayList<IstorijaPregleda> pregledi = new ArrayList<>();
	
	
	public String getPol() {
		return pol;
	}
	public void setPol(String pol) {
		this.pol = pol;
	}
	public String getRasa() {
		return rasa;
	}
	public void setRasa(String rasa) {
		this.rasa = rasa;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getJMBG() {
		return JMBG;
	}
	public void setJMBG(String jMBG) {
		JMBG = jMBG;
	}
	public String getDatumRodjenja() {
		return datumRodjenja;
	}
	public void setDatumRodjenja(String datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String getZaduzenLekar() {
		return zaduzenLekar;
	}
	public void setZaduzenLekar(String zaduzenLekar) {
		this.zaduzenLekar = zaduzenLekar;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public ArrayList<IstorijaPregleda> getBolesti() {
		return pregledi;
	}
	public void setBolesti(ArrayList<IstorijaPregleda> bolesti) {
		this.pregledi = bolesti;
	}
	
	

}
