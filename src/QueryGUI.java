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
    JLabel queryResult;
    JScrollPane scrollPane;
    JTable table;
    JTextArea query;
    JPanel subPanel1;

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


        JButton run = new JButton("run");
        run.setMargin(new Insets(0, 0, 0, 0));
        run.setBackground(Color.LIGHT_GRAY);
        run.setForeground(Color.BLACK);
        run.setBorder(null);
        final boolean[] hasBeenPressed = {false};
        try {
            Image img = ImageIO.read(new File("src/runButton.png"));
            run.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        run.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hasBeenPressed[0]){
                    subPanel1.remove(scrollPane);
                }
                sendSQLRequest(query.getText());
                subPanel1.add(scrollPane);
                pack();
                hasBeenPressed[0] =true;
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

        subPanel1 = new JPanel();
        subPanel1.setLayout(new GridLayout(2, 1));
        subPanel1.add(query);


        pane.setLayout(new BorderLayout(10, 10));
        pane.add(subPanel2, BorderLayout.NORTH);
        pane.add(subPanel1, BorderLayout.CENTER);


    }


    private void sendSQLRequest(String sql) {
        try {
            this.sql = sql;
            String username = "DieKnastiGmbH";
            String passwort = "DieKnastiGmbH";
            String url = "jdbc:oracle:thin:@rs03-db-inf-min.ad.fh-bielefeld.de:1521:ORCL";
            Connection con = DriverManager.getConnection(url, username, passwort);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(this.sql);


            ResultSetMetaData rsmd = rs.getMetaData();
            int numberOfColumns = rsmd.getColumnCount();
            int rowAmount = 1;

            ArrayList<ArrayList<String>> list = new ArrayList<>();

            for (int i = 0; i < 51; i++) {
                list.add(new ArrayList<>());
            }

            for (int i = 1; i <= numberOfColumns; i++) {
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

            for (int i = list.size() - 1; i > 1; i--) {
                if (list.get(i).isEmpty()) {
                    list.remove(i);
                }
            }

            /*
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<html>");
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < list.get(i).size(); j++) {
                    stringBuilder.append(list.get(i).get(j));
                    stringBuilder.append("  ||  ");
                }
                stringBuilder.append("<br>");
            }
            stringBuilder.append("<html>");
            queryResult.setText(stringBuilder.toString());

             */


            String[] columnNames = new String[numberOfColumns];
            for (int i = 0; i < columnNames.length; i++) {
                columnNames[i] = list.getFirst().get(i);
            }

            String[][] data = new String[rowAmount][numberOfColumns];
            for (int i = 1; i < data.length; i++) {
                for (int j = 0; j < data[i].length; j++) {
                    data[i][j] = list.get(i).get(j);
                }
            }

            table = new JTable(data, columnNames);
            table.setBounds(50, 50, 200, 300);
            scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);


            /*
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
            System.out.println("List size: " + list.size());

             */


        } catch (SQLException e) {
            System.out.println("fehler!");
            JOptionPane.showMessageDialog(getContentPane(), "Die SQL Abfrage war leider fehlerhaft", "error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

}
