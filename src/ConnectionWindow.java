package com.surveytool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Database connection manager class.
 * Handles database connections and provides query execution methods.
 */
public class ConnectionWindow implements AutoCloseable {
  private static final Logger logger =
      LoggerFactory.getLogger(ConnectionWindow.class);
  private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
  private static final String DB_URL = "jdbc:mysql://localhost:3306/ssurvey";
  private static final String DB_USER = "root";
  private static final String DB_PASSWORD = "";

  private Connection connection;
  private PreparedStatement preparedStatement;
  private ResultSet resultSet;

  /**
   * Initializes database connection.
   * @throws SQLException if connection fails
   */
  public ConnectionWindow() throws SQLException {
    try {
      Class.forName(JDBC_DRIVER);
      connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    } catch (ClassNotFoundException e) {
      logger.error("JDBC Driver not found", e);
      throw new SQLException("JDBC Driver not found", e);
    }
  }

  /**
   * Executes a prepared statement.
   * @param sql SQL query string
   * @return PreparedStatement object
   * @throws SQLException if query preparation fails
   */
  public PreparedStatement prepareStatement(String sql) throws SQLException {
    preparedStatement = connection.prepareStatement(sql);
    return preparedStatement;
  }

  /**
   * Executes a query and returns results.
   * @param sql SQL query string
   * @return ResultSet object
   * @throws SQLException if query execution fails
   */
  public ResultSet executeQuery(String sql) throws SQLException {
    preparedStatement = connection.prepareStatement(sql);
    resultSet = preparedStatement.executeQuery();
    return resultSet;
  }

  @Override
  public void close() {
    try {
      if (resultSet != null)
        resultSet.close();
      if (preparedStatement != null)
        preparedStatement.close();
      if (connection != null)
        connection.close();
    } catch (SQLException e) {
      logger.error("Error closing database resources", e);
    }
  }
}
