package view;

import Actions.DaljaIspitivanjaProlog;
import model.Osoba;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

public class FurtherTesting {
    private JFrame mainFrame = new JFrame("Ophthalmology");

    public FurtherTesting(Osoba o, Map<String, Float> sortedMapRBR, String jmbg, String symptoms) {

        DaljaIspitivanjaProlog dp = new DaljaIspitivanjaProlog(o, sortedMapRBR);
        mainFrame.add(dp.preporucenaDaljaIsp());

        ImageIcon water = new ImageIcon("./eyeIcon.png");
        Image image = water.getImage(); // transform it
        Image newimg = image.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        water = new ImageIcon(newimg);
        JButton back = new JButton("Back", water);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mainFrame.dispose();
                SelectSymptoms selectSymptoms = new SelectSymptoms(o, jmbg);
            }
        });

        JButton done = new JButton("Done");
        done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddData addDataFrame = new AddData(symptoms, jmbg);
                mainFrame.dispose();
            }
        });
        mainFrame.add(back, BorderLayout.SOUTH);
        mainFrame.add(done,BorderLayout.SOUTH);
        mainFrame.setSize(800, 600);
        mainFrame.setLocationRelativeTo(null);
        //mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }
}
