package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import Actions.CbrApplication;
import example.RacunanjeTopBolesti;
import example.RangLista;
import model.Osoba;
import unbbayes.io.BaseIO;
import unbbayes.io.NetIO;
import unbbayes.io.exception.LoadException;
import unbbayes.prs.Node;
import unbbayes.prs.bn.JunctionTreeAlgorithm;
import unbbayes.prs.bn.ProbabilisticNetwork;
import unbbayes.prs.bn.ProbabilisticNode;
import unbbayes.util.extension.bn.inference.IInferenceAlgorithm;

public class SelectSymptoms extends JFrame{
	String navedeniSimptomi="";
	public static String bolest1="";
	public static String bolest2="";
	public static String bolest3="";
    ArrayList<String>simpto = new ArrayList<>();
	private JFrame mainFrame = new JFrame("Ophthalmology");
	private JPanel checkPanel=new JPanel();
	private JPanel mainPanel=new JPanel(new BorderLayout());
	private JPanel descPanel=new JPanel(new BorderLayout());
	private ArrayList<HashMap<String,Map<String, Float>>> sveRangListe=new ArrayList<HashMap<String,Map<String, Float>>>();
	private ArrayList<HashMap<String,Map<String, Float>>> sveRangListe2=new ArrayList<HashMap<String,Map<String, Float>>>();

	private String diOp="";
	private String paOp="";
	private String reOp="";
	private String doOp="";
	private String laOp="";
	private String foOp="";
	private String swOp="";
	private String clOp="";
	private String blOp="";
	private String spOp="";
	private String buOp="";
	private String whOp="";
	private String itOp="";
	public SelectSymptoms(Osoba o,String jmbg){

		try {
		ProbabilisticNetwork net = new ProbabilisticNetwork("example");
		
		 BaseIO io = new NetIO();
		
			
				net = (ProbabilisticNetwork)io.load(new File("./probicaBajesa.net"));
		 //net = (ProbabilisticNetwork)io.load(new File("./simptomiBajes2.net"));
			
	
	
		 IInferenceAlgorithm algorithm = new JunctionTreeAlgorithm();
		 algorithm.setNetwork(net);
		 algorithm.run();
		
			
		ProbabilisticNode godine = (ProbabilisticNode)net.getNode("Age");
		ProbabilisticNode rasa = (ProbabilisticNode)net.getNode("Race");
		ProbabilisticNode pol = (ProbabilisticNode)net.getNode("Sex");
		int rasaIndex=0;
		int polIndex=0;
		int godIndex = 0;

		if(o.getRasa().equals("Black")) {
			rasaIndex=0;
		}else if(o.getRasa().equals("Hispanic")) {
			rasaIndex=1;
		}else if(o.getRasa().equals("White")) {
			rasaIndex=2;
		}else {
			rasaIndex=3;
		}
		
		if(o.getPol().equals("Male")) {
			polIndex=0;
		}else {
			polIndex=1;
		}
		
		int g=o.getGodine();
		if(g==0) {
			godIndex=0;
		}else if(g>=1 && g<5) {
			godIndex=1;
		}else if(g>=5 && g<15) {
			godIndex=2;
		}else if(g>=15 && g<30) {
			godIndex=3;
		}else if(g>=30 && g<45) {
			godIndex=4;
		}else if(g>=45 && g<60) {
			godIndex=5;
		}else if(g>=60 && g<75 ) {
			godIndex=6;
		}else if(g>=75) {
			godIndex=7;
		}
		godine.addFinding(godIndex);
		rasa.addFinding(rasaIndex);
		pol.addFinding(polIndex);
			
		try {
	       	net.updateEvidences();
	    } catch (Exception e) {
	        System.out.println(e.getMessage());               	
	    }
	       //System.out.println(rasaIndex+" "+polIndex+" "+godIndex);

		List<Node> nodeList = net.getNodes();

		Border blackline = BorderFactory.createLineBorder(Color.BLACK);
		TitledBorder title = BorderFactory.createTitledBorder(blackline, "Description of symptom");
		title.setTitleJustification(TitledBorder.CENTER);
		
		Border blackline2 = BorderFactory.createLineBorder(Color.BLACK);
		TitledBorder title2 = BorderFactory.createTitledBorder(blackline2, "Probability of diseases");
		title2.setTitleJustification(TitledBorder.CENTER);
		
		JTextArea opSimptoma=new JTextArea();
		opSimptoma.setBorder(title);
		opSimptoma.setEditable(false);
		opSimptoma.setSize(new Dimension(500, 200));
		opSimptoma.setWrapStyleWord(true);
		opSimptoma.setLineWrap(true);
		descPanel.add(opSimptoma,BorderLayout.NORTH);
		
		JTextArea konacneVrv=new JTextArea();
		konacneVrv.setBorder(title2);
		konacneVrv.setEditable(false);
		descPanel.add(konacneVrv,BorderLayout.CENTER);
		
		//JTextField daljeIspitivanja=new JTextField("Further tests are possible");
		JLabel daljeIspitivanja=new JLabel("Further tests are possible");
		daljeIspitivanja.setSize(new Dimension(60,30));
		ImageIcon med = new ImageIcon("./medicalTest.png");
		Image medIm = med.getImage(); // transform it 
		Image newmedimg = medIm.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		med = new ImageIcon(newmedimg);
		JButton daljaIsBut=new JButton("See here",med);
		daljaIsBut.setSize(new Dimension(30,50));
		daljeIspitivanja.setVisible(false);
		daljaIsBut.setVisible(false);
		JPanel daljaPan=new JPanel(new FlowLayout());
		daljaPan.add(daljeIspitivanja);
		daljaPan.add(daljaIsBut);
		descPanel.add(daljaPan,BorderLayout.SOUTH);
		
		daljaIsBut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				mainFrame.dispose();
				DaljaIspitivanja di=new DaljaIspitivanja(o,bolest1,bolest2,bolest3);
			}
		});
		
		
		checkPanel.setLayout(new BoxLayout(checkPanel, BoxLayout.Y_AXIS));
		JCheckBox dim_vi=new JCheckBox("Diminished vision");
		JCheckBox pain_eye=new JCheckBox("Pain in eye");
		JCheckBox redness=new JCheckBox("Eye redness");
		JCheckBox dob_vi=new JCheckBox("Double vision");
		JCheckBox lacr=new JCheckBox("Lacrimation");
		JCheckBox forig=new JCheckBox("Foreign body sensation in eye");
		JCheckBox swol=new JCheckBox("Swollen eye");
		JCheckBox clo=new JCheckBox("Cloudy eye");
		JCheckBox bli=new JCheckBox("Blindness");
		JCheckBox spots=new JCheckBox("Spots or clouds in vision");
		JCheckBox burn=new JCheckBox("Eye burns or stings");
		JCheckBox white=new JCheckBox("White discharge from eye");
		JCheckBox itchi=new JCheckBox("Itchiness of eye");
		
		ImageIcon fin = new ImageIcon("./finish.png");
		Image finIm = fin.getImage(); // transform it 
		Image newimgF = finIm.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		fin = new ImageIcon(newimgF);
		JButton gotovo=new JButton("RBR ",fin);
	
		for (Node node : nodeList) {
			if(node.getName().equals("eye_redness")) {reOp=node.getDescription();}
			else if(node.getName().equals("pain_in_eye")) {paOp=node.getDescription();}
			else if(node.getName().equals("diminished_vision")) {diOp=node.getDescription();}
			else if(node.getName().equals("lacrimation")) {laOp=node.getDescription();}
			else if(node.getName().equals("double_vision")) {doOp=node.getDescription();}
			else if(node.getName().equals("swollen_eye")) {swOp=node.getDescription();}
			else if(node.getName().equals("foreign_body_sensation_in_eye")) {foOp=node.getDescription();}
			else if(node.getName().equals("cloudy_eye")) {clOp=node.getDescription();}
			else if(node.getName().equals("blidness")) {blOp=node.getDescription();}
			else if(node.getName().equals("spots_or_clouds_in_vision")) {spOp=node.getDescription();}
			else if(node.getName().equals("eye_burns_or_stings")) {buOp=node.getDescription();}
			else if(node.getName().equals("white_discharge_from_eye")) {whOp=node.getDescription();}
			else if(node.getName().equals("itchiness_of_eye")) {itOp=node.getDescription();}
		}
			itchi.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					opSimptoma.setText(itOp);
				}
			});
			white.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					opSimptoma.setText(whOp);
				}
			});
			burn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					opSimptoma.setText(buOp);
				}
			});
			spots.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					opSimptoma.setText(spOp);
				}
			});
			bli.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					opSimptoma.setText(blOp);
				}
			});
			clo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				opSimptoma.setText(clOp);
			}
		});
		forig.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				opSimptoma.setText(foOp);
			}
		});
		swol.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				opSimptoma.setText(swOp);
			}
		});
		lacr.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				opSimptoma.setText(laOp);
			}
		});
		dob_vi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				opSimptoma.setText(doOp);
			}
		});
	
		redness.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				opSimptoma.setText(reOp);
			}
		});
		
		pain_eye.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				opSimptoma.setText(paOp);
			}
		});
		
		dim_vi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				opSimptoma.setText(diOp);	
			}
		});
		////////////////////////////////////////////////////////////////////////////
		JPanel pr=new JPanel();
		//pr.setSize(new Dimension(300, 150));
		//pr.setPreferredSize(new Dimension(300, 150));
		Border blackline3 = BorderFactory.createLineBorder(Color.BLACK);
		TitledBorder title3 = BorderFactory.createTitledBorder(blackline3, "Suggestion for better diagnosis");
		title3.setTitleJustification(TitledBorder.CENTER);
		
		JTextArea predlog=new JTextArea();
		predlog.setBorder(title3);
		predlog.setEditable(false);
		predlog.setSize(new Dimension(780, 130));
		predlog.setPreferredSize(new Dimension(780, 130));
		predlog.setWrapStyleWord(true);
		predlog.setLineWrap(true);
		pr.add(predlog);
		///////////////////////////////////////////////////////////////////////////////
		
		gotovo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//////////////////////////////////////////////////////////////////////////////////////
				//ArrayList<String> selektovaniSimptomi=new ArrayList<String>();
				Set<String> selektovaniSimptomi=new HashSet<String>();
				//////////////////////////////////////////////////////////////////////////////////
				sveRangListe=new ArrayList<>();
				for (Node node : nodeList) {
					if(dim_vi.isSelected()) {
						selektovaniSimptomi.add("diminished vision");
						if(node.getName().equals("diminished_vision")) {
							HashMap<String,Float> map=new HashMap<String,Float>();
							for (int i = 0; i < node.getStatesSize(); i++) { 
								map.put(node.getStateAt(i),((ProbabilisticNode)node).getMarginalAt(i));
							}
							Map<String, Float> rang=RangLista.sortByComparator(map, false);
							HashMap<String,Map<String, Float>> simptomLista=new HashMap<String,Map<String, Float>>();
							simptomLista.put("diminished_vision", rang); //hash mapa simptom / rang lista
							sveRangListe.add(simptomLista);
						}
					}
					if(redness.isSelected()) {
						selektovaniSimptomi.add("eye redness");
						if(node.getName().equals("eye_redness")) {
							HashMap<String,Float> map=new HashMap<String,Float>();
							for (int i = 0; i < node.getStatesSize(); i++) { 
								map.put(node.getStateAt(i),((ProbabilisticNode)node).getMarginalAt(i));
							}
							Map<String, Float> rang=RangLista.sortByComparator(map, false);
							HashMap<String,Map<String, Float>> simptomLista=new HashMap<String,Map<String, Float>>();
							simptomLista.put("eye_redness", rang); //hash mapa simptom / rang lista
							sveRangListe.add(simptomLista);
						}
					}
					if(pain_eye.isSelected()) {
						selektovaniSimptomi.add("pain in eye");
						if(node.getName().equals("pain_in_eye")) {
							HashMap<String,Float> map=new HashMap<String,Float>();
							for (int i = 0; i < node.getStatesSize(); i++) { 
								map.put(node.getStateAt(i),((ProbabilisticNode)node).getMarginalAt(i));
							}
							Map<String, Float> rang=RangLista.sortByComparator(map, false);
							HashMap<String,Map<String, Float>> simptomLista=new HashMap<String,Map<String, Float>>();
							simptomLista.put("pain_in_eye", rang); //hash mapa simptom / rang lista
							sveRangListe.add(simptomLista);
						}
					}
					if(lacr.isSelected()) {
						selektovaniSimptomi.add("lacrimation");
						if(node.getName().equals("lacrimation")) {
							HashMap<String,Float> map=new HashMap<String,Float>();
							for (int i = 0; i < node.getStatesSize(); i++) { 
								map.put(node.getStateAt(i),((ProbabilisticNode)node).getMarginalAt(i));
							}
							Map<String, Float> rang=RangLista.sortByComparator(map, false);
							HashMap<String,Map<String, Float>> simptomLista=new HashMap<String,Map<String, Float>>();
							simptomLista.put("lacrimation", rang); //hash mapa simptom / rang lista
							sveRangListe.add(simptomLista);
						}
					}	
					if(dob_vi.isSelected()) {
						selektovaniSimptomi.add("double vision");
						if(node.getName().equals("double_vision")) {
							HashMap<String,Float> map=new HashMap<String,Float>();
							for (int i = 0; i < node.getStatesSize(); i++) { 
								map.put(node.getStateAt(i),((ProbabilisticNode)node).getMarginalAt(i));
							}
							Map<String, Float> rang=RangLista.sortByComparator(map, false);
							HashMap<String,Map<String, Float>> simptomLista=new HashMap<String,Map<String, Float>>();
							simptomLista.put("double_vision", rang); //hash mapa simptom / rang lista
							sveRangListe.add(simptomLista);
						}
					}
					if(swol.isSelected()) {
						selektovaniSimptomi.add("swollen eye");
						if(node.getName().equals("swollen_eye")) {
							HashMap<String,Float> map=new HashMap<String,Float>();
							for (int i = 0; i < node.getStatesSize(); i++) { 
								map.put(node.getStateAt(i),((ProbabilisticNode)node).getMarginalAt(i));
							}
							Map<String, Float> rang=RangLista.sortByComparator(map, false);
							HashMap<String,Map<String, Float>> simptomLista=new HashMap<String,Map<String, Float>>();
							simptomLista.put("swollen_eye", rang); //hash mapa simptom / rang lista
							sveRangListe.add(simptomLista);
						}
					}
					if(forig.isSelected()) {
						selektovaniSimptomi.add("foreign body sensation in eye");
						if(node.getName().equals("foreign_body_sensation_in_eye")) {
							HashMap<String,Float> map=new HashMap<String,Float>();
							for (int i = 0; i < node.getStatesSize(); i++) { 
								map.put(node.getStateAt(i),((ProbabilisticNode)node).getMarginalAt(i));
							}
							Map<String, Float> rang=RangLista.sortByComparator(map, false);
							HashMap<String,Map<String, Float>> simptomLista=new HashMap<String,Map<String, Float>>();
							simptomLista.put("foreign_body_sensation_in_eye", rang); //hash mapa simptom / rang lista
							sveRangListe.add(simptomLista);
						}
					}
					if(clo.isSelected()) {
						selektovaniSimptomi.add("cloudy eye");
						if(node.getName().equals("cloudy_eye")) {
							HashMap<String,Float> map=new HashMap<String,Float>();
							for (int i = 0; i < node.getStatesSize(); i++) { 
								map.put(node.getStateAt(i),((ProbabilisticNode)node).getMarginalAt(i));
							}
							Map<String, Float> rang=RangLista.sortByComparator(map, false);
							HashMap<String,Map<String, Float>> simptomLista=new HashMap<String,Map<String, Float>>();
							simptomLista.put("cloudy_eye", rang); //hash mapa simptom / rang lista
							sveRangListe.add(simptomLista);
						}
					}
					if(bli.isSelected()) {
						selektovaniSimptomi.add("blidness");
						if(node.getName().equals("blidness")) {
							HashMap<String,Float> map=new HashMap<String,Float>();
							for (int i = 0; i < node.getStatesSize(); i++) { 
								map.put(node.getStateAt(i),((ProbabilisticNode)node).getMarginalAt(i));
							}
							Map<String, Float> rang=RangLista.sortByComparator(map, false);
							HashMap<String,Map<String, Float>> simptomLista=new HashMap<String,Map<String, Float>>();
							simptomLista.put("blidness", rang); //hash mapa simptom / rang lista
							sveRangListe.add(simptomLista);
						}
					}
					if(spots.isSelected()) {
						selektovaniSimptomi.add("spots or clouds in vision");
						if(node.getName().equals("spots_or_clouds_in_vision")) {
							HashMap<String,Float> map=new HashMap<String,Float>();
							for (int i = 0; i < node.getStatesSize(); i++) { 
								map.put(node.getStateAt(i),((ProbabilisticNode)node).getMarginalAt(i));
							}
							Map<String, Float> rang=RangLista.sortByComparator(map, false);
							HashMap<String,Map<String, Float>> simptomLista=new HashMap<String,Map<String, Float>>();
							simptomLista.put("spots_or_clouds_in_vision", rang); //hash mapa simptom / rang lista
							sveRangListe.add(simptomLista);
						}
					}
					if(burn.isSelected()) {
						selektovaniSimptomi.add("eye burns or stings");
						if(node.getName().equals("eye_burns_or_stings")) {
							HashMap<String,Float> map=new HashMap<String,Float>();
							for (int i = 0; i < node.getStatesSize(); i++) { 
								map.put(node.getStateAt(i),((ProbabilisticNode)node).getMarginalAt(i));
							}
							Map<String, Float> rang=RangLista.sortByComparator(map, false);
							HashMap<String,Map<String, Float>> simptomLista=new HashMap<String,Map<String, Float>>();
							simptomLista.put("eye_burns_or_stings", rang); //hash mapa simptom / rang lista
							sveRangListe.add(simptomLista);
						}
					}
					if(white.isSelected()) {
						selektovaniSimptomi.add("white discharge from eye");
						if(node.getName().equals("white_discharge_from_eye")) {
							HashMap<String,Float> map=new HashMap<String,Float>();
							for (int i = 0; i < node.getStatesSize(); i++) { 
								map.put(node.getStateAt(i),((ProbabilisticNode)node).getMarginalAt(i));
							}
							Map<String, Float> rang=RangLista.sortByComparator(map, false);
							HashMap<String,Map<String, Float>> simptomLista=new HashMap<String,Map<String, Float>>();
							simptomLista.put("white_discharge_from_eye", rang); //hash mapa simptom / rang lista
							sveRangListe.add(simptomLista);
						}
					}
					if(itchi.isSelected()) {
						selektovaniSimptomi.add("itchiness of eye");
						if(node.getName().equals("itchiness_of_eye")) {
							HashMap<String,Float> map=new HashMap<String,Float>();
							for (int i = 0; i < node.getStatesSize(); i++) { 
								map.put(node.getStateAt(i),((ProbabilisticNode)node).getMarginalAt(i));
							}
							Map<String, Float> rang=RangLista.sortByComparator(map, false);
							HashMap<String,Map<String, Float>> simptomLista=new HashMap<String,Map<String, Float>>();
							simptomLista.put("itchiness_of_eye", rang); //hash mapa simptom / rang lista
							sveRangListe.add(simptomLista);
						}
					}
					
					
				}
				System.out.println("////////////////////////////////////////////////");
				for(HashMap<String,Map<String, Float>> mapa :sveRangListe) {
					for(String simptom : mapa.keySet()) {
						System.out.println(simptom+"/n");
						RangLista.printMap(mapa.get(simptom));
					}
				}
				System.out.println("-----------------------------");
				RacunanjeTopBolesti racunanje=new RacunanjeTopBolesti(sveRangListe);
				HashMap<String,Float> izracunate=racunanje.racunanje();
				Map<String, Float> sortirane=RangLista.sortByComparator(izracunate, false);
				RangLista.printMap(sortirane);
				konacneVrv.setText(racunanje.ispisivanjeVerovatnoca(sortirane));
				
				///////////////////////////////////////////////////////////////////////////
				
				
				RangLista rg=new RangLista();
				predlog.setText(rg.prve2Naziv(sortirane, selektovaniSimptomi));
				boolean daljaIspBol=rg.proveraRazlikeVerovatnocaZaDaljaIspitivanja(sortirane);
				if(daljaIspBol==true) {
					daljaIsBut.setVisible(true);
					daljeIspitivanja.setVisible(true);
				}else {
					daljaIsBut.setVisible(false);
					daljeIspitivanja.setVisible(false);
				}
				//////////////////////////////////////////////////////////////////////////
			}
		});
		
		JButton g2 = new JButton("CBR ",fin);
		g2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CbrApplication.mapp=new HashMap<String,Map<String,Float>>();
				
				if(dim_vi.isSelected())
					simpto.add("diminished vision");
				if(pain_eye.isSelected())
					simpto.add("pain in eye");
				if(redness.isSelected())
					simpto.add("eye redness");
				if(dob_vi.isSelected())
					simpto.add("double vision");
				if(lacr.isSelected())
					simpto.add("lacrimation");
				if(forig.isSelected())
					simpto.add("foreign body sensation in eye");
				if(swol.isSelected())
					simpto.add("swollen eye");
				if(clo.isSelected())
				    simpto.add("cloudy eye");
				if(bli.isSelected())
					simpto.add("Blindness");
				if(spots.isSelected())
				   simpto.add("spots of clouds in vision");
				if(burn.isSelected())
				   simpto.add("eye burns of stings");
				if(white.isSelected())
				   simpto.add("white discharge from eye");
				if(itchi.isSelected())
					simpto.add("itchiness of eye");
			
				CbrApplication a = new CbrApplication();	
			    a.mainS(o, simpto);

				sveRangListe2.add(CbrApplication.mapp);

				RacunanjeTopBolesti racunanje=new RacunanjeTopBolesti(sveRangListe2);
				HashMap<String,Float> izracunate=racunanje.racunanje();
				Map<String, Float> sortirane=RangLista.sortByComparator(izracunate, false);
				RangLista.printMap(sortirane);
				konacneVrv.setText(racunanje.ispisivanjeVerovatnoca(sortirane));
			}
		});
		
		JLabel simpt=new JLabel("Symptoms: ");
		checkPanel.add(simpt);
		checkPanel.add(itchi);
		checkPanel.add(white);
		checkPanel.add(burn);
		checkPanel.add(spots);
		checkPanel.add(bli);
		checkPanel.add(clo);
		checkPanel.add(swol);
		checkPanel.add(forig);
		checkPanel.add(lacr);
		checkPanel.add(dim_vi);
		checkPanel.add(pain_eye);
		checkPanel.add(redness);
		checkPanel.add(dob_vi);
		ImageIcon donIm = new ImageIcon("./done.png");
		Image doneImg = donIm.getImage(); // transform it 
		Image newDoneImg = doneImg.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		donIm = new ImageIcon(newDoneImg);
		JButton done = new JButton("Done",donIm);
		done.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				simpto=new ArrayList<String>();
				if(dim_vi.isSelected())
					simpto.add("diminished vision");
				if(pain_eye.isSelected())
					simpto.add("pain in eye");
				if(redness.isSelected())
					simpto.add("eye redness");
				if(dob_vi.isSelected())
					simpto.add("double vision");
				if(lacr.isSelected())
					simpto.add("lacrimation");
				if(forig.isSelected())
					simpto.add("foreign body sensation in eye");
				if(swol.isSelected())
					simpto.add("swollen eye");
				if(clo.isSelected())
				    simpto.add("cloudy eye");
				if(bli.isSelected())
					simpto.add("blidness");
				if(spots.isSelected())
				   simpto.add("spots of clouds in vision");
				if(burn.isSelected())
				   simpto.add("eye burns of stings");
				if(white.isSelected())
				   simpto.add("white discharge from eye");
				if(itchi.isSelected())
					simpto.add("itchiness of eye");			
				
				for(String simpton : simpto) {
					navedeniSimptomi+=simpton;
					navedeniSimptomi+=", ";
				}
				try {
					navedeniSimptomi = navedeniSimptomi.substring(0, navedeniSimptomi.length() - 2);
					System.out.println(navedeniSimptomi);
				}catch (Exception e){}
				AddData addDataFrame=new AddData(navedeniSimptomi,jmbg);
				mainFrame.dispose();
			}
		});
		checkPanel.add(gotovo);
	    checkPanel.add(g2);
	    checkPanel.add(done);
		ImageIcon water = new ImageIcon("./eyeIcon.png");
		Image image = water.getImage(); // transform it 
		Image newimg = image.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		water = new ImageIcon(newimg);
		JButton bbb=new JButton("Back",water);
		bbb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					mainFrame mf=new mainFrame();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				mainFrame.dispose();
			}
		});
		JPanel tug=new JPanel(new BorderLayout());
		///////////////////////////////////////////////tug.add(checkPanel,BorderLayout.WEST);
		tug.add(checkPanel,BorderLayout.WEST);
		tug.add(descPanel,BorderLayout.EAST);
		JPanel proba=new JPanel(new BorderLayout());
		proba.add(tug,BorderLayout.NORTH);
		proba.add(pr,BorderLayout.SOUTH);
		mainPanel.add(proba,BorderLayout.CENTER);
		mainPanel.add(bbb,BorderLayout.SOUTH);
		mainFrame.add(mainPanel);
		mainFrame.setSize(800, 700);
		} catch (LoadException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
	}

}
