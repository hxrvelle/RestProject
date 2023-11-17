package org.example.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class ConnectionManagerImpl {

    public String connectToDb() throws IOException {
        Properties properties = new Properties();
        InputStream in = getClass().getClassLoader().getResourceAsStream("db.properties");
        properties.load(in);
        assert in != null;
        in.close();

        String url = properties.getProperty("jdbc.url");
        String username = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");

        return url + "?user=" + username + "&password=" + password;
    }

    public Connection connection() throws ClassNotFoundException, IOException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(connectToDb());
    }

    public Statement statement(Connection connection) throws SQLException {
        return connection.createStatement();
    }

    public ResultSet connect(Statement statement, String query) throws SQLException {
        return statement.executeQuery(query);
    }

    public void updateConnect(Statement statement, String query) throws SQLException {
        statement.executeUpdate(query);
    }

    public void voidConnect(Statement statement, String query) throws SQLException {
        statement.execute(query);
    }
}
