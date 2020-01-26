package app;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

///PROVERIIIIIIIIIIIIIIIIIIIIII
public class CalculationOfTopDisease {
    private ArrayList<HashMap<String, Map<String, Float>>> allRankingLists = new ArrayList<HashMap<String, Map<String, Float>>>();
    //kljuc: simptom vrednost: bolest/verovatnoca
    private HashMap<String, Float> mapOfSumProbabilities = new HashMap<String, Float>();

    public CalculationOfTopDisease(ArrayList<HashMap<String, Map<String, Float>>> allRankingLists) {
        this.allRankingLists = allRankingLists;
    }

    public static float round(float number, int decimalPlace) {
        BigDecimal bd = new BigDecimal(number);
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    public HashMap<String, Float> calculation() {
        for (HashMap<String, Map<String, Float>> throughList : allRankingLists) {
            for (Map<String, Float> maps : throughList.values()) {
                for (String disease : maps.keySet()) {
                    if (mapOfSumProbabilities.containsKey(disease)) {
                        Float trVr = mapOfSumProbabilities.get(disease);
                        Float novaVr = (trVr + maps.get(disease)) / 2;
                        mapOfSumProbabilities.replace(disease, novaVr);
                    } else {
                        mapOfSumProbabilities.put(disease, maps.get(disease));
                    }
                }
            }
        }
        //skaliranje da zbir svih verovatnoca bude 100%
        Float sum = 0f;
        for (String key : mapOfSumProbabilities.keySet()) {
            sum += mapOfSumProbabilities.get(key);
        }
        Float scaleNum = 1 / sum;
        HashMap<String, Float> mapOfScaledProbabilities = new HashMap<String, Float>();
        for (String key : mapOfSumProbabilities.keySet()) {
            mapOfScaledProbabilities.put(key, mapOfSumProbabilities.get(key) * scaleNum);
        }
        return mapOfScaledProbabilities;
    }

    public String printOfProbabilitiesRBR(Map<String, Float> map) {
        String print = "";
        String disease = "";
        int it = 0;
        for (Entry<String, Float> entry : map.entrySet()) {
            if (entry.getValue() != 0) {
                //Da bi bilo na 2 decmalna
                disease = entry.getKey();
                disease = disease.substring(0, 1).toUpperCase() + disease.substring(1);
                disease = disease.replaceAll("_", " ");
                print += disease + " : " + round(entry.getValue() * 100, 2) + " %" + "\n";
                it++;
            }
            if (it >= 5) { //maximalno 5 bolesti se ispisuje
                break;
            }
        }
        return print;
    }
}
