package model;
 

// Adapted from http://www.vogella.com/tutorials/MySQLJava/article.html
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class MySQLAccess {

	private Connection connection = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	final private String host = "mydbinstance.cxtiodibiw6j.ap-southeast-2.rds.amazonaws.com:3306";
	final private String user = "root";
	final private String passwd = "root1234";

	public void readDataBase() throws Exception {
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");

			// Setup the connection with the DB
			connection = DriverManager
					.getConnection("jdbc:mysql://" + host + "/SLGame?" + "user=" + user + "&password=" + passwd);

			// Statements allow to issue SQL queries to the database
			statement = connection.createStatement();
			// Result set get the result of the SQL query
			// Executing the query and getting the result set
			ResultSet rs = statement.executeQuery("select * from player");

			// Iterating the result set and printing the 3rd column
			while (rs.next()) {
				System.out.println(rs.getString(2));
			}
			// close the result set, statement and connection.
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
	}

	public void register(String username, String password) throws Exception {
		
		// This will load the MySQL driver, each DB has its own driver
					Class.forName("com.mysql.jdbc.Driver");

					// Setup the connection with the DB
					connection = DriverManager
							.getConnection("jdbc:mysql://" + host + "/SLGame?" + "user=" + user + "&password=" + passwd);

					// // Statements allow to issue SQL queries to the database
					// statement = connection.createStatement();

					// Get the result of the SQL query
					preparedStatement = connection
							.prepareStatement("insert into player (Username, Password, Score) values (?,?,?)");
					preparedStatement.setString(1, username);
					preparedStatement.setString(2, password);
					preparedStatement.setInt(3, 0);
					// Executing the query and getting the result set
					preparedStatement.executeUpdate();
					System.out.println("Register Successful!\n");
					close();
		
		/* try {
			
		} catch (MySQLIntegrityConstraintViolationException e) {
			System.out.println("Username already exists!\n");
		
		} finally {
			close();
		}
		*/
	}

	public ResultSet login(String username, String password) throws Exception {
		// This will load the MySQL driver, each DB has its own driver
		Class.forName("com.mysql.jdbc.Driver");

		// Setup the connection with the DB
		connection = DriverManager
				.getConnection("jdbc:mysql://" + host + "/SLGame?" + "user=" + user + "&password=" + passwd);
		// Get the result of the SQL query
		preparedStatement = connection.prepareStatement("select password from player where username = ?");
		preparedStatement.setString(1, username);
		// preparedStatement.setString(2, password);
		ResultSet rs = preparedStatement.executeQuery();

		return rs;
	}

	public void updateWinningScore(String winner) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");

		// Setup the connection with the DB
		connection = DriverManager
				.getConnection("jdbc:mysql://" + host + "/SLGame?" + "user=" + user + "&password=" + passwd);
		preparedStatement = connection.prepareStatement("update player set score = score + 1 where username = ?");
		preparedStatement.setString(1, winner);
		preparedStatement.executeUpdate();
	}

	// Close the resultSet, statement and connection
	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {

		}
	}

	public void updateLosingScore(String loser) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");

		// Setup the connection with the DB
		connection = DriverManager
				.getConnection("jdbc:mysql://" + host + "/SLGame?" + "user=" + user + "&password=" + passwd);
		preparedStatement = connection.prepareStatement("update player set score = score - 1 where username = ?");
		preparedStatement.setString(1, loser);
		preparedStatement.executeUpdate();
	}

	public ResultSet displayScore() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");

		// Setup the connection with the DB
		connection = DriverManager
				.getConnection("jdbc:mysql://" + host + "/SLGame?" + "user=" + user + "&password=" + passwd);
		// Get the result of the SQL query
		preparedStatement = connection.prepareStatement("select username, score from player");
		ResultSet rs = preparedStatement.executeQuery();
		
		
		List<String> results = new ArrayList<String>();
		if (rs.next()) {
			int i = 1;
	        while(i <= 2) {
	            results.add(rs.getString(i++));
	        }
		}

		return rs;
	}

}
