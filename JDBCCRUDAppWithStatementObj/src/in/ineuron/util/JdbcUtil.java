package in.ineuron.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class JdbcUtil {
	
	private JdbcUtil() {}
	
	
	
	public static Connection getJdbcConnection() throws IOException, SQLException {
		HikariConfig config = new HikariConfig("src\\in\\ineuron\\properties\\application.properties");
		HikariDataSource dataSource = new HikariDataSource(config);
		return dataSource.getConnection();
	}
	
	
	public static void cleanUp(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {
		
		if(connection != null) {
			connection.close();
		}
		if(statement != null) {
			statement.close();
		}
		if(resultSet != null) {
			resultSet.close();
		}
	}

}
