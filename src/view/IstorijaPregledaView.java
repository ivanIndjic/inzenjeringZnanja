package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.util.Vector;

public class IstorijaPregledaView {
    private JFrame mainFr = new JFrame("Oftalmology");

    public IstorijaPregledaView() {

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
                "Symptom", "Treatment", "Doctor", "Date", "Disease", "IdPregleda", "DN"
        };


        String sql = "SELECT * FROM IP WHERE JMBG='" + jmbg + "'";

        Connection conn;
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inzenjering", "root", "password");


        PreparedStatement st = conn.prepareStatement(sql);
        DefaultTableModel dtm = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(dtm);
        table.getColumnModel().getColumn(5).setWidth(0);
        table.getColumnModel().getColumn(5).setMinWidth(0);
        table.getColumnModel().getColumn(5).setMaxWidth(0);
        table.getColumnModel().getColumn(0).setWidth(0);
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        table.getColumnModel().getColumn(6).setWidth(0);
        table.getColumnModel().getColumn(6).setMinWidth(0);
        table.getColumnModel().getColumn(6).setMaxWidth(0);
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    Vector<String> selectedRow = (Vector<String>) ((DefaultTableModel) table.getModel()).getDataVector().elementAt(table.getSelectedRow());
                    System.out.println(selectedRow);
                    int sel = table.getSelectedRow();

                    String symptoms = (String) table.getValueAt(sel, getColumnByName(table, "Symptom"));
                    String treatment = (String) table.getValueAt(sel, getColumnByName(table, "Treatment"));
                    String doctor = (String) table.getValueAt(sel, getColumnByName(table, "Doctor"));
                    String date = (String) table.getValueAt(sel, getColumnByName(table, "Date"));
                    String disease = (String) table.getValueAt(sel, getColumnByName(table, "Disease"));
                    String id = (String) table.getValueAt(sel, getColumnByName(table, "IdPregleda"));
                    String notes = (String) table.getValueAt(sel, getColumnByName(table, "DN"));

                    ConcreteMedicalRecord cmr = new ConcreteMedicalRecord(symptoms, treatment, doctor, date, disease, notes, id);
                    mainFr.dispose();
                }
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });
        mainFr.setLayout(new BorderLayout());
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            String d = rs.getString("Simptomi");
            String e = rs.getString("Tretman");
            String f = rs.getString("Doktor");
            String c = rs.getString("Datum");
            String b = rs.getString("Disease");
            String i = rs.getString("IdPregleda");
            String a = rs.getString("DN");

            dtm.addRow(new Object[]{d, e, f, c, b, i, a});
        }
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        od.setSize(30, 60);
        table.setDefaultEditor(Object.class, null);
        mainFr.add(scrollPane, BorderLayout.CENTER);
        mainFr.add(od, BorderLayout.SOUTH);
        mainFr.setPreferredSize(new Dimension(800, 600));
        mainFr.setSize(800, 600);
        mainFr.setLocationRelativeTo(null);


        mainFr.setVisible(true);

    }

    private int getColumnByName(JTable table, String name) {
        for (int i = 0; i < table.getColumnCount(); ++i)
            if (table.getColumnName(i).equals(name))
                return i;
        return -1;
    }
}
