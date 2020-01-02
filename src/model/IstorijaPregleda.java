package model;

public class IstorijaPregleda {

    private int idPregleda;
    public String simptomi;
    public String tretman;
    private String dodatneNapomene;
    public String doktor;
    private String datum;
    private String bolest;

    public String getSimptomi() {
        return simptomi;
    }

    public void setSimptomi(String nazivBolesti) {
        this.simptomi = nazivBolesti;
    }

    public String getTretman() {
        return tretman;
    }

    public void setTretman(String tretman) {
        this.tretman = tretman;
    }

    public String getDoktor() {
        return doktor;
    }

    public void setDoktor(String doktor) {
        this.doktor = doktor;
    }

    public String getDodatneNapomene() {
        return dodatneNapomene;
    }

    public void setDodatneNapomene(String dodatneNapomene) {
        this.dodatneNapomene = dodatneNapomene;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getBolest() {
        return bolest;
    }

    public void setBolest(String bolest) {
        this.bolest = bolest;
    }

	public int getIdPregleda() {
		return idPregleda;
	}

	public void setIdPregleda(int idPregleda) {
		this.idPregleda = idPregleda;
	}
}
