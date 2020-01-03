package app;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

///PROVERIIIIIIIIIIIIIIIIIIIIII
public class RacunanjeTopBolesti {
    private ArrayList<HashMap<String, Map<String, Float>>> sveRangListe = new ArrayList<HashMap<String, Map<String, Float>>>();
    //kljuc: simptom vrednost: bolest/verovatnoca
    private HashMap<String, Float> mapaSabranih = new HashMap<String, Float>();

    //private HashMap<HashMap<String,Float>, Integer> mapaSabPuta=new HashMap<HashMap<String,Float>, Integer>();
    public RacunanjeTopBolesti(ArrayList<HashMap<String, Map<String, Float>>> sveRangListe) {
        this.sveRangListe = sveRangListe;
    }

    public static float round(float number, int decimalPlace) {
        BigDecimal bd = new BigDecimal(number);
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    public HashMap<String, Float> racunanje() {
        for (HashMap<String, Map<String, Float>> krozListu : sveRangListe) {
            for (Map<String, Float> mape : krozListu.values()) {
                for (String bolest : mape.keySet()) {
                    if (mapaSabranih.containsKey(bolest)) {
                        Float trVr = mapaSabranih.get(bolest);
                        Float novaVr = (trVr + mape.get(bolest)) / 2;
                        mapaSabranih.replace(bolest, novaVr);
                        //Integer tr=
                        //if(mapaSabPuta.containsKey(key))
                    } else {
                        mapaSabranih.put(bolest, mape.get(bolest));
                        //HashMap<String,Float> tr=new HashMap<String,Float>();
                        //tr.put(bolest, mape.get(bolest));
                        //mapaSabPuta.put(tr, 1);
                    }
                }
            }
        }
        return mapaSabranih;
    }

    public String ispisivanjeVerovatnoca(Map<String, Float> map) {
        String ispis = "";
        String bolest = "";
        int brojac = 0;
        for (Entry<String, Float> entry : map.entrySet()) {
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
}
