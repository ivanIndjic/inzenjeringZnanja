package view;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import model.Osoba;

public class mainFrame extends JFrame{
public JFrame mainFr = new JFrame("Oftalmology");
private int sel=-500;	
	public mainFrame() throws SQLException {
	
		JPanel gl = new JPanel();
		gl.setLayout(new BoxLayout(gl, BoxLayout.Y_AXIS));
		JPanel pacijent = new JPanel(new BorderLayout());
		pacijent.setName("Patients");
		
		String[] columnNames = {
				"Name", "Last name","Age","Address","Phone","Mail","Subscribed doctor","JMBG","Gender","Race"      
              };
	
		String sql="SELECT * FROM karton";
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inzenjering?useSSL=false","root","root");

		PreparedStatement st = conn.prepareStatement(sql);
		DefaultTableModel dtm=new DefaultTableModel(columnNames,0);
		JTable table=new JTable(dtm);	
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		ResultSet rs = st.executeQuery(sql);
		while(rs.next())
		{
		    String ime = rs.getString("Ime");
		    String prezime = rs.getString("Prezime");
		    String god = rs.getString("Godina");
		    String adr = rs.getString("Adresa");
		    String brTel = rs.getString("Telefon");
		    String mail = rs.getString("Mail");
		    String lekar = rs.getString("zaduzeniLekar");
		    String jmbg = rs.getString("JMBG");
		    String pol = rs.getString("Pol");
		    String rasa = rs.getString("Rasa");
		    
		    
		    dtm.addRow(new Object[]{ime,prezime,god,adr,brTel,mail,lekar,jmbg,pol,rasa});
		}

		 table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
		        public void valueChanged(ListSelectionEvent event) {
		           sel =table.getSelectedRow();
		        }
		    });
		
		table.setDefaultEditor(Object.class, null);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		ImageIcon fin = new ImageIcon("resources/user.png");
		java.awt.Image finIm = fin.getImage(); // transform it 
		java.awt.Image newimgF = finIm.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		fin = new ImageIcon(newimgF);
		JButton user=new JButton("Add User           ",fin);
		
		user.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					AddUser pp = new AddUser();
					mainFr.dispose();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		ImageIcon fin2 = new ImageIcon("resources/userR.png");
		java.awt.Image finIm2 = fin2.getImage(); // transform it 
		java.awt.Image newimgF2 = finIm2.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		fin2 = new ImageIcon(newimgF2);
		JButton userR=new JButton("Remove User    ",fin2);
		
		ImageIcon fin3 = new ImageIcon("resources/edit2.png");
		java.awt.Image finIm3 = fin3.getImage(); // transform it 
		java.awt.Image newimgF3 = finIm3.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		fin3 = new ImageIcon(newimgF3);
		JButton userE=new JButton("Edit User           ",fin3);
		userE.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				 if(sel==-500) {
					 JOptionPane.showMessageDialog(null,
							    "Select user first!");	 
				 }	else {
					 Vector<String> vektor = (Vector<String>) ((DefaultTableModel) table.getModel()).getDataVector().elementAt(table.getSelectedRow());
				  try {
					EditUser iw = new EditUser(vektor);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				  mainFr.dispose();
				 }	
			}
		});
		
		userR.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			 if(sel==-500) {
				 JOptionPane.showMessageDialog(null,
						    "Select user first!");	 
			 }	
			 else
			 {
				 String podatak = (String) table.getValueAt(sel, getColumnByName(table, "JMBG"));
				 try {
		 				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inzenjering","root","password");
		 				
		 				//za kolone koje nisu navedene bice iskoriscena default vrednost
		 				String sql = "DELETE FROM Karton WHERE JMBG = '"+podatak+"';";	
		 				String sql2 = "DELETE FROM IP WHERE JMBG = '"+podatak+"';";	
		 				PreparedStatement pstmt = conn.prepareStatement(sql);
		 				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
		 				int updated = pstmt.executeUpdate();
		 				int updated2 = pstmt2.executeUpdate();
		 				pstmt.close();	 				
		 			} catch (Exception ee) {
		 				ee.printStackTrace();
		 			}

				 ((DefaultTableModel)table.getModel()).removeRow(sel);
				 table.repaint();
				 table.revalidate();
				 pacijent.repaint();
				 pacijent.revalidate();
			 }
			}
		});
		
		ImageIcon simptomiIm = new ImageIcon("resources/symptoms.png");
		java.awt.Image simptomiIm2 = simptomiIm.getImage(); // transform it 
		java.awt.Image simptomiF2 = simptomiIm2.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		simptomiIm = new ImageIcon(simptomiF2);
		JButton simptomi=new JButton("Add symptoms ",simptomiIm);
		simptomi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				 if(sel==-500) {
					 JOptionPane.showMessageDialog(null,
							    "Select user first!");	 
				 }	
				 else
				 {
					Vector<String> selectedRow = (Vector<String>) ((DefaultTableModel) table.getModel()).getDataVector().elementAt(table.getSelectedRow());
	
					String godString = selectedRow.get(2);
					int god=Integer.parseInt(godString);
					String race = selectedRow.get(9);
					String gender = selectedRow.get(8);
					Osoba o = new Osoba();
					o.setGodine(god);
					o.setRasa(race);
					o.setPol(gender);
					SelectSymptoms mw = new SelectSymptoms(o,(String) table.getValueAt(sel, getColumnByName(table, "JMBG")));
					mainFr.dispose();
				 }
			}
		});
		
		gl.add(user);
		gl.add(userR);
		gl.add(userE);
		gl.add(simptomi);
		
		pacijent.add(gl,BorderLayout.WEST);
	    pacijent.add(scrollPane,BorderLayout.CENTER);
	   
		mainFr.setBounds(0,0,1200,700);
	//	mainFr.setSize(800, 600);
		mainFr.setDefaultCloseOperation(EXIT_ON_CLOSE);
		mainFr.setLocationRelativeTo(null);
		mainFr.add(pacijent);
		mainFr.setVisible(true);
	}
	
	private int getColumnByName(JTable table, String name) {
	    for (int i = 0; i < table.getColumnCount(); ++i)
	        if (table.getColumnName(i).equals(name))
	            return i;
	    return -1;
	}
}
