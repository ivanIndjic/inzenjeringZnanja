package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class EditUser {

    private JFrame mainF = new JFrame("Oftalmology");

    public EditUser(Vector<String> v) throws IOException {
        JPanel levi = new JPanel();

        JButton odustani = new JButton("Cancel");
        JButton sacuvaj = new JButton("Save");
        JButton novo = new JButton("History");
        JPanel dug = new JPanel();
        dug.setLayout(new FlowLayout());

        dug.add(novo);
        dug.add(odustani);
        dug.add(sacuvaj);
        JPanel dugmici = new JPanel();
        dugmici.setLayout(new BorderLayout());
        dugmici.add(dug, BorderLayout.EAST);

        levi.setLayout(new BoxLayout(levi, BoxLayout.Y_AXIS));
        JPanel panel = new JPanel(new BorderLayout());
        BufferedImage myPicture = ImageIO.read(new File("resources/us.jpg"));
        JPanel zasliku = new JPanel();
        zasliku.setLayout(new BoxLayout(zasliku, BoxLayout.Y_AXIS));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture.getScaledInstance(200, 200, 1)));
        panel.add(dugmici, BorderLayout.SOUTH);
        panel.add(zasliku, BorderLayout.EAST);
        JLabel labela = new JLabel("                                                                                Patient's card"
                + "                                            "
                + "                                            "
                + "                                              "
                + "                                               "
                + "                                                 "
                + "                                                "
                + ""
                + ""
                + "");
        labela.setBorder(new LineBorder(Color.DARK_GRAY));
        panel.add(labela, BorderLayout.NORTH);
        //----------------------------------------------//
        JLabel ime = new JLabel("Name:      ");
        JTextField imeT = new JTextField();
        imeT.setText(v.get(0));
        imeT.setSize(20, 50);
        imeT.setPreferredSize(new Dimension(100, 20));
        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout());
        p1.add(ime);
        p1.add(imeT);
        //*********************************************//
        JLabel prezime = new JLabel("Last name:");
        JTextField prezimeT = new JTextField();
        prezimeT.setPreferredSize(new Dimension(100, 20));
        prezimeT.setText(v.get(1));
        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout());
        p2.add(prezime);
        p2.add(prezimeT);
        //*********************************************//
        JLabel adresa = new JLabel("Address:  ");
        JTextField adresaT = new JTextField();
        adresaT.setText(v.get(3));
        adresaT.setPreferredSize(new Dimension(100, 20));
        JPanel p3 = new JPanel();
        p3.setLayout(new FlowLayout());
        p3.add(adresa);
        p3.add(adresaT);
        //*********************************************//
        JLabel jmbg = new JLabel("JMBG:   ");
        JTextField jmbgT = new JTextField();
        jmbgT.setEditable(false);
        jmbgT.setText(v.get(7));
        jmbgT.setPreferredSize(new Dimension(100, 20));
        JPanel p4 = new JPanel();
        p4.setLayout(new FlowLayout());
        p4.add(jmbg);
        p4.add(jmbgT);
        //*********************************************//
        JLabel il = new JLabel("Subscribed doctor:");
        String[] dok = {"Nema", "Srdjan", "Milica", "Ivan", "Nina"};
        JComboBox doktori = new JComboBox(dok);
        doktori.setSelectedItem((String) v.get(6));
        JPanel p5 = new JPanel();
        p5.setLayout(new FlowLayout());
        p5.add(il);
        p5.add(doktori);
        //*********************************************//
        JLabel tel = new JLabel("Phone:   ");
        JTextField telT = new JTextField();
        telT.setText(v.get(4));
        telT.setPreferredSize(new Dimension(100, 20));
        JPanel p6 = new JPanel();
        p6.setLayout(new FlowLayout());
        p6.add(tel);
        p6.add(telT);
        //*********************************************//
        JLabel mail = new JLabel("E-mail:    ");
        JTextField mailT = new JTextField();
        mailT.setText(v.get(5));
        mailT.setPreferredSize(new Dimension(100, 20));
        JPanel p7 = new JPanel();
        p7.setLayout(new FlowLayout());
        p7.add(mail);
        p7.add(mailT);
        //*********************************************//
        JLabel god = new JLabel("Age:");
        ArrayList<String> years_tmp = new ArrayList<String>();
        for (int years = 1; years < 110; years++) {
            years_tmp.add(years + "");
        }
        JComboBox jComboBox1 = new JComboBox(years_tmp.toArray());
        jComboBox1.setSelectedItem((String) v.get(2));
        JPanel p8 = new JPanel();
        p8.setLayout(new FlowLayout());
        p8.add(god);
        p8.add(jComboBox1);
        //*********************************************//
        JLabel rasa = new JLabel("Race:");
        JRadioButton b1 = new JRadioButton("Black");
        JRadioButton b2 = new JRadioButton("White");
        JRadioButton b3 = new JRadioButton("Hispanic");
        JRadioButton b4 = new JRadioButton("Other");
        ButtonGroup bg = new ButtonGroup();
        bg.add(b1);
        bg.add(b2);
        bg.add(b3);
        bg.add(b4);
        if (v.get(9).equals("Black"))
            b1.setSelected(true);
        if (v.get(9).equals("White"))
            b2.setSelected(true);
        if (v.get(9).equals("Hispanic"))
            b3.setSelected(true);
        if (v.get(9).equals("Other"))
            b4.setSelected(true);

        JPanel p9 = new JPanel();
        p9.setLayout(new FlowLayout());
        p9.add(rasa);
        p9.add(b1);
        p9.add(b2);
        p9.add(b3);
        p9.add(b4);
        //*********************************************//
        JLabel pol = new JLabel("Gender:");
        JRadioButton b11 = new JRadioButton("Male");
        JRadioButton b22 = new JRadioButton("Female");
        ButtonGroup bg2 = new ButtonGroup();
        bg2.add(b11);
        bg2.add(b22);
        if (v.get(8).equals("Male"))
            b11.setSelected(true);
        if (v.get(8).equals("Female"))
            b22.setSelected(true);

        JPanel p10 = new JPanel();
        p10.setLayout(new FlowLayout());
        p10.add(pol);
        p10.add(b11);
        p10.add(b22);

        sacuvaj.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if (jmbgT.getText().equals("") || (!b1.isSelected() && !b2.isSelected() && !b3.isSelected() && !b4.isSelected()) || (!b11.isSelected() && !b22.isSelected())) {
                    JOptionPane.showMessageDialog(null, "JMBG, Race and Gender must not be empty");
                    jmbgT.setBackground(Color.RED);
                } else {
                    try {
                        //otvaranje konekcije
                        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inzenjering?useSSL=false", "root", "password");

                        String rasa = "White";
                        String pol = "Male";
                        if (b1.isSelected())
                            rasa = "Black";
                        if (b2.isSelected())
                            rasa = "White";
                        if (b3.isSelected())
                            rasa = "Hispanic";
                        if (b4.isSelected())
                            rasa = "Other";
                        if (b11.isSelected())
                            pol = "Male";
                        if (b22.isSelected())
                            pol = "Female";
                        String sql = "update Karton set Ime='" + imeT.getText() + "',Prezime='" + prezimeT.getText() + "',Godina='" + (String) jComboBox1.getSelectedItem() + "',Adresa='" + adresaT.getText() + "',Telefon='" + telT.getText() + "',Mail='" + mailT.getText() + "',zaduzeniLekar='" + (String) doktori.getSelectedItem() + "',Pol='" + pol + "',Rasa='" + rasa + "' where JMBG='" + jmbgT.getText() + "'";
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        int updated = pstmt.executeUpdate();

                        pstmt.close();

                        mainF.dispose();

                    } catch (Exception ee) {
                        ee.printStackTrace();
                    }
                    try {
                        MainFrame mf = new MainFrame();
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

                }
            }

        });


        novo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                IstorijaPregledaView iw = new IstorijaPregledaView();
                try {
                    iw.nacrtaj(jmbgT.getText());
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        odustani.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                try {
                    MainFrame mf = new MainFrame();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                mainF.dispose();

            }
        });

        levi.add(p1);
        levi.add(p2);
        levi.add(p3);
        levi.add(p4);
        levi.add(p5);
        levi.add(p6);
        levi.add(p7);
        levi.add(p8);
        levi.add(p9);
        levi.add(p10);
        panel.add(levi, BorderLayout.WEST);

        mainF.setSize(800, 600);
        mainF.setLocationRelativeTo(null);
        mainF.add(panel);
        mainF.setVisible(true);
    }

}
