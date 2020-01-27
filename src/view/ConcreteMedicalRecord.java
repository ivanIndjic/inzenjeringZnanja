package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConcreteMedicalRecord {
    public JFrame mainF = new JFrame("Medical record");

    public ConcreteMedicalRecord(String symptoms, String treatment, String doctor, String date) {
        JPanel main = new JPanel();
        ImageIcon water = new ImageIcon("./eyeIcon.png");
        Image image = water.getImage(); // transform it
        Image newimg = image.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        water = new ImageIcon(newimg);
        JButton canc = new JButton("Exit",water);
        JPanel pan = new JPanel();
        pan.setLayout(new FlowLayout());
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout());
        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout());
        JPanel p3 = new JPanel();
        p3.setLayout(new FlowLayout());
        JPanel p4 = new JPanel();
        p4.setLayout(new FlowLayout());
        JPanel p5 = new JPanel();
        p5.setLayout(new FlowLayout());
        JLabel nb = new JLabel("Symptoms:             ");
        JTextArea nbT = new JTextArea();
        nbT.setText(symptoms);
        nbT.setEditable(false);
        nbT.setPreferredSize(new Dimension(400, 50));

        JLabel tr = new JLabel("Tretment:                ");
        JTextArea trT = new JTextArea();
        trT.setPreferredSize(new Dimension(400, 50));
        trT.setText(treatment);
        trT.setEditable(false);
        JLabel dn = new JLabel("Additional notes:      ");
        JTextArea dnT = new JTextArea();
        dnT.setPreferredSize(new Dimension(400, 250));
        dnT.setEditable(false);
        //////////////ovde iz onog selektovanog sa reload probabilities!!!!!!!!!!!!!!!!
        JLabel dok = new JLabel("Doctor:                    ");
        JTextArea doktor = new JTextArea(doctor);
        doktor.setEditable(false);
        JLabel datum = new JLabel("Date");
        JTextArea datumT = new JTextArea(date);
        datumT.setEditable(false);
        p5.add(datum);
        p5.add(datumT);
        p1.add(nb);
        p1.add(nbT);
        p2.add(tr);
        p2.add(trT);
        p3.add(dn);
        p3.add(dnT);
        p4.add(dok);
        p4.add(doktor);

        JLabel lab1 = new JLabel("                  ");
        JLabel lab2 = new JLabel("                   ");
        pan.add(lab1);
        pan.add(canc);

        main.add(lab1);
        main.add(lab2);

        canc.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                mainF.dispose();
            }
        });

        main.add(p5);
        main.add(p1);
        main.add(p2);
        main.add(p3);
        main.add(p4);
        main.add(pan);
        mainF.setSize(800, 560);
        mainF.add(main);
        mainF.setVisible(true);
    }
}
