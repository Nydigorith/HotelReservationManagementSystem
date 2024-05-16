package hotelreservationmanagementsystem;


import java.sql.Connection;
import java.sql.DriverManager;


public class DbConnection {
    
    Connection con= null;
    
    public static Connection ConnectDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:mysql://localhost/hotelreservation","root", "");
            System.out.print("Connection Successful");
            return conn;
        }
        catch(Exception e) {
            System.out.print("Connection Failed:"+e);
        }
        return null;
    }
}