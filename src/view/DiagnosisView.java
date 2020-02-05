package view;

import Actions.MedicationsProlog;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DiagnosisView {
    public ArrayList<String> notes = new ArrayList<>();

    public DiagnosisView(String disease,String navedeniSimptomi,String jmbg) {
        JFrame mainF = new JFrame(disease);
        JPanel main = new JPanel();
        if (disease.equals("conjunctivitis")) {
            main = getConj();
        } else if (disease.equals("dry eye of unknown cause")) {
            main = getDryEye();
        } else if (disease.equals("cataract")) {
            main = getCataract();
        } else if (disease.equals("blepharitis")) {
            main = getBle();
        } else if (disease.equals("chronic glaucoma")) {
            main = getChro();
        } else if (disease.equals("macular degeneration")) {
            main = getMac();
        } else if (disease.equals("corneal abrasion")) {
            main = getAbr();
        } else if (disease.equals("cornea infection")) {
            main = getInf();
        } else if (disease.equals("retinal detachment")) {
            main = getRet();
        } else if (disease.equals("optic neuritis")) {
            main = getOpt();
        } else if (disease.equals("iridocyclitis")) {
            main = getIri();
        } else if (disease.equals("subconjunctival hemorrhage")) {
            main = getSub();
        } else if (disease.equals("floaters")) {
            main = getFlo();
        } else if (disease.equals("eye alignment disorder")) {
            main = getEye();
        }
        JPanel main2=new JPanel();
        main2.setLayout(new BorderLayout());
        main2.add(main,BorderLayout.NORTH);
        ImageIcon donIm = new ImageIcon("./done.png");
        Image doneImg = donIm.getImage(); // transform it
        Image newDoneImg = doneImg.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        donIm = new ImageIcon(newDoneImg);
        JButton done = new JButton("DoneRBR", donIm);

        done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
               AddData ad=new AddData(navedeniSimptomi,jmbg,disease,"",null);
               mainF.dispose();
            }
        });
        main2.add(done,BorderLayout.SOUTH);
        mainF.setSize(800, 560);
        mainF.add(main2);
        mainF.setVisible(true);
    }

    public JPanel getConj() {
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());
        JPanel pitanjePan = new JPanel();
        pitanjePan.setLayout(new BoxLayout(pitanjePan, BoxLayout.Y_AXIS));
        JLabel pitanje = new JLabel(" Does your patient have? ");
        //Moxifloxacin
        JCheckBox a = new JCheckBox("arthritis");
        JCheckBox b = new JCheckBox("blood circulation problems");
        JCheckBox c = new JCheckBox("heart problems");
        JCheckBox d = new JCheckBox("high blood pressure");
        JCheckBox e = new JCheckBox("diabetes");
        JCheckBox f = new JCheckBox("liver or kidney disease");
        JCheckBox g = new JCheckBox("seizures or epilepsy");
        JCheckBox h = new JCheckBox("head injury");
        //Sulfacetamide ophthalmic
        JCheckBox i = new JCheckBox("astma");
        //erythromycin
        JCheckBox k = new JCheckBox("heart rhythm disorder");
        JCheckBox l = new JCheckBox("low levels of potassium or magnesium");
        JCheckBox m = new JCheckBox("pregnancy");
        //gentamicin --pregnancy
        //ciprofloxacin --astma
        //ofloxacin --arthritis, heart problem, high blood pressure, low levels of potassium
        JCheckBox o = new JCheckBox("bone problems");
        JCheckBox p = new JCheckBox("muscle or nerve disorder");
        ImageIcon done = new ImageIcon("./medicament.jpeg");
        Image doneIm = done.getImage(); // transform it
        Image doneimg = doneIm.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        done = new ImageIcon(doneimg);
        JButton doneB = new JButton("Medicaments", done);
        JPanel right = new JPanel();
        JTextArea prolog = new JTextArea();
        prolog.setVisible(false);
        Border blackline2 = BorderFactory.createLineBorder(Color.BLACK);
        TitledBorder title2 = BorderFactory.createTitledBorder(blackline2, "Possible medications are:");
        title2.setTitleJustification(TitledBorder.CENTER);

        prolog.setBorder(title2);
        prolog.setSize(new Dimension(280, 200));
        right.add(prolog);
        doneB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                prolog.setText("");
                notes = new ArrayList<>();
                if (a.isSelected()) {
                    notes.add("arthritis");
                }
                if (b.isSelected()) {
                    notes.add("blood_circulation_problems");
                }
                if (c.isSelected()) {
                    notes.add("heart_problems");
                }
                if (d.isSelected()) {
                    notes.add("high_blood_pressure");
                }
                if (e.isSelected()) {
                    notes.add("diabetes");
                }
                if (f.isSelected()) {
                    notes.add("liver_or_kidney_disease");
                }
                if (g.isSelected()) {
                    notes.add("seizures_or_epilepsy");
                }
                if (h.isSelected()) {
                    notes.add("head_injury");
                }
                if (i.isSelected()) {
                    notes.add("astma");
                }
                if (k.isSelected()) {
                    notes.add("heart_rhythm_disorder");
                }
                if (l.isSelected()) {
                    notes.add("low_levels_of_potassium_or_magnesium");
                }
                if (m.isSelected()) {
                    notes.add("pregnancy");
                }
                if (o.isSelected()) {
                    notes.add("bone_problems");
                }
                if (p.isSelected()) {
                    notes.add("muscle_or_nerve_disorder");
                }
                MedicationsProlog mp = new MedicationsProlog();
                ArrayList<String> medicaments = mp.prolog("conjunctivitis", notes);
                String med = "     Possible medications are:     \n";
                int brojac = 1;
                for (String isp : medicaments) {
                    isp = isp.substring(0, 1).toUpperCase() + isp.substring(1);
                    isp = "        " + brojac + ". " + isp.replaceAll("_", " ");
                    med += isp + "\n";
                    brojac++;
                }
                prolog.setText(med);
                prolog.setVisible(true);
                main.revalidate();
            }
        });

        JButton end = new JButton();
        pitanjePan.add(pitanje);
        pitanjePan.add(a);
        pitanjePan.add(b);
        pitanjePan.add(c);
        pitanjePan.add(d);
        pitanjePan.add(m);
        pitanjePan.add(l);
        pitanjePan.add(k);
        pitanjePan.add(i);
        pitanjePan.add(h);
        pitanjePan.add(g);
        pitanjePan.add(f);
        pitanjePan.add(e);
        pitanjePan.add(o);
        pitanjePan.add(p);
        pitanjePan.add(doneB);
        right.setSize(new Dimension(300, 450));
        right.setPreferredSize(new Dimension(300, 450));
        main.add(pitanjePan, BorderLayout.WEST);
        main.add(right, BorderLayout.EAST);
        return main;
    }

    public JPanel getDryEye() {
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());
        JPanel pitanjePan = new JPanel();
        pitanjePan.setLayout(new BoxLayout(pitanjePan, BoxLayout.Y_AXIS));
        JLabel pitanje = new JLabel(" Does your patient have? ");
        //cyclosporine ophthalmic
        //1 drop 2 times a day in each eye approximately 12 hours apart
        JCheckBox a = new JCheckBox("pregnancy");
        JCheckBox b = new JCheckBox("astma");
        //latanoprost --astma
        //One eye drop in the affected eye(s) once a day in the evening
        JCheckBox c = new JCheckBox("been using other medications lately");
        JCheckBox d = new JCheckBox("eye surgery");
        //loteprednol --pregnancy
        JCheckBox e = new JCheckBox("eye infection");
        //phenylephrine
        JCheckBox f = new JCheckBox("heart problems");
        JCheckBox g = new JCheckBox("high blood pressure");
        JCheckBox h = new JCheckBox("diabetes");
        JCheckBox i = new JCheckBox("enlarged prostate or urination problems");
        //travoprost --eye surgery
        JCheckBox k = new JCheckBox("swelling or an infection in your eye");
        //timolol --diabetes
        JCheckBox l = new JCheckBox("sleep apnea");
        JCheckBox m = new JCheckBox("liver or kidney disease");
        JCheckBox o = new JCheckBox("overactive thyroid");
        ImageIcon done = new ImageIcon("./medicament.jpeg");
        Image doneIm = done.getImage(); // transform it
        Image doneimg = doneIm.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        done = new ImageIcon(doneimg);
        JButton doneB = new JButton("Medicaments", done);
        JPanel right = new JPanel();
        JTextArea prolog = new JTextArea();
        prolog.setVisible(false);
        Border blackline2 = BorderFactory.createLineBorder(Color.BLACK);
        TitledBorder title2 = BorderFactory.createTitledBorder(blackline2, "Possible medications are:");
        title2.setTitleJustification(TitledBorder.CENTER);
        prolog.setBorder(title2);
        prolog.setSize(new Dimension(280, 200));
        right.add(prolog);
        doneB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                prolog.setText("");
                notes = new ArrayList<>();
                if (a.isSelected()) {
                    notes.add("pregnancy");
                }
                if (b.isSelected()) {
                    notes.add("astma");
                }
                if (c.isSelected()) {
                    notes.add("been_using_other_medications_lately");
                }
                if (d.isSelected()) {
                    notes.add("eye_surgery");
                }
                if (e.isSelected()) {
                    notes.add("eye_infection");
                }
                if (f.isSelected()) {
                    notes.add("heart_problems");
                }
                if (g.isSelected()) {
                    notes.add("high_blood_pressure");
                }
                if (h.isSelected()) {
                    notes.add("diabetes");
                }
                if (i.isSelected()) {
                    notes.add("enlarged_prostate_or_urination_problems");
                }
                if (k.isSelected()) {
                    notes.add("swelling_or_an_infection_in_your_eye");
                }
                if (l.isSelected()) {
                    notes.add("sleep_apnea");
                }
                if (m.isSelected()) {
                    notes.add("liver_or_kidney_disease");
                }
                if (o.isSelected()) {
                    notes.add("overactive_thyroid");
                }
                MedicationsProlog mp = new MedicationsProlog();
                ArrayList<String> medicaments = mp.prolog("dry_eye_of_unknown_cause", notes);
                String med = "    Possible medications are:    \n";
                int brojac = 1;
                for (String isp : medicaments) {
                    isp = isp.substring(0, 1).toUpperCase() + isp.substring(1);
                    isp = "        " + brojac + ". " + isp.replaceAll("_", " ");
                    med += isp + "\n";
                    brojac++;
                }
                prolog.setText(med);
                prolog.setVisible(true);
                main.revalidate();
            }
        });
        pitanjePan.add(pitanje);
        pitanjePan.add(a);
        pitanjePan.add(b);
        pitanjePan.add(c);
        pitanjePan.add(d);
        pitanjePan.add(m);
        pitanjePan.add(l);
        pitanjePan.add(k);
        pitanjePan.add(i);
        pitanjePan.add(h);
        pitanjePan.add(g);
        pitanjePan.add(f);
        pitanjePan.add(e);
        pitanjePan.add(o);
        pitanjePan.add(doneB);
        right.setSize(new Dimension(300, 450));
        right.setPreferredSize(new Dimension(300, 450));
        main.add(pitanjePan, BorderLayout.WEST);
        main.add(right, BorderLayout.EAST);
        return main;
    }

    public JPanel getCataract() {
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());
        JPanel pitanjePan = new JPanel();
        pitanjePan.setLayout(new BoxLayout(pitanjePan, BoxLayout.Y_AXIS));
        JLabel pitanje = new JLabel(" Does your patient have? ");
        //Moxifloxacin
        JCheckBox a = new JCheckBox("arthritis");
        JCheckBox b = new JCheckBox("blood circulation problems");
        JCheckBox c = new JCheckBox("heart problems");
        JCheckBox d = new JCheckBox("high blood pressure");
        JCheckBox e = new JCheckBox("diabetes");
        JCheckBox f = new JCheckBox("liver or kidney disease");
        JCheckBox g = new JCheckBox("seizures or epilepsy");
        JCheckBox h = new JCheckBox("head injury");
        //latanoprost --astma
        //One eye drop in the affected eye(s) once a day in the evening
        JCheckBox i = new JCheckBox("been using other medications lately");
        JCheckBox j = new JCheckBox("eye surgery");
        //travoprost --eye surgery
        JCheckBox k = new JCheckBox("swelling or an infection in your eye");
        //brimonidine
        JCheckBox l = new JCheckBox("depression");
        JCheckBox m = new JCheckBox("low blood pressure");
        JCheckBox n = new JCheckBox("clogged arteries");
        //nepafenac --arthritis --diabetes --clogged arteries
        //phenylephrine --diabetes --heart problems --high blood pressure
        JCheckBox o = new JCheckBox("enlarged prostate or urination problems");

        ImageIcon done = new ImageIcon("./medicament.jpeg");
        Image doneIm = done.getImage(); // transform it
        Image doneimg = doneIm.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        done = new ImageIcon(doneimg);
        JButton doneB = new JButton("Medicaments", done);
        JPanel right = new JPanel();
        JTextArea prolog = new JTextArea();
        prolog.setVisible(false);
        Border blackline2 = BorderFactory.createLineBorder(Color.BLACK);
        TitledBorder title2 = BorderFactory.createTitledBorder(blackline2, "Possible medications are:");
        title2.setTitleJustification(TitledBorder.CENTER);
        prolog.setBorder(title2);
        prolog.setSize(new Dimension(280, 200));
        right.add(prolog);
        doneB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                prolog.setText("");
                notes = new ArrayList<>();
                if (a.isSelected()) {
                    notes.add("arthritis");
                }
                if (b.isSelected()) {
                    notes.add("blood_circulation_problems");
                }
                if (c.isSelected()) {
                    notes.add("heart_problems");
                }
                if (d.isSelected()) {
                    notes.add("high_blood_pressure");
                }
                if (e.isSelected()) {
                    notes.add("diabetes");
                }
                if (f.isSelected()) {
                    notes.add("liver_or_kidney_disease");
                }
                if (g.isSelected()) {
                    notes.add("seizures_or_epilepsy");
                }
                if (h.isSelected()) {
                    notes.add("head_injury");
                }
                if (i.isSelected()) {
                    notes.add("been_using_other_medications_lately");
                }
                if (j.isSelected()) {
                    notes.add("eye_surgery");
                }
                if (k.isSelected()) {
                    notes.add("swelling_or_an_infection_in_your_eye");
                }
                if (l.isSelected()) {
                    notes.add("depression");
                }
                if (m.isSelected()) {
                    notes.add("low_blood_pressure");
                }
                if (o.isSelected()) {
                    notes.add("enlarged_prostate_or_urination_problems");
                }
                if (n.isSelected()) {
                    notes.add("clogged_arteries");
                }
                MedicationsProlog mp = new MedicationsProlog();
                ArrayList<String> medicaments = mp.prolog("cataract", notes);
                String med = "     Possible medications are:     \n";
                int brojac = 1;
                for (String isp : medicaments) {
                    isp = isp.substring(0, 1).toUpperCase() + isp.substring(1);
                    isp = "        " + brojac + ". " + isp.replaceAll("_", " ");
                    med += isp + "\n";
                    brojac++;
                }
                prolog.setText(med);
                prolog.setVisible(true);
                main.revalidate();
            }
        });
        pitanjePan.add(pitanje);
        pitanjePan.add(a);
        pitanjePan.add(b);
        pitanjePan.add(c);
        pitanjePan.add(d);
        pitanjePan.add(e);
        pitanjePan.add(f);
        pitanjePan.add(g);
        pitanjePan.add(h);
        pitanjePan.add(i);
        pitanjePan.add(j);
        pitanjePan.add(k);
        pitanjePan.add(l);
        pitanjePan.add(m);
        pitanjePan.add(n);
        pitanjePan.add(o);
        pitanjePan.add(doneB);
        right.setSize(new Dimension(300, 450));
        right.setPreferredSize(new Dimension(300, 450));
        main.add(pitanjePan, BorderLayout.WEST);
        main.add(right, BorderLayout.EAST);
        return main;
    }

    public JPanel getChro() {
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());
        JPanel pitanjePan = new JPanel();
        pitanjePan.setLayout(new BoxLayout(pitanjePan, BoxLayout.Y_AXIS));
        JLabel pitanje = new JLabel(" Does your patient have? ");
        //latanoprost
        JCheckBox a = new JCheckBox("astma");
        JCheckBox i = new JCheckBox("been using other medications lately");
        JCheckBox j = new JCheckBox("eye surgery");
        //travoprost --eye surgery
        JCheckBox k = new JCheckBox("swelling or an infection in your eye");
        //bimatoprost --eye surgery
        //brimonidine
        JCheckBox l = new JCheckBox("depression");
        JCheckBox m = new JCheckBox("low blood pressure");
        JCheckBox n = new JCheckBox("clogged arteries");
        //timolol
        JCheckBox o = new JCheckBox("sleep apnea");
        JCheckBox b = new JCheckBox("liver or kidney disease");
        JCheckBox c = new JCheckBox("overactive thyroid");
        JCheckBox d = new JCheckBox("diabetes");

        //prednisolone --depression --liver or kidney disease
        JCheckBox e = new JCheckBox("tuberculosis");
        JCheckBox f = new JCheckBox("thyroid disorder");
        JCheckBox g = new JCheckBox("mental illness");
        JCheckBox h = new JCheckBox("high blood pressure");
        JCheckBox p = new JCheckBox("multiple sclerosis");


        ImageIcon done = new ImageIcon("./medicament.jpeg");
        Image doneIm = done.getImage(); // transform it
        Image doneimg = doneIm.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        done = new ImageIcon(doneimg);
        JButton doneB = new JButton("Medicaments", done);
        JPanel right = new JPanel();
        JTextArea prolog = new JTextArea();
        prolog.setVisible(false);
        Border blackline2 = BorderFactory.createLineBorder(Color.BLACK);
        TitledBorder title2 = BorderFactory.createTitledBorder(blackline2, "Possible medications are:");
        title2.setTitleJustification(TitledBorder.CENTER);
        prolog.setBorder(title2);
        prolog.setSize(new Dimension(280, 200));
        right.add(prolog);
        doneB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                prolog.setText("");
                notes = new ArrayList<>();
                if (a.isSelected()) {
                    notes.add("astma");
                }
                if (b.isSelected()) {
                    notes.add("liver_or_kidney_disease");
                }
                if (c.isSelected()) {
                    notes.add("overactive_thyroid");
                }
                if (d.isSelected()) {
                    notes.add("diabetes");
                }
                if (e.isSelected()) {
                    notes.add("tuberculosis");
                }
                if (f.isSelected()) {
                    notes.add("thyroid_disorder");
                }
                if (g.isSelected()) {
                    notes.add("mental_illness");
                }
                if (h.isSelected()) {
                    notes.add("high_blood_pressure");
                }
                if (p.isSelected()) {
                    notes.add("multiple_sclerosis");
                }
                if (i.isSelected()) {
                    notes.add("been_using_other_medications_lately");
                }
                if (j.isSelected()) {
                    notes.add("eye_surgery");
                }
                if (k.isSelected()) {
                    notes.add("swelling_or_an_infection_in_your_eye");
                }
                if (l.isSelected()) {
                    notes.add("depression");
                }
                if (m.isSelected()) {
                    notes.add("low_blood_pressure");
                }
                if (o.isSelected()) {
                    notes.add("sleep_apnea");
                }
                if (n.isSelected()) {
                    notes.add("clogged_arteries");
                }
                MedicationsProlog mp = new MedicationsProlog();
                ArrayList<String> medicaments = mp.prolog("chronic_glaucoma", notes);
                String med = "     Possible medications are:     \n";
                int brojac = 1;
                for (String isp : medicaments) {
                    isp = isp.substring(0, 1).toUpperCase() + isp.substring(1);
                    isp = "        " + brojac + ". " + isp.replaceAll("_", " ");
                    med += isp + "\n";
                    brojac++;
                }
                prolog.setText(med);
                prolog.setVisible(true);
                main.revalidate();
            }
        });
        pitanjePan.add(pitanje);
        pitanjePan.add(a);
        pitanjePan.add(b);
        pitanjePan.add(c);
        pitanjePan.add(i);
        pitanjePan.add(j);
        pitanjePan.add(l);
        pitanjePan.add(m);
        pitanjePan.add(n);
        pitanjePan.add(o);
        pitanjePan.add(d);
        pitanjePan.add(e);
        pitanjePan.add(f);
        pitanjePan.add(g);
        pitanjePan.add(h);
        pitanjePan.add(p);
        pitanjePan.add(k);

        pitanjePan.add(doneB);
        right.setSize(new Dimension(300, 450));
        right.setPreferredSize(new Dimension(300, 450));
        main.add(pitanjePan, BorderLayout.WEST);
        main.add(right, BorderLayout.EAST);
        return main;
    }

    public JPanel getBle() {
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());
        JPanel pitanjePan = new JPanel();
        pitanjePan.setLayout(new BoxLayout(pitanjePan, BoxLayout.Y_AXIS));
        JLabel pitanje = new JLabel(" Does your patient have? ");
        //latanoprost
        JCheckBox a = new JCheckBox("astma");
        JCheckBox i = new JCheckBox("been using other medications lately");
        JCheckBox j = new JCheckBox("eye surgery");
        //phenylephrine --heart problems
        JCheckBox b = new JCheckBox("diabetes");
        JCheckBox c = new JCheckBox("high blood pressure");
        JCheckBox o = new JCheckBox("enlarged prostate or urination problems");
        //erythromycin
        JCheckBox l = new JCheckBox("liver or kidney disease");
        JCheckBox m = new JCheckBox("heart problems");
        JCheckBox n = new JCheckBox("electrolyte imbalance");
        //ocular lubricant
        //bimatoprost --eye surgery


        ImageIcon done = new ImageIcon("./medicament.jpeg");
        Image doneIm = done.getImage(); // transform it
        Image doneimg = doneIm.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        done = new ImageIcon(doneimg);
        JButton doneB = new JButton("Medicaments", done);
        JPanel right = new JPanel();
        JTextArea prolog = new JTextArea();
        prolog.setVisible(false);
        Border blackline2 = BorderFactory.createLineBorder(Color.BLACK);
        TitledBorder title2 = BorderFactory.createTitledBorder(blackline2, "Possible medications are:");
        title2.setTitleJustification(TitledBorder.CENTER);
        prolog.setBorder(title2);
        prolog.setSize(new Dimension(280, 200));
        right.add(prolog);
        doneB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                prolog.setText("");
                notes = new ArrayList<>();
                if (a.isSelected()) {
                    notes.add("astma");
                }
                if (b.isSelected()) {
                    notes.add("diabetes");
                }
                if (c.isSelected()) {
                    notes.add("high_blood_pressure");
                }
                if (i.isSelected()) {
                    notes.add("been_using_other_medications_lately");
                }
                if (j.isSelected()) {
                    notes.add("eye_surgery");
                }
                if (l.isSelected()) {
                    notes.add("liver_or_kidney_disease");
                }
                if (m.isSelected()) {
                    notes.add("heart_problems");
                }
                if (o.isSelected()) {
                    notes.add("enlarged_prostate_or_urination_problems");
                }
                if (n.isSelected()) {
                    notes.add("electrolyte_imbalance");
                }
                MedicationsProlog mp = new MedicationsProlog();
                ArrayList<String> medicaments = mp.prolog("blepharitis", notes);
                String med = "     Possible medications are:     \n";
                int brojac = 1;
                for (String isp : medicaments) {
                    isp = isp.substring(0, 1).toUpperCase() + isp.substring(1);
                    isp = "        " + brojac + ". " + isp.replaceAll("_", " ");
                    med += isp + "\n";
                    brojac++;
                }
                prolog.setText(med);
                prolog.setVisible(true);
                main.revalidate();
            }
        });
        pitanjePan.add(pitanje);
        pitanjePan.add(a);
        pitanjePan.add(b);
        pitanjePan.add(c);
        pitanjePan.add(i);
        pitanjePan.add(j);
        pitanjePan.add(l);
        pitanjePan.add(m);
        pitanjePan.add(n);
        pitanjePan.add(o);
        pitanjePan.add(doneB);
        right.setSize(new Dimension(300, 450));
        right.setPreferredSize(new Dimension(300, 450));
        main.add(pitanjePan, BorderLayout.WEST);
        main.add(right, BorderLayout.EAST);
        return main;
    }

    public JPanel getMac() {
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());
        JPanel pitanjePan = new JPanel();
        pitanjePan.setLayout(new BoxLayout(pitanjePan, BoxLayout.Y_AXIS));
        JLabel pitanje = new JLabel(" Does your patient have? ");
        //phenylephrine
        JCheckBox a = new JCheckBox("heart problems");
        JCheckBox b = new JCheckBox("diabetes");
        JCheckBox c = new JCheckBox("high blood pressure");
        JCheckBox d = new JCheckBox("enlarged prostate or urination problems");
        //prednisolone --high blood pressure
        JCheckBox e = new JCheckBox("tuberculosis");
        JCheckBox f = new JCheckBox("thyroid disorder");
        JCheckBox g = new JCheckBox("mental illness");
        JCheckBox h = new JCheckBox("depression");
        JCheckBox i = new JCheckBox("multiple sclerosis");
        JCheckBox j = new JCheckBox("liver or kidney disease");
        //latanoprost
        JCheckBox k = new JCheckBox("been using other medications lately");
        JCheckBox l = new JCheckBox("eye surgery");
        JCheckBox m = new JCheckBox("astma");
        //bevacizumab --high blood pressure
        JCheckBox n = new JCheckBox("heart attack");
        JCheckBox o = new JCheckBox("stomach or intestinal bleeding");

        ImageIcon done = new ImageIcon("./medicament.jpeg");
        Image doneIm = done.getImage(); // transform it
        Image doneimg = doneIm.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        done = new ImageIcon(doneimg);
        JButton doneB = new JButton("Medicaments", done);
        JPanel right = new JPanel();
        JTextArea prolog = new JTextArea();
        prolog.setVisible(false);
        Border blackline2 = BorderFactory.createLineBorder(Color.BLACK);
        TitledBorder title2 = BorderFactory.createTitledBorder(blackline2, "Possible medications are:");
        title2.setTitleJustification(TitledBorder.CENTER);
        prolog.setBorder(title2);
        prolog.setSize(new Dimension(280, 200));
        right.add(prolog);
        doneB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                prolog.setText("");
                notes = new ArrayList<>();
                if (a.isSelected()) {
                    notes.add("heart_problems");
                }
                if (b.isSelected()) {
                    notes.add("diabetes");
                }
                if (c.isSelected()) {
                    notes.add("high_blood_pressure");
                }
                if (d.isSelected()) {
                    notes.add("enlarged_prostate_or_urination_problems");
                }
                if (e.isSelected()) {
                    notes.add("tuberculosis");
                }
                if (f.isSelected()) {
                    notes.add("thyroid_disorder");
                }
                if (g.isSelected()) {
                    notes.add("mental_illness");
                }
                if (h.isSelected()) {
                    notes.add("depression");
                }
                if (i.isSelected()) {
                    notes.add("multiple_sclerosis");
                }
                if (j.isSelected()) {
                    notes.add("liver_or_kidney_disease");
                }
                if (k.isSelected()) {
                    notes.add("been_using_other_medications_lately");
                }
                if (l.isSelected()) {
                    notes.add("eye_surgery");
                }
                if (m.isSelected()) {
                    notes.add("astma");
                }
                if (n.isSelected()) {
                    notes.add("heart_attack");
                }
                if (o.isSelected()) {
                    notes.add("stomach_or_intestinal_bleeding");
                }

                MedicationsProlog mp = new MedicationsProlog();
                ArrayList<String> medicaments = mp.prolog("macular_degeneration", notes);
                String med = "     Possible medications are:     \n";
                int brojac = 1;
                for (String isp : medicaments) {
                    isp = isp.substring(0, 1).toUpperCase() + isp.substring(1);
                    isp = "        " + brojac + ". " + isp.replaceAll("_", " ");
                    med += isp + "\n";
                    brojac++;
                }
                prolog.setText(med);
                prolog.setVisible(true);
                main.revalidate();
            }
        });
        pitanjePan.add(pitanje);
        pitanjePan.add(a);
        pitanjePan.add(b);
        pitanjePan.add(c);
        pitanjePan.add(d);
        pitanjePan.add(e);
        pitanjePan.add(f);
        pitanjePan.add(g);
        pitanjePan.add(h);
        pitanjePan.add(i);
        pitanjePan.add(j);
        pitanjePan.add(k);
        pitanjePan.add(l);
        pitanjePan.add(m);
        pitanjePan.add(n);
        pitanjePan.add(o);
        pitanjePan.add(doneB);
        right.setSize(new Dimension(300, 450));
        right.setPreferredSize(new Dimension(300, 450));
        main.add(pitanjePan, BorderLayout.WEST);
        main.add(right, BorderLayout.EAST);
        return main;
    }

    public JPanel getAbr() {
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());
        JPanel pitanjePan = new JPanel();
        pitanjePan.setLayout(new BoxLayout(pitanjePan, BoxLayout.Y_AXIS));
        JLabel pitanje = new JLabel(" Does your patient have? ");
        //gentamicin --pregnancy
        //erythromycin
        JCheckBox a = new JCheckBox("heart rhythm disorder");
        JCheckBox b = new JCheckBox("low levels of potassium or magnesium");
        JCheckBox c = new JCheckBox("pregnancy");
        //tetracaine --heart rhythm disorder
        JCheckBox d = new JCheckBox("high blood pressure");
        //proparacaine
        JCheckBox e = new JCheckBox("heart problems");
        JCheckBox f = new JCheckBox("overactive thyroid");
        //tobramycin injection
        JCheckBox g = new JCheckBox("kidney disease");
        JCheckBox h = new JCheckBox("muscle disorder");
        JCheckBox i = new JCheckBox("metabolic disorder");
        JCheckBox j = new JCheckBox("astma");

        ImageIcon done = new ImageIcon("./medicament.jpeg");
        Image doneIm = done.getImage(); // transform it
        Image doneimg = doneIm.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        done = new ImageIcon(doneimg);
        JButton doneB = new JButton("Medicaments", done);
        JPanel right = new JPanel();
        JTextArea prolog = new JTextArea();
        prolog.setVisible(false);
        Border blackline2 = BorderFactory.createLineBorder(Color.BLACK);
        TitledBorder title2 = BorderFactory.createTitledBorder(blackline2, "Possible medications are:");
        title2.setTitleJustification(TitledBorder.CENTER);
        prolog.setBorder(title2);
        prolog.setSize(new Dimension(280, 200));
        right.add(prolog);
        doneB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                prolog.setText("");
                notes = new ArrayList<>();
                if (a.isSelected()) {
                    notes.add("heart_rhythm_disorder");
                }
                if (b.isSelected()) {
                    notes.add("low_levels_of_potassium_or_magnesium");
                }
                if (c.isSelected()) {
                    notes.add("pregnancy");
                }
                if (d.isSelected()) {
                    notes.add("high_blood_pressure");
                }
                if (e.isSelected()) {
                    notes.add("heart_problems");
                }
                if (f.isSelected()) {
                    notes.add("overactive_thyroid");
                }
                if (g.isSelected()) {
                    notes.add("kidney_disease");
                }
                if (h.isSelected()) {
                    notes.add("muscle_disorder");
                }
                if (i.isSelected()) {
                    notes.add("metabolic_disorder");
                }
                if (j.isSelected()) {
                    notes.add("astma");
                }

                MedicationsProlog mp = new MedicationsProlog();
                ArrayList<String> medicaments = mp.prolog("corneal_abrasion", notes);
                String med = "     Possible medications are:     \n";
                int brojac = 1;
                for (String isp : medicaments) {
                    isp = isp.substring(0, 1).toUpperCase() + isp.substring(1);
                    isp = "        " + brojac + ". " + isp.replaceAll("_", " ");
                    med += isp + "\n";
                    brojac++;
                }
                prolog.setText(med);
                prolog.setVisible(true);
                main.revalidate();
            }
        });
        pitanjePan.add(pitanje);
        pitanjePan.add(a);
        pitanjePan.add(b);
        pitanjePan.add(c);
        pitanjePan.add(d);
        pitanjePan.add(e);
        pitanjePan.add(f);
        pitanjePan.add(g);
        pitanjePan.add(h);
        pitanjePan.add(i);
        pitanjePan.add(j);
        pitanjePan.add(doneB);
        right.setSize(new Dimension(300, 450));
        right.setPreferredSize(new Dimension(300, 450));
        main.add(pitanjePan, BorderLayout.WEST);
        main.add(right, BorderLayout.EAST);
        return main;
    }

    public JPanel getInf() {
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());
        JPanel pitanjePan = new JPanel();
        pitanjePan.setLayout(new BoxLayout(pitanjePan, BoxLayout.Y_AXIS));
        JLabel pitanje = new JLabel(" Does your patient have? ");
        //Moxifloxacin
        JCheckBox a = new JCheckBox("arthritis");
        JCheckBox b = new JCheckBox("blood circulation problems");
        JCheckBox c = new JCheckBox("heart problems");
        JCheckBox d = new JCheckBox("high blood pressure");
        JCheckBox e = new JCheckBox("diabetes");
        JCheckBox f = new JCheckBox("liver or kidney disease");
        JCheckBox g = new JCheckBox("seizures or epilepsy");
        JCheckBox h = new JCheckBox("head injury");
        //erythromycin
        JCheckBox i = new JCheckBox("heart rhythm disorder");
        JCheckBox j = new JCheckBox("low levels of potassium or magnesium");
        JCheckBox k = new JCheckBox("pregnancy");
        //proparacaine --heart problems
        JCheckBox l = new JCheckBox("overactive thyroid");
        //loteprednol --pregnancy
        JCheckBox m = new JCheckBox("eye infection");
        //dexamethasone
        JCheckBox p = new JCheckBox("thyroid disorder");
        JCheckBox n = new JCheckBox("malaria;");
        JCheckBox o = new JCheckBox("depression");

        ImageIcon done = new ImageIcon("./medicament.jpeg");
        Image doneIm = done.getImage(); // transform it
        Image doneimg = doneIm.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        done = new ImageIcon(doneimg);
        JButton doneB = new JButton("Medicaments", done);
        JPanel right = new JPanel();
        JTextArea prolog = new JTextArea();
        prolog.setVisible(false);
        Border blackline2 = BorderFactory.createLineBorder(Color.BLACK);
        TitledBorder title2 = BorderFactory.createTitledBorder(blackline2, "Possible medications are:");
        title2.setTitleJustification(TitledBorder.CENTER);
        prolog.setBorder(title2);
        prolog.setSize(new Dimension(280, 200));
        right.add(prolog);
        doneB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                prolog.setText("");
                notes = new ArrayList<>();
                if (a.isSelected()) {
                    notes.add("arthritis");
                }
                if (b.isSelected()) {
                    notes.add("blood_circulation_problems");
                }
                if (c.isSelected()) {
                    notes.add("heart_problems");
                }
                if (d.isSelected()) {
                    notes.add("high_blood_pressure");
                }
                if (e.isSelected()) {
                    notes.add("diabetes");
                }
                if (f.isSelected()) {
                    notes.add("liver_or_kidney_disease");
                }
                if (g.isSelected()) {
                    notes.add("seizures_or_epilepsy");
                }
                if (h.isSelected()) {
                    notes.add("head_injury");
                }
                if (i.isSelected()) {
                    notes.add("heart_rhythm_disorder");
                }
                if (j.isSelected()) {
                    notes.add("low_levels_of_potassium_or_magnesium");
                }
                if (k.isSelected()) {
                    notes.add("pregnancy");
                }
                if (l.isSelected()) {
                    notes.add("overactive_thyroid");
                }
                if (m.isSelected()) {
                    notes.add("eye_infection");
                }
                if (p.isSelected()) {
                    notes.add("thyroid_disorder");
                }
                if (n.isSelected()) {
                    notes.add("malaria");
                }
                if (o.isSelected()) {
                    notes.add("depression");
                }

                MedicationsProlog mp = new MedicationsProlog();
                ArrayList<String> medicaments = mp.prolog("cornea_infection", notes);
                String med = "     Possible medications are:     \n";
                int brojac = 1;
                for (String isp : medicaments) {
                    isp = isp.substring(0, 1).toUpperCase() + isp.substring(1);
                    isp = "        " + brojac + ". " + isp.replaceAll("_", " ");
                    med += isp + "\n";
                    brojac++;
                }
                prolog.setText(med);
                prolog.setVisible(true);
                main.revalidate();
            }
        });
        pitanjePan.add(pitanje);
        pitanjePan.add(a);
        pitanjePan.add(b);
        pitanjePan.add(c);
        pitanjePan.add(d);
        pitanjePan.add(e);
        pitanjePan.add(f);
        pitanjePan.add(g);
        pitanjePan.add(h);
        pitanjePan.add(i);
        pitanjePan.add(j);
        pitanjePan.add(k);
        pitanjePan.add(l);
        pitanjePan.add(m);
        pitanjePan.add(n);
        pitanjePan.add(o);
        pitanjePan.add(p);
        pitanjePan.add(doneB);
        right.setSize(new Dimension(300, 450));
        right.setPreferredSize(new Dimension(300, 450));
        main.add(pitanjePan, BorderLayout.WEST);
        main.add(right, BorderLayout.EAST);
        return main;
    }

    public JPanel getRet() {
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());
        JPanel pitanjePan = new JPanel();
        pitanjePan.setLayout(new BoxLayout(pitanjePan, BoxLayout.Y_AXIS));
        JLabel pitanje = new JLabel(" Does your patient have? ");
        //phenylephrine
        JCheckBox a = new JCheckBox("heart problems");
        JCheckBox b = new JCheckBox("diabetes");
        JCheckBox c = new JCheckBox("high blood pressure");
        JCheckBox d = new JCheckBox("enlarged prostate or urination problems");
        //prednisolone --high blood pressure
        JCheckBox e = new JCheckBox("tuberculosis");
        JCheckBox f = new JCheckBox("thyroid disorder");
        JCheckBox g = new JCheckBox("mental illness");
        JCheckBox h = new JCheckBox("depression");
        JCheckBox p = new JCheckBox("multiple sclerosis");
        JCheckBox i = new JCheckBox("liver or kidney disease");
        //cyclosporine ophthalmic
        JCheckBox j = new JCheckBox("pregnancy");
        JCheckBox k = new JCheckBox("astma");
        //tropicamide

        ImageIcon done = new ImageIcon("./medicament.jpeg");
        Image doneIm = done.getImage(); // transform it
        Image doneimg = doneIm.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        done = new ImageIcon(doneimg);
        JButton doneB = new JButton("Medicaments", done);
        JPanel right = new JPanel();
        JTextArea prolog = new JTextArea();
        prolog.setVisible(false);
        Border blackline2 = BorderFactory.createLineBorder(Color.BLACK);
        TitledBorder title2 = BorderFactory.createTitledBorder(blackline2, "Possible medications are:");
        title2.setTitleJustification(TitledBorder.CENTER);
        prolog.setBorder(title2);
        prolog.setSize(new Dimension(280, 200));
        right.add(prolog);
        doneB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                prolog.setText("");
                notes = new ArrayList<>();
                if (a.isSelected()) {
                    notes.add("heart_problems");
                }
                if (b.isSelected()) {
                    notes.add("diabetes");
                }
                if (c.isSelected()) {
                    notes.add("high_blood_pressure");
                }
                if (d.isSelected()) {
                    notes.add("enlarged_prostate_or_urination_problems");
                }
                if (e.isSelected()) {
                    notes.add("tuberculosis");
                }
                if (f.isSelected()) {
                    notes.add("thyroid_disorder");
                }
                if (g.isSelected()) {
                    notes.add("mental_illness");
                }
                if (h.isSelected()) {
                    notes.add("depression");
                }
                if (p.isSelected()) {
                    notes.add("multiple_sclerosis");
                }
                if (i.isSelected()) {
                    notes.add("liver_or_kidney_disease");
                }
                if (j.isSelected()) {
                    notes.add("pregnancy");
                }
                if (k.isSelected()) {
                    notes.add("astma");
                }

                MedicationsProlog mp = new MedicationsProlog();
                ArrayList<String> medicaments = mp.prolog("retinal_detachment", notes);
                String med = "     Possible medications are:     \n";
                int brojac = 1;
                for (String isp : medicaments) {
                    isp = isp.substring(0, 1).toUpperCase() + isp.substring(1);
                    isp = "        " + brojac + ". " + isp.replaceAll("_", " ");
                    med += isp + "\n";
                    brojac++;
                }
                prolog.setText(med);
                prolog.setVisible(true);
                main.revalidate();
            }
        });
        pitanjePan.add(pitanje);
        pitanjePan.add(a);
        pitanjePan.add(b);
        pitanjePan.add(c);
        pitanjePan.add(d);
        pitanjePan.add(e);
        pitanjePan.add(f);
        pitanjePan.add(g);
        pitanjePan.add(h);
        pitanjePan.add(i);
        pitanjePan.add(j);
        pitanjePan.add(k);
        pitanjePan.add(p);
        pitanjePan.add(doneB);
        right.setSize(new Dimension(300, 450));
        right.setPreferredSize(new Dimension(300, 450));
        main.add(pitanjePan, BorderLayout.WEST);
        main.add(right, BorderLayout.EAST);
        return main;
    }

    public JPanel getOpt() {
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());
        JPanel pitanjePan = new JPanel();
        pitanjePan.setLayout(new BoxLayout(pitanjePan, BoxLayout.Y_AXIS));
        JLabel pitanje = new JLabel(" Does your patient have? ");
        //glatiramer injection
        JCheckBox a = new JCheckBox("pregnancy");
        //fluorescein
        JCheckBox b = new JCheckBox("shortness of breath");
        JCheckBox c = new JCheckBox("swelling of face");
        //naltrexone
        JCheckBox d = new JCheckBox("liver or kidney disease");
        JCheckBox e = new JCheckBox("bleeding disorder");
        JCheckBox f = new JCheckBox("drug or alcohol addiction");
        //brinzolamide --liver or kidney disease
        //desvenlafaxine
        JCheckBox g = new JCheckBox("mental illness");
        JCheckBox h = new JCheckBox("depression");
        JCheckBox i = new JCheckBox("migraine");
        JCheckBox j = new JCheckBox("high blood pressure");
        JCheckBox k = new JCheckBox("high cholesterol");
        JCheckBox l = new JCheckBox("seizures or epilepsy");
        JCheckBox m = new JCheckBox("lung or breathing problems");
        JCheckBox n = new JCheckBox("bleeding or blood clotting disorder");

        ImageIcon done = new ImageIcon("./medicament.jpeg");
        Image doneIm = done.getImage(); // transform it
        Image doneimg = doneIm.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        done = new ImageIcon(doneimg);
        JButton doneB = new JButton("Medicaments", done);
        JPanel right = new JPanel();
        JTextArea prolog = new JTextArea();
        prolog.setVisible(false);
        Border blackline2 = BorderFactory.createLineBorder(Color.BLACK);
        TitledBorder title2 = BorderFactory.createTitledBorder(blackline2, "Possible medications are:");
        title2.setTitleJustification(TitledBorder.CENTER);
        prolog.setBorder(title2);
        prolog.setSize(new Dimension(280, 200));
        right.add(prolog);
        doneB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                prolog.setText("");
                notes = new ArrayList<>();
                if (a.isSelected()) {
                    notes.add("pregnancy");
                }
                if (b.isSelected()) {
                    notes.add("shortness_of_breath");
                }
                if (c.isSelected()) {
                    notes.add("swelling_of_face");
                }
                if (d.isSelected()) {
                    notes.add("liver_or_kidney_disease");
                }
                if (e.isSelected()) {
                    notes.add("bleeding_disorder");
                }
                if (f.isSelected()) {
                    notes.add("drug_or_alcohol_addiction");
                }
                if (g.isSelected()) {
                    notes.add("mental_illness");
                }
                if (h.isSelected()) {
                    notes.add("depression");
                }
                if (i.isSelected()) {
                    notes.add("migraine");
                }
                if (j.isSelected()) {
                    notes.add("high_blood_pressure");
                }
                if (k.isSelected()) {
                    notes.add("high_cholesterol");
                }
                if (m.isSelected()) {
                    notes.add("lung_or_breathing_problems");
                }
                if (l.isSelected()) {
                    notes.add("seizures_or_epilepsy");
                }
                if (n.isSelected()) {
                    notes.add("bleeding_or_blood_clotting_disorder");
                }
                MedicationsProlog mp = new MedicationsProlog();
                ArrayList<String> medicaments = mp.prolog("optic_neuritis", notes);
                String med = "     Possible medications are:     \n";
                int brojac = 1;
                for (String isp : medicaments) {
                    isp = isp.substring(0, 1).toUpperCase() + isp.substring(1);
                    isp = "        " + brojac + ". " + isp.replaceAll("_", " ");
                    med += isp + "\n";
                    brojac++;
                }
                prolog.setText(med);
                prolog.setVisible(true);
                main.revalidate();
            }
        });
        pitanjePan.add(pitanje);
        pitanjePan.add(a);
        pitanjePan.add(b);
        pitanjePan.add(c);
        pitanjePan.add(d);
        pitanjePan.add(e);
        pitanjePan.add(f);
        pitanjePan.add(g);
        pitanjePan.add(h);
        pitanjePan.add(i);
        pitanjePan.add(j);
        pitanjePan.add(k);
        pitanjePan.add(l);
        pitanjePan.add(m);
        pitanjePan.add(n);
        pitanjePan.add(doneB);
        right.setSize(new Dimension(300, 450));
        right.setPreferredSize(new Dimension(300, 450));
        main.add(pitanjePan, BorderLayout.WEST);
        main.add(right, BorderLayout.EAST);
        return main;
    }

    public JPanel getIri() {
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());
        JPanel pitanjePan = new JPanel();
        pitanjePan.setLayout(new BoxLayout(pitanjePan, BoxLayout.Y_AXIS));
        JLabel pitanje = new JLabel(" Does your patient have? ");
        //prednisolone --depression --liver or kidney disease
        JCheckBox e = new JCheckBox("tuberculosis");
        JCheckBox f = new JCheckBox("thyroid disorder");
        JCheckBox g = new JCheckBox("mental illness");
        JCheckBox h = new JCheckBox("high blood pressure");
        JCheckBox p = new JCheckBox("multiple sclerosis");
        //cyclosporine ophthalmic
        JCheckBox a = new JCheckBox("pregnancy");
        JCheckBox b = new JCheckBox("astma");
        //brimonidine
        JCheckBox l = new JCheckBox("depression");
        JCheckBox m = new JCheckBox("low blood pressure");
        JCheckBox n = new JCheckBox("clogged arteries");
        //tropicamide
        //loteprednol --pregnancy
        JCheckBox c = new JCheckBox("eye infection");
        //atropine --astma
        JCheckBox d = new JCheckBox("enlarged prostate");
        JCheckBox i = new JCheckBox("heart rhythm disorder");
        JCheckBox j = new JCheckBox("liver or kidney disease");

        ImageIcon done = new ImageIcon("./medicament.jpeg");
        Image doneIm = done.getImage(); // transform it
        Image doneimg = doneIm.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        done = new ImageIcon(doneimg);
        JButton doneB = new JButton("Medicaments", done);
        JPanel right = new JPanel();
        JTextArea prolog = new JTextArea();
        prolog.setVisible(false);
        Border blackline2 = BorderFactory.createLineBorder(Color.BLACK);
        TitledBorder title2 = BorderFactory.createTitledBorder(blackline2, "Possible medications are:");
        title2.setTitleJustification(TitledBorder.CENTER);
        prolog.setBorder(title2);
        prolog.setSize(new Dimension(280, 200));
        right.add(prolog);
        doneB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                prolog.setText("");
                notes = new ArrayList<>();
                if (e.isSelected()) {
                    notes.add("tuberculosis");
                }
                if (f.isSelected()) {
                    notes.add("thyroid_disorder");
                }
                if (g.isSelected()) {
                    notes.add("mental_illness");
                }
                if (h.isSelected()) {
                    notes.add("high_blood_pressure");
                }
                if (p.isSelected()) {
                    notes.add("multiple_sclerosis");
                }
                if (a.isSelected()) {
                    notes.add("pregnancy");
                }
                if (b.isSelected()) {
                    notes.add("astma");
                }
                if (m.isSelected()) {
                    notes.add("low_blood_pressure");
                }
                if (l.isSelected()) {
                    notes.add("depression");
                }
                if (n.isSelected()) {
                    notes.add("clogged_arteries");
                }
                if (c.isSelected()) {
                    notes.add("eye_infection");
                }
                if (d.isSelected()) {
                    notes.add("enlarged_prostate");
                }
                if (i.isSelected()) {
                    notes.add("heart_rhythm_disorder");
                }
                if (j.isSelected()) {
                    notes.add("liver_or_kidney_disease");
                }

                MedicationsProlog mp = new MedicationsProlog();
                ArrayList<String> medicaments = mp.prolog("iridocyclitis", notes);
                String med = "     Possible medications are:     \n";
                int brojac = 1;
                for (String isp : medicaments) {
                    isp = isp.substring(0, 1).toUpperCase() + isp.substring(1);
                    isp = "        " + brojac + ". " + isp.replaceAll("_", " ");
                    med += isp + "\n";
                    brojac++;
                }
                prolog.setText(med);
                prolog.setVisible(true);
                main.revalidate();
            }
        });
        pitanjePan.add(pitanje);
        pitanjePan.add(a);
        pitanjePan.add(b);
        pitanjePan.add(c);
        pitanjePan.add(d);
        pitanjePan.add(e);
        pitanjePan.add(f);
        pitanjePan.add(g);
        pitanjePan.add(h);
        pitanjePan.add(i);
        pitanjePan.add(j);
        pitanjePan.add(l);
        pitanjePan.add(m);
        pitanjePan.add(n);
        pitanjePan.add(doneB);
        right.setSize(new Dimension(300, 450));
        right.setPreferredSize(new Dimension(300, 450));
        main.add(pitanjePan, BorderLayout.WEST);
        main.add(right, BorderLayout.EAST);
        return main;
    }

    public JPanel getSub() {
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());
        JPanel pitanjePan = new JPanel();
        pitanjePan.setLayout(new BoxLayout(pitanjePan, BoxLayout.Y_AXIS));
        JLabel pitanje = new JLabel(" Does your patient have? ");
        //tetracaine --heart rhythm disorder
        JCheckBox d = new JCheckBox("high blood pressure");
        //fluorescein
        JCheckBox b = new JCheckBox("shortness of breath");
        JCheckBox c = new JCheckBox("swelling of face");
        //prednisolone
        JCheckBox e = new JCheckBox("tuberculosis");
        JCheckBox f = new JCheckBox("thyroid disorder");
        JCheckBox g = new JCheckBox("mental illness");
        JCheckBox h = new JCheckBox("high blood pressure");
        JCheckBox p = new JCheckBox("multiple sclerosis");
        JCheckBox a = new JCheckBox("liver or kidney diseas");
        JCheckBox x = new JCheckBox("depression");
        //gentamicin
        JCheckBox y = new JCheckBox("pregnancy");
        //erythromycin --pregnancy
        JCheckBox k = new JCheckBox("heart rhythm disorder");
        JCheckBox l = new JCheckBox("low levels of potassium or magnesium");
        //atropine --astma --liver or kidney disease
        JCheckBox m = new JCheckBox("enlarged prostate");
        JCheckBox i = new JCheckBox("heart rhythm disorder");
        //Sulfacetamide ophthalmic
        JCheckBox n = new JCheckBox("astma");

        ImageIcon done = new ImageIcon("./medicament.jpeg");
        Image doneIm = done.getImage(); // transform it
        Image doneimg = doneIm.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        done = new ImageIcon(doneimg);
        JButton doneB = new JButton("Medicaments", done);
        JPanel right = new JPanel();
        JTextArea prolog = new JTextArea();
        prolog.setVisible(false);
        Border blackline2 = BorderFactory.createLineBorder(Color.BLACK);
        TitledBorder title2 = BorderFactory.createTitledBorder(blackline2, "Possible medications are:");
        title2.setTitleJustification(TitledBorder.CENTER);
        prolog.setBorder(title2);
        prolog.setSize(new Dimension(280, 200));
        right.add(prolog);
        doneB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                prolog.setText("");
                notes = new ArrayList<>();
                if (e.isSelected()) {
                    notes.add("tuberculosis");
                }
                if (f.isSelected()) {
                    notes.add("thyroid_disorder");
                }
                if (g.isSelected()) {
                    notes.add("mental_illness");
                }
                if (h.isSelected()) {
                    notes.add("high_blood_pressure");
                }
                if (p.isSelected()) {
                    notes.add("multiple_sclerosis");
                }
                if (d.isSelected()) {
                    notes.add("high_blood_pressure");
                }
                if (b.isSelected()) {
                    notes.add("shortness_of_breath");
                }
                if (c.isSelected()) {
                    notes.add("swelling_of_face");
                }
                if (a.isSelected()) {
                    notes.add("liver_or_kidney_diseas");
                }
                if (x.isSelected()) {
                    notes.add("depression");
                }
                if (y.isSelected()) {
                    notes.add("pregnancy");
                }
                if (k.isSelected()) {
                    notes.add("heart_rhythm_disorder");
                }
                if (l.isSelected()) {
                    notes.add("low_levels_of_potassium_or_magnesium");
                }
                if (m.isSelected()) {
                    notes.add("enlarged_prostate");
                }
                if (n.isSelected()) {
                    notes.add("astma");
                }
                if (i.isSelected()) {
                    notes.add("heart_rhythm_disorder");
                }

                MedicationsProlog mp = new MedicationsProlog();
                ArrayList<String> medicaments = mp.prolog("subconjunctival_hemorrhage", notes);
                String med = "     Possible medications are:     \n";
                int brojac = 1;
                for (String isp : medicaments) {
                    isp = isp.substring(0, 1).toUpperCase() + isp.substring(1);
                    isp = "        " + brojac + ". " + isp.replaceAll("_", " ");
                    med += isp + "\n";
                    brojac++;
                }
                prolog.setText(med);
                prolog.setVisible(true);
                main.revalidate();
            }
        });
        pitanjePan.add(pitanje);
        pitanjePan.add(a);
        pitanjePan.add(b);
        pitanjePan.add(c);
        pitanjePan.add(d);
        pitanjePan.add(e);
        pitanjePan.add(f);
        pitanjePan.add(g);
        pitanjePan.add(h);
        pitanjePan.add(i);
        pitanjePan.add(x);
        pitanjePan.add(y);
        pitanjePan.add(l);
        pitanjePan.add(m);
        pitanjePan.add(n);
        pitanjePan.add(k);
        pitanjePan.add(p);
        pitanjePan.add(doneB);
        right.setSize(new Dimension(300, 450));
        right.setPreferredSize(new Dimension(300, 450));
        main.add(pitanjePan, BorderLayout.WEST);
        main.add(right, BorderLayout.EAST);
        return main;
    }

    public JPanel getFlo() {
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());
        JPanel pitanjePan = new JPanel();
        pitanjePan.setLayout(new BoxLayout(pitanjePan, BoxLayout.Y_AXIS));
        JLabel pitanje = new JLabel(" Does your patient have? ");
        //phenylephrine
        JCheckBox f = new JCheckBox("heart problems");
        JCheckBox g = new JCheckBox("high blood pressure");
        JCheckBox h = new JCheckBox("diabetes");
        JCheckBox i = new JCheckBox("enlarged prostate or urination problems");
        //tropicamide
        //ocular lubricant
        //loteprednol --pregnancy
        JCheckBox e = new JCheckBox("eye infection");
        //fluorescein
        JCheckBox b = new JCheckBox("shortness of breath");
        JCheckBox c = new JCheckBox("swelling of face");
        //cyclosporine ophthalmic
        JCheckBox a = new JCheckBox("pregnancy");
        JCheckBox d = new JCheckBox("astma");

        ImageIcon done = new ImageIcon("./medicament.jpeg");
        Image doneIm = done.getImage(); // transform it
        Image doneimg = doneIm.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        done = new ImageIcon(doneimg);
        JButton doneB = new JButton("Medicaments", done);
        JPanel right = new JPanel();
        JTextArea prolog = new JTextArea();
        prolog.setVisible(false);
        Border blackline2 = BorderFactory.createLineBorder(Color.BLACK);
        TitledBorder title2 = BorderFactory.createTitledBorder(blackline2, "Possible medications are:");
        title2.setTitleJustification(TitledBorder.CENTER);
        prolog.setBorder(title2);
        prolog.setSize(new Dimension(280, 200));
        right.add(prolog);
        doneB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                prolog.setText("");
                notes = new ArrayList<>();
                if (f.isSelected()) {
                    notes.add("heart_problems");
                }
                if (g.isSelected()) {
                    notes.add("high_blood_pressure");
                }
                if (h.isSelected()) {
                    notes.add("diabetes");
                }
                if (i.isSelected()) {
                    notes.add("enlarged_prostate_or_urination_problems");
                }
                if (e.isSelected()) {
                    notes.add("eye_infection");
                }
                if (b.isSelected()) {
                    notes.add("shortness_of_breath");
                }
                if (c.isSelected()) {
                    notes.add("swelling_of_face");
                }
                if (d.isSelected()) {
                    notes.add("astma");
                }
                if (a.isSelected()) {
                    notes.add("pregnancy");
                }

                MedicationsProlog mp = new MedicationsProlog();
                ArrayList<String> medicaments = mp.prolog("floaters", notes);
                String med = "     Possible medications are:     \n";
                int brojac = 1;
                for (String isp : medicaments) {
                    isp = isp.substring(0, 1).toUpperCase() + isp.substring(1);
                    isp = "        " + brojac + ". " + isp.replaceAll("_", " ");
                    med += isp + "\n";
                    brojac++;
                }
                prolog.setText(med);
                prolog.setVisible(true);
                main.revalidate();
            }
        });
        pitanjePan.add(pitanje);
        pitanjePan.add(a);
        pitanjePan.add(b);
        pitanjePan.add(c);
        pitanjePan.add(d);
        pitanjePan.add(e);
        pitanjePan.add(f);
        pitanjePan.add(g);
        pitanjePan.add(h);
        pitanjePan.add(i);
        pitanjePan.add(doneB);
        right.setSize(new Dimension(300, 450));
        right.setPreferredSize(new Dimension(300, 450));
        main.add(pitanjePan, BorderLayout.WEST);
        main.add(right, BorderLayout.EAST);
        return main;
    }

    public JPanel getEye() {
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());
        JPanel pitanjePan = new JPanel();
        pitanjePan.setLayout(new BoxLayout(pitanjePan, BoxLayout.Y_AXIS));
        JLabel pitanje = new JLabel(" Does your patient have? ");
        //phenylephrine
        JCheckBox f = new JCheckBox("heart problems");
        JCheckBox g = new JCheckBox("high blood pressure");
        JCheckBox h = new JCheckBox("diabetes");
        JCheckBox i = new JCheckBox("enlarged prostate or urination problems");
        //cyclosporine ophthalmic
        JCheckBox a = new JCheckBox("pregnancy");
        JCheckBox d = new JCheckBox("astma");
        //tropicamide
        //atropine --astma --enlarged prostate or urination problems
        JCheckBox k = new JCheckBox("heart rhythm disorder");
        JCheckBox j = new JCheckBox("liver or kidney disease");
        //dexamethasone
        JCheckBox p = new JCheckBox("thyroid disorder");
        JCheckBox n = new JCheckBox("malaria;");
        JCheckBox o = new JCheckBox("depression");
        //fluorescein
        JCheckBox b = new JCheckBox("shortness of breath");
        JCheckBox c = new JCheckBox("swelling of face");

        ImageIcon done = new ImageIcon("./medicament.jpeg");
        Image doneIm = done.getImage(); // transform it
        Image doneimg = doneIm.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        done = new ImageIcon(doneimg);
        JButton doneB = new JButton("Medicaments", done);
        JPanel right = new JPanel();
        JTextArea prolog = new JTextArea();
        prolog.setVisible(false);
        Border blackline2 = BorderFactory.createLineBorder(Color.BLACK);
        TitledBorder title2 = BorderFactory.createTitledBorder(blackline2, "Possible medications are:");
        title2.setTitleJustification(TitledBorder.CENTER);
        prolog.setBorder(title2);
        prolog.setSize(new Dimension(280, 200));
        right.add(prolog);
        doneB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                prolog.setText("");
                notes = new ArrayList<>();
                if (f.isSelected()) {
                    notes.add("heart_problems");
                }
                if (g.isSelected()) {
                    notes.add("high_blood_pressure");
                }
                if (h.isSelected()) {
                    notes.add("diabetes");
                }
                if (i.isSelected()) {
                    notes.add("enlarged_prostate_or_urination_problems");
                }
                if (d.isSelected()) {
                    notes.add("astma");
                }
                if (a.isSelected()) {
                    notes.add("pregnancy");
                }
                if (k.isSelected()) {
                    notes.add("heart_rhythm_disorder");
                }
                if (j.isSelected()) {
                    notes.add("liver_or_kidney_disease");
                }
                if (p.isSelected()) {
                    notes.add("thyroid_disorder");
                }
                if (n.isSelected()) {
                    notes.add("malaria");
                }
                if (o.isSelected()) {
                    notes.add("depression");
                }
                if (b.isSelected()) {
                    notes.add("shortness_of_breath");
                }
                if (c.isSelected()) {
                    notes.add("swelling_of_face");
                }

                MedicationsProlog mp = new MedicationsProlog();
                ArrayList<String> medicaments = mp.prolog("eye_alignment_disorder", notes);
                String med = "     Possible medications are:     \n";
                int brojac = 1;
                for (String isp : medicaments) {
                    isp = isp.substring(0, 1).toUpperCase() + isp.substring(1);
                    isp = "        " + brojac + ". " + isp.replaceAll("_", " ");
                    med += isp + "\n";
                    brojac++;
                }
                prolog.setText(med);
                prolog.setVisible(true);
                main.revalidate();
            }
        });
        pitanjePan.add(pitanje);
        pitanjePan.add(a);
        pitanjePan.add(b);
        pitanjePan.add(c);
        pitanjePan.add(d);
        pitanjePan.add(f);
        pitanjePan.add(g);
        pitanjePan.add(h);
        pitanjePan.add(i);
        pitanjePan.add(j);
        pitanjePan.add(k);
        pitanjePan.add(n);
        pitanjePan.add(o);
        pitanjePan.add(p);
        pitanjePan.add(doneB);
        right.setSize(new Dimension(300, 450));
        right.setPreferredSize(new Dimension(300, 450));
        main.add(pitanjePan, BorderLayout.WEST);
        main.add(right, BorderLayout.EAST);
        return main;
    }
}
