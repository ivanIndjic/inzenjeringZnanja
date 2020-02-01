package Actions;

import model.Treatment;
import ucm.gaia.jcolibri.cbrcore.CBRCase;
import ucm.gaia.jcolibri.cbrcore.CaseBaseFilter;
import ucm.gaia.jcolibri.cbrcore.Connector;
import ucm.gaia.jcolibri.exception.InitializingException;
import ucm.gaia.jcolibri.util.FileIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collection;
import java.util.LinkedList;

public class CsvConnector2 implements Connector {

    @Override
    public void initFromXMLfile(URL url) throws InitializingException {

    }

    @Override
    public void close() {

    }

    @Override
    public void storeCases(Collection<CBRCase> collection) {

    }

    @Override
    public void deleteCases(Collection<CBRCase> collection) {

    }

    @Override
    public Collection<CBRCase> retrieveAllCases() {
        LinkedList<CBRCase> cases2 = new LinkedList<CBRCase>();
        BufferedReader br = new BufferedReader(new InputStreamReader(FileIO.openFile("resources/treatment.csv")));
        System.out.println("Otvoren csv");
        String line = "";
        while (true){
            try {
                if ((line = br.readLine()) == null) break;
                if (line.startsWith("#") || (line.length() == 0))
                    continue;
                String[] values = line.split(";");
                Treatment t = new Treatment(values[0],values[2],Integer.parseInt(values[1]));
                CBRCase cbrCase = new CBRCase();
                cbrCase.setDescription(t);
                cases2.add(cbrCase);

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        return cases2;
    }

    @Override
    public Collection<CBRCase> retrieveSomeCases(CaseBaseFilter caseBaseFilter) {
        return null;
    }
}
