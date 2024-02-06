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


    //Constructor to implement GUI features
    public QueryGUI() {
        setTitle("Query");
        setSize(1500, 1000);
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


        run = new JButton("run");
        run.setMargin(new Insets(0, 0, 0, 0));
        run.setBackground(Color.LIGHT_GRAY);
        run.setForeground(Color.BLACK);
        run.setBorder(null);

        try {
            Image img = ImageIO.read(new File("src/runButton.png"));
            run.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        run.addActionListener(this);


        subPanel3 = new JPanel();
        subPanel3.setLayout(new GridLayout(11, 1));
        subPanel3.setSize(200, 200);
        subPanel3.setPreferredSize(new Dimension(200, 500));

        for (int i = 0; i < 11; i++) {
            switch (i) {
                case 0:
                    JLabel info = new JLabel("beliebte Suchanfragen: ");
                    info.setFont(info.getFont().deriveFont(15.0F));
                    info.setHorizontalAlignment(SwingConstants.CENTER);
                    subPanel3.add(info);
                    break;
                case 1:
                    sql1 = new JButton("<html>" + "<&nbsp häufigste Zwischenfälle" + "<html>");
                    sql1.addActionListener(this);
                    subPanel3.add(sql1);
                    break;
                case 2:
                    sql2 = new JButton("<html>" + "<&nbsp Liste aller Insassen in einem <br> <&nbsp Gefängnis" + "<html>");
                    sql2.addActionListener(this);
                    subPanel3.add(sql2);
                    break;
                case 3:
                    sql3 = new JButton("<html>" + "<&nbsp Liste aller Insassen innerhalb <br> <&nbsp eines Gefängnisses" + "<html>");
                    sql3.addActionListener(this);
                    subPanel3.add(sql3);
                    break;
                case 4:
                    sql4 = new JButton("<html>" + "<&nbsp Welche Rollen haben welche <br> <&nbsp Rechte?" + "<html>");
                    sql4.addActionListener(this);
                    subPanel3.add(sql4);
                    break;
                case 5:
                    sql5 = new JButton("<html>" + "<&nbsp Welches Angebot hat die meisten <br> <&nbsp Teilnehmer gehabt?" + "<html>");
                    sql5.addActionListener(this);
                    subPanel3.add(sql5);
                    break;
                case 6:
                    sql6 = new JButton("<html>" + "<&nbsp Wie viele Chefs gibt es und wo <br> <&nbsp arbeiten diese?" + "<html>");
                    sql6.addActionListener(this);
                    subPanel3.add(sql6);
                    break;
                case 7:
                    sql7 = new JButton("<html>" + "<&nbsp Welche Firmen beliefern die <br> <&nbsp JVA Bielefeld?" + "<html>");
                    sql7.addActionListener(this);
                    subPanel3.add(sql7);
                    break;
                case 8:
                    sql8 = new JButton("<html>" + "<&nbsp Wie viel geld haben Gefängnisse <br> <&nbsp im Jahr 2024 ausgegeben?" + "<html>");
                    sql8.addActionListener(this);
                    subPanel3.add(sql8);
                    break;
                case 9:
                    sql9 = new JButton("<html>" + "<&nbsp Welche Firmen liefern aktuell <br> <&nbsp Lebensmittel?" + "<html>");
                    sql9.addActionListener(this);
                    subPanel3.add(sql9);
                    break;
                case 10:
                    sql10 = new JButton("<html>" + "<&nbsp Welche Insassen sind aktuell im <br> <&nbsp Bereich Küche beschäftigt?" + "<html>");
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

    //describes how all the buttons inside the query window work
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
            query.setText("SELECT COUNT(*) \n" +
                    "FROM ZELLE \n" +
                    "JOIN GEFANGNIS on GEFANGNIS.id = ZELLE.gef_id \n" +
                    "WHERE GEFANGNIS.name = 'Justizvollzugsanstalt Bielefeld'");

        } else if (e.getSource() == sql3) {
            query.setText("SELECT * FROM(\n" +
                    "SELECT INSASSE.id, INSASSE.vorname, INSASSE.nachname, MAX(INSASSENHISTORIE.datum) AS DATUM, INSASSENHISTORIE.zel_id, ROW_NUMBER() OVER (PARTITION BY INSASSE.id \n" +
                    "ORDER BY INSASSENHISTORIE.datum DESC) AS RANG\n" +
                    "FROM\n" +
                    "INSASSENHISTORIE\n" +
                    "JOIN INSASSE ON INSASSE.id = INSASSENHISTORIE.ins_id \n" +
                    "JOIN ZELLE on INSASSENHISTORIE.zel_id = ZELLE.id\n" +
                    "JOIN GEFANGNIS ON ZELLE.gef_id = GEFANGNIS.id\n" +
                    "WHERE GEFANGNIS.name =  'Justizvollzugsanstalt Hameln' OR INSASSENHISTORIE.zel_id IS NULL\n" +
                    "GROUP BY INSASSE.id, INSASSE.vorname, INSASSE.nachname, INSASSENHISTORIE.datum, INSASSENHISTORIE.zel_id\n" +
                    "ORDER BY INSASSE.id\n" +
                    ") WHERE RANG = 1 AND zel_id IS NOT NULL");

        } else if (e.getSource() == sql4) {
            query.setText("SELECT a.id AS Rollen_ID, a.bezeichnung AS BezeichnungRolle, b.recht_id, c.bezeichnung AS BezeichnungRecht \n" +
                    "FROM ROLLE a \n" +
                    "JOIN ROLLEN_RECHTE b ON a.id = b.rol_id \n" +
                    "JOIN RECHTE c ON b.recht_id = c.id \n" +
                    "ORDER BY a.id");

        } else if (e.getSource() == sql5) {
            query.setText("SELECT a.bezeichnung, count(a.bezeichnung) AS Number_of_users \n" +
                    "from angebot a \n" +
                    "JOIN VERTRAG b ON a.id = b.ang_id \n" +
                    "join INSASSE c ON b.ins_id = c.id \n" +
                    "GROUP BY a.bezeichnung \n" +
                    "ORDER BY number_of_users DESC");

        } else if (e.getSource() == sql6) {
            query.setText("SELECT nachname, vorname, GEFANGNIS.name\n" +
                    "FROM MITARBEITER \n" +
                    "JOIN GEFANGNIS ON GEFANGNIS.id = MITARBEITER.gef_id\n" +
                    "WHERE mit_id IS NULL");

        } else if (e.getSource() == sql7) {
            query.setText("SELECT FIRMA.firmenname, DIENSTLEISTUNGSVERTRAG.import_export\n" +
                    "FROM GEFANGNIS \n" +
                    "JOIN GEFANGNIS_DIENSTLEISTUNG ON GEFANGNIS_DIENSTLEISTUNG.gef_id = GEFANGNIS.id\n" +
                    "JOIN DIENSTLEISTUNGSVERTRAG ON DIENSTLEISTUNGSVERTRAG.id = GEFANGNIS_DIENSTLEISTUNG.vertrag_id\n" +
                    "JOIN FIRMA ON FIRMA.id = DIENSTLEISTUNGSVERTRAG.fir_id\n" +
                    "WHERE GEFANGNIS.name = 'Justizvollzugsanstalt Bielefeld' AND SYSDATE BETWEEN DIENSTLEISTUNGSVERTRAG.beginn AND DIENSTLEISTUNGSVERTRAG.ende\n");

        } else if (e.getSource() == sql8) {
            query.setText("SELECT SUM(ausgaben) AS SummeAusgaben\n" +
                    "FROM FINANZEN\n" +
                    "WHERE jahr = 2024 AND die_id IS NULL");

        } else if (e.getSource() == sql9) {
            query.setText("SELECT FIRMA.firmenname, beginn, ende, fir_id\n" +
                    "FROM DIENSTLEISTUNGSVERTRAG \n" +
                    "JOIN FIRMA ON FIRMA.id = DIENSTLEISTUNGSVERTRAG.fir_id\n" +
                    "WHERE DIENSTLEISTUNGSVERTRAG.import_export = 'Lebensmittel' AND (SYSDATE BETWEEN DIENSTLEISTUNGSVERTRAG.beginn AND DIENSTLEISTUNGSVERTRAG.ende OR DIENSTLEISTUNGSVERTRAG.ende IS NULL)");

        } else if (e.getSource() == sql10) {
            query.setText("SELECT a.id, a.vorname, a.nachname, c.id AS Angebot_ID, c.bezeichnung, b.beginn, b.ende \n" +
                    "FROM INSASSE a JOIN VERTRAG b on a.id = b.ins_id LEFT OUTER JOIN ANGEBOT c on b.ang_id = c.id where c.bezeichnung = 'Küche' ORDER BY a.id");

        }

    }

}
