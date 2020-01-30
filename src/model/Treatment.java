package model;

import ucm.gaia.jcolibri.cbrcore.Attribute;
import ucm.gaia.jcolibri.cbrcore.CaseComponent;

import java.lang.reflect.Field;

public class Treatment implements CaseComponent {

    private String disease;
    private String treatment;

    @Override
    public String   toString() {
        return "Treatment{" +
                "disease='" + disease + '\'' +
                ", treatment='" + treatment + '\'' +
                '}';
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }


    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    @Override
    public Attribute getIdAttribute() {
        return null;
    }
}
