package Access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBconnection {

    Connection connection;

    public DBconnection() {
        try{
            this.connection = DBConnector.getConnection();
        }catch(SQLException e){
            e.printStackTrace();;
        }

        if(this.connection == null){
            System.exit(1);
        }
    }
}
