import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class SQLRequest {
    private String sql;

    //given a valid sql String, it will return the response from the database inside a JScrollpane object
    public JScrollPane sendSQLRequest(String sql) throws SQLException, ClassNotFoundException {
        this.sql = sql;
        Class.forName("oracle.jdbc.driver.OracleDriver");
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

        for (int i = 0; i < 100; i++) {
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

        JTable table = new JTable(data, columnNames);
        table.setBounds(50, 50, 200, 300);
        JScrollPane returnValue = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        return returnValue;


    }
}


