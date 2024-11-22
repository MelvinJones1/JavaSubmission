package util;

import exception.FileHandlingException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnUtil {

    public static Connection getConnection() {

        Connection con = null;
        String fileName = "db.properties";
        try {
            String url = DBPropertyUtil.getConnectionString(fileName);
            con = DriverManager.getConnection(url);


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileHandlingException e) {
            throw new RuntimeException(e);
        }

        return con;
    }
}
