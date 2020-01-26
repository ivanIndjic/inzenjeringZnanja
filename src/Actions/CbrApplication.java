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

    public static String ispisivanjeVerovatnoca() {
        String ispis = "";
        String bolest = "";
        int brojac = 0;
        for (Map.Entry<String, Double> entry : sortedFinalMap.entrySet()) {
            if (entry.getValue() != 0) {
                bolest = entry.getKey();
                bolest = bolest.substring(0, 1).toUpperCase() + bolest.substring(1);
                bolest = bolest.replaceAll("_", " ");
                ispis += bolest + " : " + round(entry.getValue() * 100, 2) + " %" + "\n";
                //ispis+=bolest+"\n";
                brojac++;
            } //ovo gore da bi bilo na 2 decmalna
            if (brojac >= 5) {
                break;
            }
        }
        return ispis;
    }

    public static int kategorisiGodine(int godine) {
        if (godine < 1) {
            return 1;
        } else if (godine < 5) {
            return 2;
        } else if (godine < 15) {
            return 3;
        } else if (godine < 30) {
            return 4;
        } else if (godine < 45) {
            return 5;
        } else if (godine < 60) {
            return 6;
        } else if (godine < 75) {
            return 7;
        } else {
            return 8;
        }
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
        simConfig.addMapping(new Attribute("godine", DiseaseDesc.class), new Equal());
        simConfig.addMapping(new Attribute("rasa", DiseaseDesc.class), new Equal());
        simConfig.addMapping(new Attribute("pol", DiseaseDesc.class), new Equal());
        // simConfig.addMapping(new Attribute("ime",DiseaseDesc.class), ts);
        // simConfig.addMapping(new Attribute("season", TravelDescription.class), new Equal());
        // simConfig.addMapping(new Attribute("accommodation", TravelDescription.class), new Equal());
        // simConfig.addMapping(new Attribute("hotel", TravelDescription.class), new Equal());

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

//            String zasplit = nse.get_case().getDescription() + ";" + nse.getEval();
//            String[] values = zasplit.split(";");
//            if (a == 0) {
//                prag = Float.parseFloat(values[2]);
//                map.put(values[0], Float.parseFloat(values[1]));
//                a++;
//            } else {
//
//                if (prag == Float.parseFloat(values[2])) {
//                    System.out.println(nse.getEval());
//                    map.put(values[0], Float.parseFloat(values[1]));
//                }
//            }
//        }

//        for (String s : map.keySet()) {
//            System.out.println(s + " " + map.get(s));
//        }
//        mapp.put("blidness", map);


        }
        HashMap<String, HashMap<Integer, Double>> mapaBolestIdSumaVrv = new HashMap<>();
        for (String bolest : mapaBolesi.keySet()) {
            HashMap<Integer, ArrayList<Double>> idVrvLista = mapaBolesi.get(bolest);
            HashMap<Integer, Double> idVrv = new HashMap<>();
            for (Integer idBol : idVrvLista.keySet()) {
                ArrayList<Double> vrvLista = idVrvLista.get(idBol);
                Double sumaVerovatnoca = 0d;
                for (Double vrv : vrvLista) {
                    sumaVerovatnoca += vrv;
                }
                idVrv.put(idBol, sumaVerovatnoca / vrvLista.size());
            }
            mapaBolestIdSumaVrv.put(bolest, idVrv);
        }
        System.out.println("MAPA " + mapaBolestIdSumaVrv);
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
        Integer duzina = simptomi.size();
        StandardCBRApplication recommender = null;
        for (int i = 0; i < simptomi.size(); i++) {
            System.out.println("SIMPTOMI " + simptomi.get(i));
            try {
                recommender = new CbrApplication();
                recommender.configure();
                recommender.preCycle();
                CBRQuery query = new CBRQuery();
                DiseaseDesc dd = new DiseaseDesc();
                String godine = String.valueOf(o.getGodine());
                //  dd.setIme(simptomi.get(i));
                dd.setGodine(godine);
                dd.setPol(o.getPol());
                dd.setRasa(o.getRasa());
                dd.setSimptom(simptomi.get(i));
                System.out.println(dd.toString());
                query.setDescription(dd);
                recommender.cycle(query);
                recommender.postCycle();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public static boolean proveraRazlikeVerovatnocaZaDaljaIspitivanjaDouble(Map<String, Double> map) {
        boolean povratna = true;
        Double vrednost1 = 0d;
        Double vrednost2 = 0.0d;
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
        if (vrednost1 > vrednost2 + 0.3) {
            povratna = false;
        }

        return povratna;
    }

}