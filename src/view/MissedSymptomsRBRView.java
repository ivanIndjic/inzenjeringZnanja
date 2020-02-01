package view;

import model.Osoba;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MissedSymptomsRBRView {
    private JFrame mainFrame = new JFrame("Ophthalmology");

    public MissedSymptomsRBRView(ArrayList<String> missedSymptoms, ArrayList<String> selectedSymptoms, Osoba o, String jmbg) {
        if (missedSymptoms.size() != 0) {
            JPanel pitanjePan = new JPanel();
            pitanjePan.setLayout(new BoxLayout(pitanjePan, BoxLayout.Y_AXIS));
            JLabel pitanje = new JLabel("Did you miss some of those symptoms? ");
            pitanjePan.add(pitanje);
            int it = 0;
            JCheckBox[] checkBoxList = new JCheckBox[missedSymptoms.size()];

            for (int i = 0; i < missedSymptoms.size(); i++) {
                checkBoxList[i] = new JCheckBox(missedSymptoms.get(i));
                checkBoxList[i].setName(missedSymptoms.get(i));
                pitanjePan.add(checkBoxList[i]);
            }

            ImageIcon donIm = new ImageIcon("./questionmark.png");
            Image doneImg = donIm.getImage(); // transform it
            Image newDoneImg = doneImg.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            donIm = new ImageIcon(newDoneImg);
            JButton done = new JButton("Done", donIm);
            done.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    for (int i = 0; i < missedSymptoms.size(); i++) {
                        if (checkBoxList[i].isSelected()) {
                            String selected = checkBoxList[i].getName().replaceAll(" ", "_");
                            selectedSymptoms.add(selected);
                        }
                    }
                    System.out.println(selectedSymptoms);
                    SelectSymptoms.selected = new ArrayList<>();
                    SelectSymptoms.selected = selectedSymptoms;
                    SelectSymptoms se = new SelectSymptoms(o, jmbg);
                    mainFrame.dispose();
                }
            });
            pitanjePan.add(done);
            mainFrame.add(pitanjePan);
        }
        mainFrame.setSize(400, 500);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }
}
