package Actions;

import app.RankingList;
import com.ugos.jiprolog.engine.JIPEngine;
import com.ugos.jiprolog.engine.JIPQuery;
import com.ugos.jiprolog.engine.JIPTerm;
import com.ugos.jiprolog.engine.JIPVariable;
import model.Osoba;
import view.DiagnosisView;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DaljaIspitivanjaProlog {
    public static ArrayList<String> notes = new ArrayList<>();
    public Map<String, Float> sortedMapRBR = new HashMap<>();
    public String bolest1 = "";
    public String bolest2 = "";
    public String bolest3 = "";
    public Osoba korisnik = new Osoba();
    public String jmbg;
    public String navedeniSimptomi;
    public ArrayList<String> symptoms = new ArrayList<>();

    private JFrame mainFrame = new JFrame("Ophthalmology");

    public DaljaIspitivanjaProlog(Osoba korisnik, Map<String, Float> sortedMapRBR, ArrayList<String> symptoms, String jmbg, String symptomsString) {
        this.symptoms = symptoms;
        this.jmbg = jmbg;
        this.navedeniSimptomi = symptomsString;
        notes = new ArrayList<>();
        this.sortedMapRBR = sortedMapRBR;
        int it = 1;
        for (String key : sortedMapRBR.keySet()) {
            if (it == 1) {
                bolest1 = key;
            } else if (it == 2) {
                bolest2 = key;
            } else if (it == 3) {
                bolest3 = key;
            } else {
                break;
            }
            it++;
        }
        this.korisnik = korisnik;

        JPanel dodatak = new JPanel();
        dodatak.setLayout(new BoxLayout(dodatak, BoxLayout.Y_AXIS));

        String b1 = bolest1;
        b1 = b1.substring(0, b1.length()).toUpperCase();
        b1 = " " + b1.replaceAll("_", " ");
        JLabel lab1 = new JLabel(b1);
        Font font = lab1.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        lab1.setFont(font.deriveFont(attributes));
        dodatak.add(lab1);
        if (bolest1.equals("conjunctivitis")) {
            dodatak.add(getConj());
            //ako klikne za koliko se poveca verovatnoca?
        } else if (bolest1.equals("cataract")) {
            dodatak.add(getCataract());
        } else if (bolest1.equals("retinal_detachment")) {
            dodatak.add(getRetDet());
        } else if (bolest1.equals("optic_neuritis")) {
            dodatak.add(getOptNe());
        } else if (bolest1.equals("iridocyclitis")) {
            dodatak.add(getIri());
        } else if (bolest1.equals("subconjunctival_hemorrhage")) {
            dodatak.add(getSubHem());
        }

        dodatak.add(prolog(bolest1, symptoms));

        JLabel prazan = new JLabel(" ");
        dodatak.add(prazan);
        String b2 = bolest2;
        b2 = b2.substring(0, b2.length()).toUpperCase();
        b2 = " " + b2.replaceAll("_", " ");
        JLabel lab2 = new JLabel(b2);
        lab2.setFont(font.deriveFont(attributes));
        dodatak.add(lab2);
        if (bolest2.equals("conjunctivitis")) {
            dodatak.add(getConj());
        } else if (bolest2.equals("cataract")) {
            dodatak.add(getCataract());
        } else if (bolest2.equals("retinal_detachment")) {
            dodatak.add(getRetDet());
        } else if (bolest2.equals("optic_neuritis")) {
            dodatak.add(getOptNe());
        } else if (bolest2.equals("iridocyclitis")) {
            dodatak.add(getIri());
        } else if (bolest2.equals("subconjunctival_hemorrhage")) {
            dodatak.add(getSubHem());
        }
        dodatak.add(prolog(bolest2, symptoms));

        JLabel prazan2 = new JLabel(" ");
        dodatak.add(prazan2);
        String b3 = bolest3;
        b3 = b3.substring(0, b3.length()).toUpperCase();
        b3 = " " + b3.replaceAll("_", " ");
        JLabel lab3 = new JLabel(b3);
        lab3.setFont(font.deriveFont(attributes));
        dodatak.add(lab3);
        if (bolest3.equals("conjunctivitis")) {
            dodatak.add(getConj());
        } else if (bolest3.equals("cataract")) {
            dodatak.add(getCataract());
        } else if (bolest3.equals("retinal_detachment")) {
            dodatak.add(getRetDet());
        } else if (bolest3.equals("optic_neuritis")) {
            dodatak.add(getOptNe());
        } else if (bolest3.equals("iridocyclitis")) {
            dodatak.add(getIri());
        } else if (bolest3.equals("subconjunctival_hemorrhage")) {
            dodatak.add(getSubHem());
        }
        dodatak.add(prolog(bolest3, symptoms));

        JLabel prazan3 = new JLabel(" ");
        dodatak.add(prazan3);
        ImageIcon med = new ImageIcon("./reload.png");
        Image medIm = med.getImage(); // transform it
        Image newmedimg = medIm.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        med = new ImageIcon(newmedimg);
        Border blackline2 = BorderFactory.createLineBorder(Color.BLACK);
        TitledBorder title2 = BorderFactory.createTitledBorder(blackline2, "Probability of diseases");
        title2.setTitleJustification(TitledBorder.CENTER);
        JTextArea konacneVrv = new JTextArea();
        konacneVrv.setBorder(title2);
        konacneVrv.setSize(new Dimension(150, 150));
        konacneVrv.setPreferredSize(new Dimension(150, 150));
        //konacneVrv.setVisible(true);
        JPanel vr = new JPanel();
        vr.setSize(new Dimension(300, 600));
        vr.setPreferredSize(new Dimension(300, 600));
        vr.setLayout(new BoxLayout(vr, BoxLayout.Y_AXIS));
        String[] diseaseStrings = {"conjunctivitis", "blepharitis", "chronic glaucoma", "cataract",
                "macular degeneration", "dry eye of unknown cause", "eye alignment disorder", "corneal abrasion",
                "cornea infection", "retinal detachment", "optic neuritis", "iridocyclitis",
                "subconjunctival hemorrhage", "floaters"
        };
        JPanel combo = new JPanel();
        combo.setLayout(new FlowLayout());
        combo.setSize(new Dimension(400, 40));
        combo.setPreferredSize(new Dimension(400, 40));
        JComboBox diseaseList = new JComboBox(diseaseStrings);
        diseaseList.setSize(new Dimension(250, 20));
        diseaseList.setPreferredSize(new Dimension(250, 20));
        diseaseList.setSelectedIndex(0);
        JLabel diagnosis = new JLabel("Diagnosis:   ");
        combo.add(diagnosis);
        combo.add(diseaseList);
        ImageIcon done = new ImageIcon("./medicament.jpeg");
        Image doneIm = done.getImage(); // transform it
        Image doneimg = doneIm.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        done = new ImageIcon(doneimg);
        JButton diagnose = new JButton("Medicaments", done);

        diagnose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                DiagnosisView dv = new DiagnosisView(diseaseList.getSelectedItem().toString(), navedeniSimptomi, jmbg);
                mainFrame.dispose();
            }
        });

        vr.add(konacneVrv);

        JButton presaberi = new JButton("Reload", med);

        presaberi.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                vr.setVisible(true);
                Float sum = 0f;
                for (String key : sortedMapRBR.keySet()) {
                    sum += sortedMapRBR.get(key);
                }
                Float scaleNum = 1 / sum;
                Map<String, Float> mapOfScaledProbabilities = new HashMap<String, Float>();
                for (String key : sortedMapRBR.keySet()) {
                    mapOfScaledProbabilities.put(key, sortedMapRBR.get(key) * scaleNum);
                }
                mapOfScaledProbabilities = RankingList.sortByComparator(mapOfScaledProbabilities, false);
                konacneVrv.setText(printProbabilities(mapOfScaledProbabilities));

            }
        });
        combo.add(diagnose);
        dodatak.add(presaberi);
        dodatak.add(combo);
        JPanel ceo = new JPanel(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(dodatak);
        ceo.add(scrollPane, BorderLayout.CENTER);
        vr.setVisible(false);
        ceo.add(vr, BorderLayout.EAST);
        mainFrame.add(ceo);
        mainFrame.setSize(950, 700);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    public static float round(float number, int decimalPlace) {
        BigDecimal bd = new BigDecimal(number);
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    public String printProbabilities(Map<String, Float> map) {
        String print = "";
        String disease = "";
        int it = 0;
        for (Map.Entry<String, Float> entry : map.entrySet()) {
            if (entry.getValue() != 0) {
                //Da bi bilo na 2 decmalna
                disease = entry.getKey();
                disease = disease.substring(0, 1).toUpperCase() + disease.substring(1);
                disease = disease.replaceAll("_", " ");
                print += disease + " : " + round(entry.getValue() * 100, 2) + " %" + "\n";
                it++;
            }
            if (it >= 3) { //maximalno 5 bolesti se ispisuje
                break;
            }
        }
        return print;
    }

    public JPanel getConj() {
        JPanel pitanjePan = new JPanel();
        pitanjePan.setLayout(new BoxLayout(pitanjePan, BoxLayout.Y_AXIS));
        JLabel pitanje = new JLabel(" Did patient had lately? ");
        JCheckBox inf = new JCheckBox("Bacterial infections");
        JCheckBox che = new JCheckBox("Chemical irritants");
        JCheckBox al = new JCheckBox("Allergies");
        pitanjePan.add(pitanje);
        pitanjePan.add(inf);
        pitanjePan.add(che);
        pitanjePan.add(al);

        inf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (inf.isSelected()) {
                    Float probability = sortedMapRBR.get("conjunctivitis");
                    sortedMapRBR.put("conjunctivitis", (probability + 0.033f));
                    notes.add("bacterial infections");
                } else {
                    Float probability = sortedMapRBR.get("conjunctivitis");
                    sortedMapRBR.put("conjunctivitis", (probability - 0.033f));
                    for (String note : notes) {
                        if (note.equals("bacterial infections")) {
                            notes.remove("bacterial infections");
                            break;
                        }
                    }
                }
            }
        });

        che.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (che.isSelected()) {
                    Float probability = sortedMapRBR.get("conjunctivitis");
                    sortedMapRBR.put("conjunctivitis", (probability + 0.033f));
                    notes.add("chemical irritants");

                } else {
                    Float probability = sortedMapRBR.get("conjunctivitis");
                    sortedMapRBR.put("conjunctivitis", (probability - 0.033f));
                    for (String note : notes) {
                        if (note.equals("chemical irritants")) {
                            notes.remove("chemical irritants");
                            break;
                        }
                    }
                }
            }
        });

        al.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (al.isSelected()) {
                    Float probability = sortedMapRBR.get("conjunctivitis");
                    sortedMapRBR.put("conjunctivitis", (probability + 0.033f));
                    notes.add("allergies");
                } else {
                    Float probability = sortedMapRBR.get("conjunctivitis");
                    sortedMapRBR.put("conjunctivitis", (probability - 0.033f));
                    for (String note : notes) {
                        if (note.equals("allergies")) {
                            notes.remove("allergies");
                            break;
                        }
                    }
                }
            }
        });

        return pitanjePan;
    }


    public JPanel getRetDet() {
        JPanel pitanjePan = new JPanel();
        pitanjePan.setLayout(new BoxLayout(pitanjePan, BoxLayout.Y_AXIS));
        JLabel pitanje = new JLabel(" Does your patient have prior eye surgery? ");
        JRadioButton da = new JRadioButton("Yes");
        JRadioButton ne = new JRadioButton("No");
        ButtonGroup bg = new ButtonGroup();
        bg.add(da);
        bg.add(ne);
        ne.setSelected(true);

        da.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Float probability = sortedMapRBR.get("retinal_detachment");
                boolean found = false;
                for (String note : notes) {
                    if (note.equals("prior eye surgery")) {
                        found = true;
                        break;
                    }
                }
                if (found == false) {
                    sortedMapRBR.put("retinal_detachment", (probability + 0.05f));
                    notes.add("prior eye surgery");
                }
            }
        });

        ne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Float probability = sortedMapRBR.get("retinal_detachment");
                for (String note : notes) {
                    if (note.equals("prior eye surgery")) {
                        notes.remove("prior eye surgery");
                        sortedMapRBR.put("retinal_detachment", (probability - 0.05f));
                        break;
                    }
                }
            }
        });

        pitanjePan.add(pitanje);
        pitanjePan.add(da);
        pitanjePan.add(ne);
        return pitanjePan;
    }

    public JPanel getOptNe() {
        JPanel pitanjePan = new JPanel();
        pitanjePan.setLayout(new BoxLayout(pitanjePan, BoxLayout.Y_AXIS));
        JLabel pitanje = new JLabel(" Does your patient have? ");
        JCheckBox inf = new JCheckBox("Multiple sclerosis");
        JCheckBox che = new JCheckBox("Been using drugs lately");
        JCheckBox al = new JCheckBox("Auto-immune disorders");

        inf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (inf.isSelected()) {
                    Float probability = sortedMapRBR.get("optic_neuritis");
                    sortedMapRBR.put("optic_neuritis", (probability + 0.033f));
                    notes.add("multiple sclerosis");
                } else {
                    Float probability = sortedMapRBR.get("optic_neuritis");
                    sortedMapRBR.put("optic_neuritis", (probability - 0.033f));
                    for (String note : notes) {
                        if (note.equals("multiple sclerosis")) {
                            notes.remove("multiple sclerosis");
                            break;
                        }
                    }
                }
            }
        });

        che.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (che.isSelected()) {
                    Float probability = sortedMapRBR.get("optic_neuritis");
                    sortedMapRBR.put("optic_neuritis", (probability + 0.033f));
                    notes.add("using drugs lately");
                } else {
                    Float probability = sortedMapRBR.get("optic_neuritis");
                    sortedMapRBR.put("optic_neuritis", (probability - 0.033f));
                    for (String note : notes) {
                        if (note.equals("using drugs lately")) {
                            notes.remove("using drugs lately");
                            break;
                        }
                    }
                }
            }
        });

        al.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (al.isSelected()) {
                    Float probability = sortedMapRBR.get("optic_neuritis");
                    sortedMapRBR.put("optic_neuritis", (probability + 0.033f));
                    notes.add("auto-immune disorders");
                } else {
                    Float probability = sortedMapRBR.get("optic_neuritis");
                    sortedMapRBR.put("optic_neuritis", (probability - 0.033f));
                    for (String note : notes) {
                        if (note.equals("auto-immune disorders")) {
                            notes.remove("auto-immune disorders");
                            break;
                        }
                    }
                }
            }
        });

        pitanjePan.add(pitanje);
        pitanjePan.add(inf);
        pitanjePan.add(che);
        pitanjePan.add(al);
        return pitanjePan;
    }

    public JPanel getSubHem() {
        JPanel pitanjePan = new JPanel();
        pitanjePan.setLayout(new BoxLayout(pitanjePan, BoxLayout.Y_AXIS));
        JLabel pitanje = new JLabel(" Does your patient have? ");
        JCheckBox inf = new JCheckBox("High blood pressure");
        JCheckBox che = new JCheckBox("Bleeding disorders");

        inf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (inf.isSelected()) {
                    Float probability = sortedMapRBR.get("subconjunctival_hemorrhage");
                    sortedMapRBR.put("subconjunctival_hemorrhage", (probability + 0.05f));
                    notes.add("high blood pressure");
                } else {
                    Float probability = sortedMapRBR.get("subconjunctival_hemorrhage");
                    sortedMapRBR.put("subconjunctival_hemorrhage", (probability - 0.05f));
                    for (String note : notes) {
                        if (note.equals("high blood pressure")) {
                            notes.remove("high blood pressure");
                            break;
                        }
                    }
                }
            }
        });

        che.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (che.isSelected()) {
                    Float probability = sortedMapRBR.get("subconjunctival_hemorrhage");
                    sortedMapRBR.put("subconjunctival_hemorrhage", (probability + 0.05f));
                    notes.add("bleeding disorders");
                } else {
                    Float probability = sortedMapRBR.get("subconjunctival_hemorrhage");
                    sortedMapRBR.put("subconjunctival_hemorrhage", (probability - 0.05f));
                    for (String note : notes) {
                        if (note.equals("bleeding disorders")) {
                            notes.remove("bleeding disorders");
                            break;
                        }
                    }
                }
            }
        });

        pitanjePan.add(pitanje);
        pitanjePan.add(inf);
        pitanjePan.add(che);
        return pitanjePan;
    }

    public JPanel getIri() {
        JPanel pitanjePan = new JPanel();
        pitanjePan.setLayout(new BoxLayout(pitanjePan, BoxLayout.Y_AXIS));
        JLabel pitanje = new JLabel(" Does your patient have? ");
        JCheckBox inf = new JCheckBox("Infection of the eyeball");
        JCheckBox al = new JCheckBox("Auto-immune disorders");
        inf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (inf.isSelected()) {
                    Float probability = sortedMapRBR.get("iridocyclitis");
                    sortedMapRBR.put("iridocyclitis", (probability + 0.05f));
                    notes.add("infection of the eyeball");
                } else {
                    Float probability = sortedMapRBR.get("iridocyclitis");
                    sortedMapRBR.put("iridocyclitis", (probability - 0.05f));
                    for (String note : notes) {
                        if (note.equals("infection of the eyeball")) {
                            notes.remove("infection of the eyeball");
                            break;
                        }
                    }
                }
            }
        });

        al.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (al.isSelected()) {
                    Float probability = sortedMapRBR.get("iridocyclitis");
                    sortedMapRBR.put("iridocyclitis", (probability + 0.05f));
                    notes.add("auto-immune disorders");
                } else {
                    Float probability = sortedMapRBR.get("iridocyclitis");
                    sortedMapRBR.put("iridocyclitis", (probability - 0.05f));
                    for (String note : notes) {
                        if (note.equals("auto-immune disorders")) {
                            notes.remove("auto-immune disorders");
                            break;
                        }
                    }
                }
            }
        });
        pitanjePan.add(pitanje);
        pitanjePan.add(inf);
        pitanjePan.add(al);
        return pitanjePan;
    }

    public JPanel getCataract() {
        JPanel pitanjePan = new JPanel();
        pitanjePan.setLayout(new BoxLayout(pitanjePan, BoxLayout.Y_AXIS));
        if (korisnik.getGodine() < 15) {
            //na decu se odnosi
            JLabel pitanje = new JLabel(" Does your patient have metabolic abnormality? ");
            JRadioButton da = new JRadioButton("Yes");
            JRadioButton ne = new JRadioButton("No");
            ButtonGroup bg = new ButtonGroup();
            bg.add(da);
            bg.add(ne);
            da.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    Float probability = sortedMapRBR.get("cataract");
                    boolean found = false;
                    for (String note : notes) {
                        if (note.equals("metabolic abnormality")) {
                            found = true;
                            break;
                        }
                    }
                    if (found == true) {
                        sortedMapRBR.put("retinal_detachment", (probability + 0.05f));
                        notes.add("metabolic abnormality");
                    }
                }
            });

            ne.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    Float probability = sortedMapRBR.get("cataract");
                    for (String note : notes) {
                        if (note.equals("metabolic abnormality")) {
                            sortedMapRBR.put("retinal_detachment", (probability - 0.05f));
                            notes.remove("metabolic abnormality");
                            break;
                        }
                    }
                }
            });
            pitanjePan.add(pitanje);
            pitanjePan.add(da);
            pitanjePan.add(ne);
        }
        JLabel pitanje2 = new JLabel(" Is your patient? ");
        JCheckBox dia = new JCheckBox("Diabetic");
        JCheckBox ste = new JCheckBox("Steroid user");
        JCheckBox smo = new JCheckBox("Smoker");
        dia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (dia.isSelected()) {
                    Float probability = sortedMapRBR.get("cataract");
                    sortedMapRBR.put("cataract", (probability + 0.033f));
                    notes.add("diabetic");
                } else {
                    Float probability = sortedMapRBR.get("cataract");
                    sortedMapRBR.put("cataract", (probability - 0.033f));
                    for (String note : notes) {
                        if (note.equals("diabetic")) {
                            notes.remove("diabetic");
                            break;
                        }
                    }
                }
            }
        });

        ste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (ste.isSelected()) {
                    Float probability = sortedMapRBR.get("cataract");
                    sortedMapRBR.put("cataract", (probability + 0.033f));
                    notes.add("steroid user");
                } else {
                    Float probability = sortedMapRBR.get("cataract");
                    sortedMapRBR.put("cataract", (probability - 0.033f));
                    for (String note : notes) {
                        if (note.equals("steroid user")) {
                            notes.remove("steroid user");
                            break;
                        }
                    }
                }
            }
        });

        smo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (smo.isSelected()) {
                    Float probability = sortedMapRBR.get("cataract");
                    sortedMapRBR.put("cataract", (probability + 0.033f));
                    notes.add("smoker");
                } else {
                    Float probability = sortedMapRBR.get("cataract");
                    sortedMapRBR.put("cataract", (probability - 0.033f));
                    for (String note : notes) {
                        if (note.equals("smoker")) {
                            notes.remove("smoker");
                            break;
                        }
                    }
                }
            }
        });
        pitanjePan.add(pitanje2);
        pitanjePan.add(dia);
        pitanjePan.add(ste);
        pitanjePan.add(smo);
        return pitanjePan;

    }

    public JPanel prolog(String bolest, ArrayList<String> symptoms) {
        JIPEngine engine = new JIPEngine();
        ArrayList<String> tests = new ArrayList<>();

        for (String symptom : symptoms) {
            engine.assertz(engine.getTermParser().parseTerm("bolest(" + bolest + ")."));
            engine.assertz(engine.getTermParser().parseTerm("simptom(" + symptom + ")."));
            engine.assertz(engine.getTermParser().parseTerm("daljeIspitivanje(visual_acuity_test):- bolest(cataract),\\+ simptom(diminished_vision)."));
            engine.assertz(engine.getTermParser().parseTerm("daljeIspitivanje(slit_lamp_examination):- bolest(cataract)."));
            engine.assertz(engine.getTermParser().parseTerm("daljeIspitivanje(retinal_exam):- bolest(cataract)."));

            engine.assertz(engine.getTermParser().parseTerm("daljeIspitivanje(measuring_intraocular_pressure):- bolest(chronic_glaucoma)."));
            engine.assertz(engine.getTermParser().parseTerm("daljeIspitivanje(testing_for_optic_nerve_damage_with_a_dilated_eye_examination_and_imaging_tests):- bolest(chronic_glaucoma)."));
            engine.assertz(engine.getTermParser().parseTerm("daljeIspitivanje(visual_field_test):- bolest(chronic_glaucoma),\\+ simptom(spots_or_clouds_in_vision)."));
            engine.assertz(engine.getTermParser().parseTerm("daljeIspitivanje(measuring_corneal_thickness):- bolest(chronic_glaucoma)."));
            engine.assertz(engine.getTermParser().parseTerm("daljeIspitivanje(inspecting_the_drainage_angle):- bolest(chronic_glaucoma)."));

            engine.assertz(engine.getTermParser().parseTerm("daljeIspitivanje(laboratory_analysis_of_the_liquid_that_drains_from_eye):- bolest(conjunctivitis)."));

            engine.assertz(engine.getTermParser().parseTerm("daljeIspitivanje(examination_of_eyelids):- bolest(blepharitis)."));
            engine.assertz(engine.getTermParser().parseTerm("daljeIspitivanje(swabbing_skin_for_testing):- bolest(blepharitis)."));

            engine.assertz(engine.getTermParser().parseTerm("daljeIspitivanje(examination_of_the_back_of_eye):- bolest(macular_degeneration),\\+ simptom(spots_or_clouds_in_vision)."));
            engine.assertz(engine.getTermParser().parseTerm("daljeIspitivanje(test_for_defects_in_the_center_of_vision):- bolest(macular_degeneration),\\+ simptom(spots_or_clouds_in_vision)."));
            engine.assertz(engine.getTermParser().parseTerm("daljeIspitivanje(fluorescein_angiography):- bolest(macular_degeneration)."));
            engine.assertz(engine.getTermParser().parseTerm("daljeIspitivanje(optical_coherence_tomography):- bolest(macular_degeneration)."));

            engine.assertz(engine.getTermParser().parseTerm("daljeIspitivanje(comprehensive_eye_exam):- bolest(dry_eye_of_unknown_cause)."));
            engine.assertz(engine.getTermParser().parseTerm("daljeIspitivanje(measuring_the_volume_of_tears):- bolest(dry_eye_of_unknown_cause),\\+ simptom(lacrimation)."));
            engine.assertz(engine.getTermParser().parseTerm("daljeIspitivanje(determining_the_quality_of_tears):- bolest(dry_eye_of_unknown_cause)."));

            engine.assertz(engine.getTermParser().parseTerm("daljeIspitivanje(referral_to_home_health_care_service):- bolest(eye_alignment_disorder)."));
            engine.assertz(engine.getTermParser().parseTerm("daljeIspitivanje(ophtalmic_examination_and_evaluation):- bolest(eye_alignment_disorder)."));

            engine.assertz(engine.getTermParser().parseTerm("daljeIspitivanje(wound_care_management):- bolest(corneal_abrasion)."));
            engine.assertz(engine.getTermParser().parseTerm("daljeIspitivanje(ophtalmic_examination_and_evaluation):- bolest(corneal_abrasion)."));

            engine.assertz(engine.getTermParser().parseTerm("daljeIspitivanje(ophtalmic_examination_and_evaluation):- bolest(cornea_infection)."));
            engine.assertz(engine.getTermParser().parseTerm("daljeIspitivanje(ophtalmologic_and_otologic_diagnosis_and_treatment):- bolest(cornea_infection)."));

            engine.assertz(engine.getTermParser().parseTerm("daljeIspitivanje(retinal_examination):- bolest(retinal_detachment)."));
            engine.assertz(engine.getTermParser().parseTerm("daljeIspitivanje(ultrasound_imaging):- bolest(retinal_detachment), simptom(spots_or_clouds_in_vision)."));

            engine.assertz(engine.getTermParser().parseTerm("daljeIspitivanje(ophtalmic_examination_and_evaluation):- bolest(optic_neuritis)."));
            engine.assertz(engine.getTermParser().parseTerm("daljeIspitivanje(ophthalmoscopy):- bolest(optic_neuritis)."));
            engine.assertz(engine.getTermParser().parseTerm("daljeIspitivanje(pupillary_light_reaction_test):- bolest(optic_neuritis)."));

            engine.assertz(engine.getTermParser().parseTerm("daljeIspitivanje(ophtalmic_examination_and_evaluation):- bolest(iridocyclitis)."));
            engine.assertz(engine.getTermParser().parseTerm("daljeIspitivanje(ophtalmologic_and_otologic_diagnosis_and_treatment):- bolest(iridocyclitis)."));

            engine.assertz(engine.getTermParser().parseTerm("daljeIspitivanje(ophtalmic_examination_and_evaluation):- bolest(subconjunctival_hemorrhage)."));
            engine.assertz(engine.getTermParser().parseTerm("daljeIspitivanje(blood_test):- bolest(subconjunctival_hemorrhage)."));

            engine.assertz(engine.getTermParser().parseTerm("daljeIspitivanje(eye_dilation):- bolest(floaters)."));
            engine.assertz(engine.getTermParser().parseTerm("daljeIspitivanje(vitreous_examination):- bolest(floaters)."));

            JIPQuery query2 = engine.openSynchronousQuery("daljeIspitivanje(X).");

            JIPTerm solution;
            while ((solution = query2.nextSolution()) != null) {
                System.out.println("solution: " + solution);
                for (JIPVariable var : solution.getVariables()) {
                    System.out.println(var.getName() + "=" + var.getValue());
                }
            }
            JIPQuery query = engine.openSynchronousQuery("daljeIspitivanje(X).");
            while ((solution = query.nextSolution()) != null) {
                for (JIPVariable var : solution.getVariables()) {
                    boolean found = false;
                    for (String test : tests) {
                        if (test.equals(var.getValue().toString())) {
                            found = true;
                            break;
                        }
                    }
                    if (found == false) {
                        tests.add(var.getValue().toString());
                    }
                }
            }
        }

        JPanel res = new JPanel();
        res.setLayout(new BoxLayout(res, BoxLayout.Y_AXIS));
        JLabel rec = new JLabel(" Possible additional tests are:");
        res.add(rec);
        int brojac = 1;
        for (String isp : tests) {
            isp = isp.substring(0, 1).toUpperCase() + isp.substring(1);
            isp = "        " + brojac + ". " + isp.replaceAll("_", " ");
            JLabel res1 = new JLabel(isp);
            res.add(res1);
            brojac++;
        }
        return res;
    }

}
