package Access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static final String SQCONN = "jdbc:sqlite:Patients.sqlite";

    public static Connection getConnection() throws SQLException{

        try{
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(SQCONN);

        }catch(ClassNotFoundException e){

        }
        return null;
    }
}
