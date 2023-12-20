import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args)throws Exception {
        String sql = "select name from angestellter where id=5";























        String username = "mbuggenhagen";
        String passwort = "sch420mmsec";

























        String url = "jdbc:oracle:thin:@rs03-db-inf-min.ad.fh-bielefeld.de:1521:ORCL";

        Connection con = DriverManager.getConnection(url, username, passwort);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        rs.next();
        String result = rs.getString(1);
        System.out.println(result);
        con.close();

    }
}