import java.sql.*;

public class ConnectionManager {

    private static String url = "jdbc:mysql://localhost/MyGym";
    private static String driverName = "com.mysql.cj.jdbc.Driver";
    private static String username = "Farhaan";
    private static String password = "cst2550";
    private static Connection connection;

    public static Connection getConnection() {

        try {

            Class.forName(driverName);

            try {

                connection = DriverManager.getConnection(url, username, password);

            } catch (SQLException ex) {

                System.out.println("Failed to create the database connection.");
            }

        } catch (ClassNotFoundException ex) {

            System.out.println("Driver not found.");

        }

        return connection;

    }
}
