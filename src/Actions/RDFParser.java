package Actions;

import org.apache.jena.rdf.model.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;

public class RDFParser {

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
                for(int j = 0;j<cekiraniSimptomi.size();j++) {
                    if (simptoms.get(i).trim().toLowerCase().replace(" ", "_").equals(cekiraniSimptomi.get(j).trim().toLowerCase().replace(" ", "_"))) {
                        flag=1;
                    }
                }
                if(flag==0) {
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

}
