package view;

import Actions.RDFParser;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class PreventiveCBR {

    public void drawFrame(){

        JLabel labelHeadline = new JLabel("High risk for following diseases based on patient's disease history, gender,age or race");
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        labelHeadline.setBorder(border);
        JFrame daljaIspitivanjaFrameCBR = new JFrame("Preventive tests");
        JPanel daljaIspitivanjaPanelCBR = new JPanel();
        daljaIspitivanjaFrameCBR.revalidate();
        daljaIspitivanjaFrameCBR.repaint();
        daljaIspitivanjaPanelCBR.setVisible(true);
        daljaIspitivanjaPanelCBR.setPreferredSize(new Dimension(900, 750));
        daljaIspitivanjaPanelCBR.setLayout(new BoxLayout(daljaIspitivanjaPanelCBR, BoxLayout.Y_AXIS));
        daljaIspitivanjaPanelCBR.add(labelHeadline);
        daljaIspitivanjaPanelCBR.add(Box.createVerticalStrut(10));
        JButton buttonConfirmation = new JButton("Check again");
        buttonConfirmation.setBorder(border);
        buttonConfirmation.setBackground(Color.DARK_GRAY);
        buttonConfirmation.setForeground(Color.WHITE);
        //          daljaIspitivanjaPanelCBR.add(buttonConfirmation);
        //        daljaIspitivanjaPanelCBR.add(buttonConfirmation);
        daljaIspitivanjaFrameCBR.add(daljaIspitivanjaPanelCBR);
        daljaIspitivanjaFrameCBR.setPreferredSize(new Dimension(900, 750));
        daljaIspitivanjaFrameCBR.setLocationRelativeTo(null);
        daljaIspitivanjaFrameCBR.setVisible(true);
        daljaIspitivanjaFrameCBR.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        daljaIspitivanjaFrameCBR.pack();

        int counter = 1;
        for (Map.Entry<String,String> entry : RDFParser.diseasesAndTests.entrySet()) {
            JLabel lab = new JLabel(entry.getKey());
            lab.setText(counter+". "+entry.getKey().substring(0, 1).toUpperCase() + entry.getKey().substring(1).replace("_"," "));
            JTextArea textT = new JTextArea(entry.getValue());
            StringBuilder content = new StringBuilder();
            String []niz = entry.getValue().split("\\.");
            for(String s : niz){
                content.append(s).append("\n");
            }
            textT.setText(content.toString());
            textT.setLineWrap(true);
            textT.setEnabled(false);
            JScrollPane pane = new JScrollPane(textT);
            pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            daljaIspitivanjaPanelCBR.add(lab);
            daljaIspitivanjaPanelCBR.add(pane);
            daljaIspitivanjaPanelCBR.add(Box.createVerticalStrut(10));
            counter++;
        }
        RDFParser.diseasesAndTests = new HashMap<>();
        JButton doneP = new JButton("Done");
        doneP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                daljaIspitivanjaFrameCBR.dispose();
            }
        });
        daljaIspitivanjaPanelCBR.add(doneP);



    }
}
