package Actions;

import model.DiseaseDesc;
import model.Osoba;
import ucm.gaia.jcolibri.casebase.LinealCaseBase;
import ucm.gaia.jcolibri.cbraplications.StandardCBRApplication;
import ucm.gaia.jcolibri.cbrcore.*;
import ucm.gaia.jcolibri.exception.ExecutionException;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.NNConfig;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import ucm.gaia.jcolibri.method.retrieve.RetrievalResult;
import ucm.gaia.jcolibri.method.retrieve.selection.SelectCases;
import view.SelectSymptoms;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class CbrApplication implements StandardCBRApplication {
    //bolest -- id-max verovatnoce za svaki cekirani simptom
    public static HashMap<String, HashMap<Integer, ArrayList<Double>>> mapaBolesiMax = new HashMap<>();
    //id --minimalna verovatnoca
    //ako klikne 2 simptoma a u slucaju ima 3 da se taj 1 racuna kao da je promasen
    public static HashMap<Integer, Double> idMin = new HashMap<>();
    public static Map<String, Double> sortedFinalMap = new HashMap<>();
    Connector _connector;
    /**
     * Connector object
     */
    CBRCaseBase _caseBase;
    /**
     * CaseBase object
     */

    NNConfig simConfig;

    public static Map<String, Double> sortByValue(final Map<String, Double> map) {
        return map.entrySet()
                .stream()
                .sorted((Map.Entry.<String, Double>comparingByValue().reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

    }

    public static float round(double number, int decimalPlace) {
        BigDecimal bd = new BigDecimal(number);
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    public static Map<String, Double> scalingOfSortedMap() {
        Double sum = 0d;
        for (String key : sortedFinalMap.keySet()) {
            sum += sortedFinalMap.get(key);
        }
        Double scaleNum = 1 / sum;
        HashMap<String, Double> mapOfScaledProbabilities = new HashMap<String, Double>();
        for (String key : sortedFinalMap.keySet()) {
            mapOfScaledProbabilities.put(key, sortedFinalMap.get(key) * scaleNum);
        }
        return mapOfScaledProbabilities;
    }

    public static String printOfProbabilitiesCBR() {
        String print = "";
        String disease = "";
        int it = 0;
        Map<String, Double> mapOfScaledProbabilities = scalingOfSortedMap();
        for (Map.Entry<String, Double> entry : mapOfScaledProbabilities.entrySet()) {
            if (entry.getValue() != 0) {
                disease = entry.getKey();
                disease = disease.substring(0, 1).toUpperCase() + disease.substring(1);
                disease = disease.replaceAll("_", " ");
                try {
                    print += disease + " : " + round(entry.getValue() * 100, 2) + " %" + "\n";
                } catch(Exception e) {
                    if(entry.getValue().isNaN()) {
                        print += disease + " : 0 %" + "\n";
                    }else {
                        print += disease + " : " + entry.getValue() + " %" + "\n";
                    }
                }
                it++;
            }
            if (it >= 5) {
                break;
            }
        }
        return print;
    }

    public static int yearCategorization(int yearsOld) {
        if (yearsOld < 1) {
            return 1;
        } else if (yearsOld < 5) {
            return 2;
        } else if (yearsOld < 15) {
            return 3;
        } else if (yearsOld < 30) {
            return 4;
        } else if (yearsOld < 45) {
            return 5;
        } else if (yearsOld < 60) {
            return 6;
        } else if (yearsOld < 75) {
            return 7;
        } else {
            return 8;
        }
    }

    public static boolean proveraRazlikeVerovatnocaZaDaljaIspitivanjaDouble(Map<String, Double> map) {
        boolean povratna = true;
        Double vrednost1 = 0d;
        Double vrednost2 = 0d;
        int brojac = 0;
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            if (brojac == 0) {
                SelectSymptoms.bolest1 = entry.getKey();
                vrednost1 = entry.getValue();
            } else if (brojac == 1) {
                SelectSymptoms.bolest2 = entry.getKey();
                vrednost2 = entry.getValue();
            } else if (brojac == 2) {
                SelectSymptoms.bolest3 = entry.getKey();
            }
            brojac++;
        }
        if (vrednost1 > vrednost2 + 0.3 || vrednost2==0.0) {
            povratna = false;
        }

        return povratna;
    }

    /**
     * KNN configuration
     */

    public void configure() throws ExecutionException {
        _connector = new CsvConnector();
        _caseBase = new LinealCaseBase();// Create a Lineal case base for in-memory organizatiom
        simConfig = new NNConfig(); // KNN configuration
        simConfig.setDescriptionSimFunction(new Average());  // global similarity function = average

//        TableSimilarity ts = new TableSimilarity((Arrays.asList(new String[]{"blidness", "double vision"})));
//        ts.setSimilarity("blidness", "double vision", 0);

        simConfig.addMapping(new Attribute("simptom", DiseaseDesc.class), new Equal());
        simConfig.setWeight(new Attribute("simptom", DiseaseDesc.class), 0.64);

        simConfig.addMapping(new Attribute("godine", DiseaseDesc.class), new Equal());
        simConfig.setWeight(new Attribute("godine", DiseaseDesc.class), 0.12);

        simConfig.addMapping(new Attribute("rasa", DiseaseDesc.class), new Equal());
        simConfig.setWeight(new Attribute("rasa", DiseaseDesc.class), 0.12);

        simConfig.addMapping(new Attribute("pol", DiseaseDesc.class), new Equal());
        simConfig.setWeight(new Attribute("pol", DiseaseDesc.class), 0.12);


        // Equal - returns 1 if both individuals are equal, otherwise returns 0
        // Interval - returns the similarity of two number inside an interval: sim(x,y) = 1-(|x-y|/interval)
        // Threshold - returns 1 if the difference between two numbers is less than a threshold, 0 in the other case
        // EqualsStringIgnoreCase - returns 1 if both String are the same despite case letters, 0 in the other case
        // MaxString - returns a similarity value depending of the biggest substring that belong to both strings
        // EnumDistance - returns the similarity of two enum values as the their distance: sim(x,y) = |ord(x) - ord(y)|
        // EnumCyclicDistance - computes the similarity between two enum values as their cyclic distance
        // Table - uses a table to obtain the similarity between two values. Allowed values are Strings or Enums. The table is read from a text file.
        // TableSimilarity(List<String> values).setSimilarity(value1,value2,similarity)
    }

    public void cycle(CBRQuery query) throws ExecutionException {
        HashMap<String, HashMap<Integer, ArrayList<Double>>> mapaBolesi = new HashMap<>();
        Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_caseBase.getCases(), query, simConfig);
        eval = SelectCases.selectTopKRR(eval, 8);
        String perc = "";
        String disea = "";
        Integer id = 0;
        System.out.println("Retrieved cases:");
        for (RetrievalResult nse : eval) {
            perc = String.valueOf(nse.getEval());
            String[] split = nse.get_case().getDescription().toString().split(":");
            disea = split[split.length - 2];
            disea = disea.substring(0, disea.length() - 2);
            id = Integer.parseInt(split[split.length - 1]);
            if (mapaBolesi.containsKey(disea)) {
                HashMap<Integer, ArrayList<Double>> idVerovatnoce = mapaBolesi.get(disea);
                if (idVerovatnoce.containsKey(id)) {
                    ArrayList<Double> listaVerovatnoca = idVerovatnoce.get(id);
                    listaVerovatnoca.add(nse.getEval());
                    idVerovatnoce.put(id, listaVerovatnoca);
                    mapaBolesi.put(disea, idVerovatnoce);
                } else {
                    ArrayList<Double> listaVerovatnoca = new ArrayList<>();
                    listaVerovatnoca.add(nse.getEval());
                    idVerovatnoce.put(id, listaVerovatnoca);
                    mapaBolesi.put(disea, idVerovatnoce);
                }
            } else {
                HashMap<Integer, ArrayList<Double>> idVerovatnoce = new HashMap<>();
                ArrayList<Double> listaVerovatnoca = new ArrayList<>();
                listaVerovatnoca.add(nse.getEval());
                idVerovatnoce.put(id, listaVerovatnoca);
                mapaBolesi.put(disea, idVerovatnoce);
            }
            System.out.println(disea + " ID " + id + " " + perc);
        } //posle ovoga imamo mapu sa id, nizom vrv koje se odnose na taj id

        for (String k : mapaBolesi.keySet()) {
            HashMap<Integer, ArrayList<Double>> idMax = new HashMap<>();

            for (Integer k2 : mapaBolesi.get(k).keySet()) {
                Double max = mapaBolesi.get(k).get(k2).get(0);
                Double min = mapaBolesi.get(k).get(k2).get(0);
                for (Double d : mapaBolesi.get(k).get(k2)) {
                    if (d > max) {
                        max = d;
                    }
                    if (d < min) {
                        min = d;
                    }
                }
                if (!idMin.containsKey(k2)) {
                    idMin.put(k2, min);
                }
                ArrayList<Double> maxVrv = new ArrayList<>();
                maxVrv.add(max);
                idMax.put(k2, maxVrv);
            }
            if (!mapaBolesiMax.containsKey(k)) {
                mapaBolesiMax.put(k, idMax);
            } else {
                HashMap<Integer, ArrayList<Double>> temp = mapaBolesiMax.get(k);
                for (Integer key : idMax.keySet()) {
                    if (temp.containsKey(key)) {
                        ArrayList<Double> tempVrv = temp.get(key);
                        for (Double vrv : idMax.get(key)) {
                            tempVrv.add(vrv);
                        }
                        temp.put(key, tempVrv);
                    } else {
                        temp.put(key, idMax.get(key));
                    }
                }
            }
        }

        for (String k : mapaBolesiMax.keySet()) {
            for (Integer k2 : mapaBolesiMax.get(k).keySet()) {
                System.out.println("NAJVECE VEROVATNOCE   " + k + " " + k2 + " " + mapaBolesiMax.get(k).get(k2));
            }
        } //mapa bolesti a vr je mapa id-max vrv

        HashMap<String, HashMap<Integer, Double>> mapaBolestIdSumaVrv = new HashMap<>();
        for (String bolest : mapaBolesi.keySet()) {
            HashMap<Integer, ArrayList<Double>> idVrvLista = mapaBolesiMax.get(bolest);
            HashMap<Integer, Double> idVrv = new HashMap<>();
            for (Integer idBol : idVrvLista.keySet()) {
                ArrayList<Double> vrvLista = idVrvLista.get(idBol);
                Double sumaVerovatnoca = 0d;
                for (Double vrv : vrvLista) {
                    sumaVerovatnoca += vrv;
                }
                if (vrvLista.size() < CsvConnector.idBrSimp.get(idBol)) {
                    int razlika = CsvConnector.idBrSimp.get(idBol) - vrvLista.size();
                    int razlikaReplika = razlika;
                    while (razlikaReplika > 0) { //ako ima manje simptoma kliknutih nego u slucaju da se taj manjak
                        //oduzima od verovatnoce ukupne
                        sumaVerovatnoca += idMin.get(idBol);
                        razlikaReplika--;
                    }
                    idVrv.put(idBol, sumaVerovatnoca / (vrvLista.size() + razlika));
                } else {
                    idVrv.put(idBol, sumaVerovatnoca / vrvLista.size());
                }
            }
            mapaBolestIdSumaVrv.put(bolest, idVrv);
        }
        System.out.println("MAPA " + mapaBolestIdSumaVrv);

        //sada za svaki bolest treba da se nadje id sa najvecom verovatnocom
        HashMap<String, Double> bolestMaxVerovatnoca = new HashMap<>();
        for (String bolest : mapaBolestIdSumaVrv.keySet()) {
            ArrayList<Double> maxVrv = new ArrayList<>();
            for (Integer key : mapaBolestIdSumaVrv.get(bolest).keySet()) {
                maxVrv.add(mapaBolestIdSumaVrv.get(bolest).get(key));
            }
            Double max = maxVrv.get(0);
            for (Double vrv : maxVrv) {
                if (max < vrv) {
                    max = vrv;
                }
            }
            bolestMaxVerovatnoca.put(bolest, max);
        }
        sortedFinalMap = sortByValue(bolestMaxVerovatnoca);
        System.out.println("SORTIRANO " + sortedFinalMap);
    }

    public void postCycle() throws ExecutionException {

    }

    public CBRCaseBase preCycle() throws ExecutionException {
        _caseBase.init(_connector);
        java.util.Collection<CBRCase> cases = _caseBase.getCases();
        for (CBRCase c : cases)
            System.out.println(c.getDescription());
        return _caseBase;
    }

    public void mainS(Osoba o, ArrayList<String> simptomi) {
        mapaBolesiMax = new HashMap<>();
        sortedFinalMap = new HashMap<>();
        StandardCBRApplication recommender = null;
        for (int i = 0; i < simptomi.size(); i++) {
            System.out.println("SIMPTOM " + simptomi.get(i));
            try {
                recommender = new CbrApplication();
                recommender.configure();
                recommender.preCycle();
                CBRQuery query = new CBRQuery();
                DiseaseDesc dd = new DiseaseDesc();
                String godine = String.valueOf(yearCategorization(o.getGodine()));
                //  dd.setIme(simptomi.get(i));
                dd.setGodine(godine);
                dd.setPol(o.getPol());
                dd.setRasa(o.getRasa());
                dd.setSimptom(simptomi.get(i));
                System.out.println("DISEASE DESCRIPTION " + dd.toString());
                query.setDescription(dd);
                recommender.cycle(query);
                recommender.postCycle();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}