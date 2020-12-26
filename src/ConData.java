import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConData {
    private static Connection con;
    private final  static String url ="jdbc:mysql://localhost:3306/docteur" ;
private  final  static  String user="root";
private final  static  String Password= "";
    public ConData() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url,user,Password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getCon() {
        return con;
    }

    public static void setCon(Connection con) {
        ConData.con = con;
    }
}

