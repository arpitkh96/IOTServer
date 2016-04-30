	

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.ResultSet;

public class DataManager {
public static String generateIp(int id) {
		ResultSet rs = null;
		Connection connection = null;
		Statement statement = null;
		String s = "-1";
		String query = "SELECT * FROM IPS WHERE id=" + id;
		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			if (rs.next()) {
				s = rs.getString("ip");
			}
		} catch (SQLException e) {

			StringWriter sw = new StringWriter();
        	PrintWriter pw = new PrintWriter(sw);
        	e.printStackTrace(pw);
        	Logger.log(sw.toString());
        	e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					StringWriter sw = new StringWriter();
		        	PrintWriter pw = new PrintWriter(sw);
		        	e.printStackTrace(pw);
		        	Logger.log(sw.toString());
		        	e.printStackTrace();
				}
			}
		}
		return s;
	}
}
