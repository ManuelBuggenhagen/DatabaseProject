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

public class QueryGUI extends JFrame {
    Container pane = getContentPane();
    private String sql;

    public QueryGUI() {
        setTitle("Query");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        JTextArea query = new JTextArea("select * from angebot");
        query.setLineWrap(true);
        query.setWrapStyleWord(true);
        query.setCaretColor(Color.WHITE);
        query.setFont(query.getFont().deriveFont(20.0F));
        query.setForeground(Color.WHITE);
        query.setBackground(Color.BLACK);
        JLabel queryResult = new JLabel("hier wird ihr Ergebnis stehen", SwingConstants.CENTER);
        queryResult.setFont(queryResult.getFont().deriveFont(20.0F));


        JButton run = new JButton("run");
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
        run.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                queryResult.setText(sendSQLRequest(query.getText()));
            }
        });

        JPanel subPanel2 = new JPanel();
        subPanel2.setLayout(new GridLayout(1, 6));
        subPanel2.setBackground(Color.WHITE);
        subPanel2.add(run);
        subPanel2.add(new JLabel(""));
        subPanel2.add(new JLabel(""));
        subPanel2.add(new JLabel(""));
        subPanel2.add(new JLabel(""));

        JPanel subPanel1 = new JPanel();
        subPanel1.setLayout(new GridLayout(2, 1));
        subPanel1.add(query);
        subPanel1.add(queryResult);


        pane.setLayout(new BorderLayout(10, 10));
        pane.add(subPanel2, BorderLayout.NORTH);
        pane.add(subPanel1, BorderLayout.CENTER);


    }


    private String sendSQLRequest(String sql) {
        try {
            this.sql = sql;
            String username = "DieKnastiGmbH";
            String passwort = "DieKnastiGmbH";
            String url = "jdbc:oracle:thin:@rs03-db-inf-min.ad.fh-bielefeld.de:1521:ORCL";
            Connection con = DriverManager.getConnection(url, username, passwort);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);


            ResultSetMetaData rsmd = rs.getMetaData();
            int numberOfColumns = rsmd.getColumnCount();
            int rowAmount = 1;

            ArrayList<ArrayList<String>> list = new ArrayList<>();

            for (int i = 0; i < 51; i++) {
                list.add(new ArrayList<>());
            }

            for (int i = 1; i <numberOfColumns ; i++) {
                list.getFirst().add(rsmd.getColumnName(i));
            }

            while (rs.next()) {

                //list.add(new ArrayList<>());


                for (int i = 1; i <= numberOfColumns; i++) {
                    list.get(rowAmount).add(rs.getString(i));
                }
                rowAmount++;
            }

            System.out.println("Rows: " + rowAmount);
            System.out.println("Columns: " + numberOfColumns);

            st.close();
            con.close();

            for (int i = list.size()-1; i > 1; i--) {
                if (list.get(i).isEmpty()) {
                    list.remove(i);
                }
            }


            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
            System.out.println("List size: " + list.size());

            return null;

        } catch (SQLException e) {
            System.out.println("fehler!");
            JOptionPane.showMessageDialog(getContentPane(), "Die SQL Abfrage war leider fehlerhaft", "error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return null;
    }

}
