package model;

import example.MyApp;
import ucm.gaia.jcolibri.cbrcore.Attribute;
import ucm.gaia.jcolibri.cbrcore.CaseComponent;

import javax.swing.*;

public class DiseaseDesc implements CaseComponent {

    private String godine;
    private String pol;
    private String simptom;
    private String rasa;
    private String ime;
    private Integer id;
    public String getGodine() {
        return godine;
    }

    public String toString() {
        return "Rasa: " + this.getRasa() + " Godine: " + this.getGodine() + " Pol: " + this.getPol() + " Simptom: " + this.getSimptom() + " Ime:" + this.getIme() + "Id:"+this.getId();
    }

    public void setGodine(String godine) {
        this.godine = godine;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public String getSimptom() {
        return simptom;
    }

    public void setSimptom(String simptom) {
        this.simptom = simptom;
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

    @Override
    public Attribute getIdAttribute() {
        //TODO vratiti bolest
        return null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

