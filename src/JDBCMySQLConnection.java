

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.log.Log;

public class JDBCMySQLConnection {
	private static JDBCMySQLConnection instance = new JDBCMySQLConnection();
	static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
	static final String URL = "jdbc:mysql://localhost:3306/iot";
	static final String USER = "arpitkh96";//"root";
	static final String PASSWORD = "Ak575429";

	private JDBCMySQLConnection() {
        try {
            //Step 2: Load MySQL Java driver
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
        	StringWriter sw = new StringWriter();
        	PrintWriter pw = new PrintWriter(sw);
        	e.printStackTrace(pw);
        	Logger.log(sw.toString());
            e.printStackTrace();
        }
    }

	private Connection createConnection() {
		Connection connection = null;
		try {
			Logger.log("Connecting"+(connection==null));
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			Logger.log("Connected"+(connection==null));
		} catch (SQLException e) {
			StringWriter sw = new StringWriter();
        	PrintWriter pw = new PrintWriter(sw);
        	e.printStackTrace(pw);
        	Logger.log(sw.toString());
        	e.printStackTrace();		
				}
		return connection;
	}

	public static Connection getConnection() {
		return instance.createConnection();
	}
}
