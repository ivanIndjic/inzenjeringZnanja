package model;

import ucm.gaia.jcolibri.cbrcore.Attribute;
import ucm.gaia.jcolibri.cbrcore.CaseComponent;

import java.lang.reflect.Field;

public class Treatment implements CaseComponent {

    private String disease;
    private String treatment;
    private Integer age;

    public Treatment(String disease, String treatment, Integer age) {
        this.disease = disease;
        this.treatment = treatment;
        this.age = age;
    }
    public Treatment(){}

    @Override
    public String toString() {
        return "Treatment{" +
                "disease='" + disease + '\'' +
                ", treatment='" + treatment + '\'' +
                ", age=" + age +
                '}';
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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
