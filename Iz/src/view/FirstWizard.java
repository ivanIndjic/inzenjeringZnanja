package view;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;

public class FirstWizard extends JFrame{
	
private JFrame mainFrame = new JFrame("OFT");
private String pol;
private JPanel mainPanel = new JPanel();
private String rasa;
private ArrayList<Object> podaci = new ArrayList<>();
public ArrayList<Object> createFrame() {
	
	JPanel p1 = new JPanel();
	JLabel gender = new JLabel("Gender:      ");
	p1.setLayout(new FlowLayout());
	p1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	JRadioButton b1 = new JRadioButton("M");
	b1.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			pol = "Musko";
		}
	});
	
	JRadioButton b2 = new JRadioButton("F");
	b2.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			pol = "Zensko";
		}
	});
	ButtonGroup group = new ButtonGroup();
	group.add(b1);
	group.add(b2);
	p1.add(gender);
	p1.add(b1);
	p1.add(b2);
	JLabel year = new JLabel("How old are you?             ");
	JTextField input = new  JTextField();
	input.setPreferredSize(new Dimension(40, 20));
	JLabel slike = new JLabel(new ImageIcon("C:\\Users\\Ivan\\Desktop\\projImage.jpg"));
	ImageIcon i = new ImageIcon("C:\\Users\\Ivan\\Desktop\\projImage.jpg");
	input.addKeyListener(new KeyAdapter() {
         public void keyTyped(KeyEvent e) {
           char c = e.getKeyChar();
           if (!(Character.isDigit(c) ||
              (c == KeyEvent.VK_BACK_SPACE) ||
              (c == KeyEvent.VK_DELETE))) {
                e.consume();
              }
         }
       });
	String godine = input.getText();
JPanel p2 = new JPanel();
p2.setLayout(new FlowLayout());

p2.add(year);
p2.add(input);

JLabel race = new JLabel("Race:       ");
JRadioButton b3= new JRadioButton("Black");
JRadioButton b4 = new JRadioButton("Hispanic");
JRadioButton b5 = new JRadioButton("White");
JRadioButton b6 = new JRadioButton("Other");
b3.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		rasa = "Black";
	}
});
b4.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		rasa = "Hispanic";
	}
});
b5.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		rasa = "White";
	}
});
b6.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		rasa = "Other";
	}
});
ButtonGroup bb = new ButtonGroup();

bb.add(b3);
bb.add(b4);
bb.add(b5);
bb.add(b6);

JPanel p3 = new JPanel();
p3.setLayout(new FlowLayout());
p3.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
p3.add(race);
p3.add(b3);
p3.add(b4);
p3.add(b5);
p3.add(b6);
mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

JLabel l = new JLabel();
l.setIcon(i);
mainFrame.add(l);
mainPanel.add(p1);
mainPanel.add(p2);
mainPanel.add(p3);
JButton bbb = new JButton("Next");
mainPanel.add(bbb);
mainFrame.add(mainPanel);

mainFrame.setSize(800, 200);

mainFrame.setLocationRelativeTo(null);
mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
mainFrame.setVisible(true);
podaci.add(pol);
podaci.add(input.getText());
podaci.add(race);
System.out.println("Pozvano");
return podaci;
}
 
}
