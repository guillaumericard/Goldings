package services.impl;

import config.Config;
import services.interfaces.DbService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteService implements DbService {

  private static Connection connection = null;

  static{
    String url = Config.getInstance().getDb().getConnectionUrl();
    try {
      connection = DriverManager.getConnection(url);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  @Override
  public Connection getConnection() {
    return connection;
  }
}
