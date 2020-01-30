package view;

import Actions.DaljaIspitivanjaProlog;
import model.Osoba;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Map;

public class FurtherTesting {
    private JFrame mainFrame = new JFrame("Ophthalmology");

    public FurtherTesting(Osoba o, Map<String, Float> sortedMapRBR, String jmbg, String symptoms, ArrayList<String> symptomsArray) {

        DaljaIspitivanjaProlog dp = new DaljaIspitivanjaProlog(o, sortedMapRBR, symptomsArray, jmbg, symptoms);
        mainFrame.add(dp.preporucenaDaljaIsp());
        mainFrame.setSize(950, 700);
        mainFrame.setLocationRelativeTo(null);
        //mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }
}
