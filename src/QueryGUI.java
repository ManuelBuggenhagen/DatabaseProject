import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class QueryGUI extends JFrame implements ActionListener {
    Container pane = getContentPane();
    private String sql;
    JLabel queryResult;
    JScrollPane scrollPane;
    JTable table;
    JTextArea query;
    JPanel subPanel1;
    JPanel subPanel3;
    JButton run;
    boolean hasBeenPressed = false;
    JButton sql1;
    JButton sql2;
    JButton sql3;
    JButton sql4;
    JButton sql5;
    JButton sql6;
    JButton sql7;
    JButton sql8;
    JButton sql9;
    JButton sql10;


    public QueryGUI() {
        setTitle("Query");
        setSize(800, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        query = new JTextArea("select * from angebot");
        query.setLineWrap(true);
        query.setWrapStyleWord(true);
        query.setCaretColor(Color.WHITE);
        query.setFont(query.getFont().deriveFont(20.0F));
        query.setForeground(Color.WHITE);
        query.setBackground(Color.BLACK);
        queryResult = new JLabel("hier wird ihr Ergebnis stehen", SwingConstants.CENTER);
        queryResult.setFont(queryResult.getFont().deriveFont(20.0F));
        //scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);


        run = new JButton("run");
        run.setMargin(new Insets(0, 0, 0, 0));
        run.setBackground(Color.LIGHT_GRAY);
        run.setForeground(Color.BLACK);
        run.setBorder(null);

        //final boolean[] hasBeenPressed = {false};
        try {
            Image img = ImageIO.read(new File("src/runButton.png"));
            run.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        run.addActionListener(this);


        subPanel3 = new JPanel();
        subPanel3.setLayout(new GridLayout(11, 1));
        subPanel3.setSize(200,200);
        subPanel3.setPreferredSize(new Dimension(200,500));

        for (int i = 0; i < 11; i++) {
            switch (i) {
                case 0:
                    JLabel info = new JLabel("beliebte Suchanfragen: ");
                    info.setFont(info.getFont().deriveFont(15.0F));
                    info.setHorizontalAlignment(SwingConstants.CENTER);
                    subPanel3.add(info);
                    break;
                case 1:
                    sql1 = new JButton("häufigste Zwischenfälle");
                    sql1.addActionListener(this);
                    subPanel3.add(sql1);
                    break;
                case 2:
                    sql2 = new JButton("Liste aller Insassen in einem Gefängnis");
                    sql2.addActionListener(this);
                    subPanel3.add(sql2);
                    break;
                case 3:
                    sql3 = new JButton("3");
                    sql3.addActionListener(this);
                    subPanel3.add(sql3);
                    break;
                case 4:
                    sql4 = new JButton("4");
                    sql4.addActionListener(this);
                    subPanel3.add(sql4);
                    break;
                case 5:
                    sql5 = new JButton("5");
                    sql5.addActionListener(this);
                    subPanel3.add(sql5);
                    break;
                case 6:
                    sql6 = new JButton("6");
                    sql6.addActionListener(this);
                    subPanel3.add(sql6);
                    break;
                case 7:
                    sql7 = new JButton("7");
                    sql7.addActionListener(this);
                    subPanel3.add(sql7);
                    break;
                case 8:
                    sql8 = new JButton("8");
                    sql8.addActionListener(this);
                    subPanel3.add(sql8);
                    break;
                case 9:
                    sql9 = new JButton("9");
                    sql9.addActionListener(this);
                    subPanel3.add(sql9);
                    break;
                case 10:
                    sql10 = new JButton("10");
                    sql10.addActionListener(this);
                    subPanel3.add(sql10);
                    break;
                default:
                    break;
            }
        }

        JPanel subPanel2 = new JPanel();
        subPanel2.setLayout(new GridLayout(1, 6));
        subPanel2.setBackground(Color.WHITE);
        subPanel2.add(run);
        subPanel2.add(new JLabel(""));
        subPanel2.add(new JLabel(""));
        subPanel2.add(new JLabel(""));
        subPanel2.add(new JLabel(""));

        subPanel1 = new JPanel();
        subPanel1.setLayout(new GridLayout(2, 1));
        subPanel1.add(query);


        pane.setLayout(new BorderLayout(10, 10));
        pane.add(subPanel2, BorderLayout.NORTH);
        pane.add(subPanel1, BorderLayout.CENTER);
        pane.add(subPanel3, BorderLayout.WEST);
        pane.setBackground(Color.black);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == run) {
            if (hasBeenPressed) {
                subPanel1.remove(scrollPane);
            }
            try {
                SQLRequest sqlRequest = new SQLRequest();
                scrollPane = sqlRequest.sendSQLRequest(query.getText());
            } catch (SQLException ex) {
                System.out.println("fehler!");
                JOptionPane.showMessageDialog(getContentPane(), "Die SQL Abfrage war leider fehlerhaft", "error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
            subPanel1.add(scrollPane);
            pack();
            hasBeenPressed = true;

        } else if (e.getSource() == sql1) {
            query.setText("select a.versto_ as Verstoß  , COUNT(a.versto_) as Number_of_incidents  \n" +
                    "     from zwischenfallhistorie a group by versto_ order by Number_of_incidents DESC\n");

        } else if (e.getSource() == sql2) {
            query.setText("SELECT * FROM(\n" +
                    "SELECT INSASSE.ID, INSASSE.VORNAME, INSASSE.NACHNAME, MAX(INSASSENHISTORIE.DATUM) AS DATUM, INSASSENHISTORIE.ZEL_ID, ROW_NUMBER() OVER (PARTITION BY INSASSE.ID \n" +
                    "ORDER BY INSASSENHISTORIE.DATUM DESC) AS RANG\n" +
                    "FROM\n" +
                    "INSASSENHISTORIE\n" +
                    "LEFT OUTER JOIN INSASSE ON INSASSE.ID = INSASSENHISTORIE.INS_ID \n" +
                    "LEFT OUTER JOIN ZELLE on INSASSENHISTORIE.ZEL_ID = ZELLE.ID\n" +
                    "LEFT OUTER JOIN GEFANGNIS ON ZELLE.GEF_ID = GEFANGNIS.ID\n" +
                    "WHERE GEFANGNIS.NAME =  'Justizvollzugsanstallt Hameln' OR INSASSENHISTORIE.ZEL_ID IS NULL\n" +
                    "GROUP BY INSASSE.ID, INSASSE.VORNAME, INSASSE.NACHNAME, INSASSENHISTORIE.DATUM, INSASSENHISTORIE.ZEL_ID\n" +
                    "ORDER BY INSASSE.ID");

        } else if (e.getSource() == sql3) {
            query.setText("dfd");

        } else if (e.getSource() == sql4) {
            query.setText("dfd");

        } else if (e.getSource() == sql5) {
            query.setText("dfd");

        } else if (e.getSource() == sql6) {
            query.setText("dfd");

        } else if (e.getSource() == sql7) {
            query.setText("dfd");

        } else if (e.getSource() == sql8) {
            query.setText("dfd");

        } else if (e.getSource() == sql9) {
            query.setText("dfd");

        } else if (e.getSource() == sql10) {
            query.setText("dfd");

        }

    }

}
