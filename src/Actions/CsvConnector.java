package Actions;

import model.DiseaseDesc;
import ucm.gaia.jcolibri.cbrcore.CBRCase;
import ucm.gaia.jcolibri.cbrcore.CaseBaseFilter;
import ucm.gaia.jcolibri.cbrcore.Connector;
import ucm.gaia.jcolibri.exception.InitializingException;
import ucm.gaia.jcolibri.util.FileIO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

public class CsvConnector implements Connector {
    static HashMap<Integer, Integer> idBrSimp = new HashMap<>();
    public static Integer lineCounterDisease = 0;

    @Override
    public Collection<CBRCase> retrieveAllCases() {
        LinkedList<CBRCase> cases = new LinkedList<CBRCase>();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(FileIO.openFile("resources/s2.csv")));
            if (br == null)
                throw new Exception("Error opening file");

            String line = "";
            while ((line = br.readLine()) != null) {
                if (line.startsWith("#") || (line.length() == 0))
                    continue;
                String[] values = line.split(";");
                lineCounterDisease++;
                String simptoms = values[1];
                String[] parts = simptoms.split(",");
                ArrayList<String> simp = new ArrayList<>();
                simp.addAll(Arrays.asList(parts));
                System.out.println(simp);
                idBrSimp.put(Integer.parseInt(values[5]), simp.size());
                for (int i = 0; i < simp.size(); i++) {
                    CBRCase cbrCase = new CBRCase();
                    DiseaseDesc dd = new DiseaseDesc();
                    dd.setSimptom(simp.get(i));
                    dd.setIme(values[0]);
                    dd.setGodine(values[2]);
                    dd.setPol(values[3]);
                    dd.setRasa(values[4]);
                    dd.setId(Integer.parseInt(values[5]));
                    cbrCase.setDescription(dd);
                    cases.add(cbrCase);

                }
            }
            br.close();
        } catch (Exception e) {

        }
        return cases;
    }

    @Override
    public Collection<CBRCase> retrieveSomeCases(CaseBaseFilter arg0) {
        return null;
    }

    @Override
    public void storeCases(Collection<CBRCase> arg0) {
    }

    @Override
    public void close() {
    }

    @Override
    public void deleteCases(Collection<CBRCase> arg0) {
    }

    @Override
    public void initFromXMLfile(URL arg0) throws InitializingException {
    }

}