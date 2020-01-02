package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class IstorijaView {
	private JFrame mainFr = new JFrame("Oftalmology");
	
	public IstorijaView() {
  
}
	
	public void nacrtaj(String jmbg) throws SQLException {
		
		JPanel main = new JPanel(new BorderLayout());
		JPanel gornji = new JPanel();

		JPanel donji = new JPanel();
		JButton od = new JButton("Close");
		od.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				mainFr.dispose();
			}
		});
		String[] columnNames = {
				 "Symptom","Treatment","Doctor","Date"
               
              };
	
		String sql="SELECT * FROM IP WHERE JMBG='"+jmbg+"'";
		
		Connection conn;
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inzenjering","root","root");
		

		PreparedStatement st = conn.prepareStatement(sql);
		 DefaultTableModel dtm=new DefaultTableModel(columnNames,0);
		 JTable table=new JTable(dtm);	
		mainFr.setLayout(new BorderLayout());
		ResultSet rs = st.executeQuery(sql);
		while(rs.next())
		{
		    String d = rs.getString("Simptomi");
		    String e = rs.getString("Tretman");
		    String f = rs.getString("Doktor");
		    String c = rs.getString("Datum");
		      
		    
		    dtm.addRow(new Object[]{d, e, f,c});
		}
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
	    od.setSize(30, 60);
	    table.setDefaultEditor(Object.class, null);
		mainFr.add(scrollPane,BorderLayout.CENTER);
		mainFr.add(od,BorderLayout.SOUTH);
		mainFr.setPreferredSize(new Dimension(800,600));
		mainFr.setSize(800, 600);
		mainFr.setLocationRelativeTo(null);


		mainFr.setVisible(true);
		
		
		
	}
}
