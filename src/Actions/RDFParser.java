package Actions;

import model.Osoba;
import org.apache.jena.rdf.model.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;

public class RDFParser {
    public static  Map<String,String> diseasesAndTests = new HashMap<>();

    public static ArrayList<String> doStuff(String bolest, ArrayList<String> cekiraniSimptomi) {
        Model model = ModelFactory.createDefaultModel();
        ArrayList<String> reT = new ArrayList<String>();
        try {
            InputStream is = null;

            is = new FileInputStream("resources/rdfFurtherTests.ttl");
            RDFDataMgr.read(model, is, Lang.TURTLE);
            Property name = model.getProperty("http://www.ftn.uns.ac.rs/simptom", "#ime");

            ResIterator nt = model.listResourcesWithProperty(name);
            List<Resource> mylist = nt.toList();
            List<String> simptoms = new ArrayList<String>();
            for (Resource simptom : mylist) {
                StmtIterator bolesti = simptom.listProperties();
                List<Statement> bolestiList = bolesti.toList();
                for (Statement statement : bolestiList) {
                    try {
                        if (statement.getLiteral().getString().equals(bolest))
                            simptoms.add(simptom.getProperty(name).getLiteral().getString());

                    } catch (Exception ignored) {
                    }
                }
            }

            int flag;
            for (int i = 0; i < simptoms.size(); i++) {
                flag = 0;
                for (int j = 0; j < cekiraniSimptomi.size(); j++) {
                    if (simptoms.get(i).trim().toLowerCase().replace(" ", "_").equals(cekiraniSimptomi.get(j).trim().toLowerCase().replace(" ", "_"))) {
                        flag = 1;
                    }
                }
                if (flag == 0) {
                    reT.add(simptoms.get(i));
                    System.out.println("VRACENO: " + simptoms.get(i));

                }
            }

            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reT;

    }

    public static ArrayList<String> riskGroup(Osoba o, ArrayList<String>oldDiseases) {
        Model model = ModelFactory.createDefaultModel();
        ArrayList<String> sveBolesti = new ArrayList<>();
        try {
            InputStream is = null;

            is = new FileInputStream("resources/groupAtRisk.ttl");
            RDFDataMgr.read(model, is, Lang.TURTLE);
            Property name = model.getProperty("http://www.ftn.uns.ac.rs/risk", "#ime");
            Property age = model.getProperty("http://www.ftn.uns.ac.rs/risk", "#godine");
            Property sex = model.getProperty("http://www.ftn.uns.ac.rs/risk", "#pol");
            Property race = model.getProperty("http://www.ftn.uns.ac.rs/risk", "#rasa");
            Property treatment = model.getProperty("http://www.ftn.uns.ac.rs/risk", "#tretman");

            ResIterator nt = model.listResourcesWithProperty(name);
            List<Resource> mylist = nt.toList();
            boolean flag;
            for (Resource bol : mylist) {
                String godine = null;
                String sexx = null;
                String rasa = null;
                flag = false;
//                if (bolest.trim().toLowerCase().replace(" ", "_").
//                        equals(bol.getProperty(name).getString().trim().
//                                toLowerCase().replace(" ", "_"))) {
                try {
                    godine = bol.getProperty(age).getLiteral().getString();
                    System.out.println(bol.getProperty(age).getLiteral().getString());
                } catch (Exception ignored) {
                }
                try {
                    rasa = bol.getProperty(race).getLiteral().getString();
                } catch (Exception ignored) {

                }
                try {
                    sexx = bol.getProperty(sex).getLiteral().getString();
                } catch (Exception ignored) {

                }
                if (godine != null) {
                    int down = Integer.parseInt(godine.split("-")[0]);
                    int upper = Integer.parseInt(godine.split("-")[1]);
                    if (o.getGodine() > down && o.getGodine() < upper) {
                        flag = true;

                    }
                }
                if (sexx != null) {
                    if (sexx.toLowerCase().equals(o.getPol().toLowerCase())) {
                        flag = true;

                    }
                }
                if (rasa != null) {
                    if (rasa.toLowerCase().equals(o.getRasa().toLowerCase())) {
                        flag = true;

                    }
                }

                if (flag){
                    sveBolesti.add(bol.getProperty(name).getLiteral().getString());
                    diseasesAndTests.put(bol.getProperty(name).getLiteral().getString(),bol.getProperty(treatment).getLiteral().getString());
                    flag = false;
                }
            }

//            }
            for (String s:oldDiseases){
                for(Resource resource:mylist){
                    if(s.replace(" ","_").toLowerCase().equals(resource.getProperty(name).getString().replace(" ","_").toLowerCase())){
                        String ime = resource.getProperty(name).getString().replace(" ","_").toLowerCase();
                        String opis = resource.getProperty(treatment).getString();
                        if (!diseasesAndTests.containsKey(ime))
                        diseasesAndTests.put(ime,opis);
                    }
                }
            }

            return sveBolesti;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<String>();
    }
}


