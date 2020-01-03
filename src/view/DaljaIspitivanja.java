package view;

import Actions.DaljaIspitivanjaProlog;
import model.Osoba;

import javax.swing.*;

public class DaljaIspitivanja {
    private JFrame mainFrame = new JFrame("Ophthalmology");

    public DaljaIspitivanja(Osoba o, String bolest1, String bolest2, String bolest3) {


        DaljaIspitivanjaProlog dp = new DaljaIspitivanjaProlog(o, bolest1, bolest2, bolest3);
        mainFrame.add(dp.preporucenaDaljaIsp());


        mainFrame.setSize(800, 600);
        mainFrame.setLocationRelativeTo(null);
        //mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }
}
