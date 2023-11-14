package org.example.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class ConnectionManagerImpl implements ConnectionManager {

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

    @Override
    public Statement sqlConnection() throws ClassNotFoundException, SQLException, IOException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(connectToDb());
        return connection.createStatement();
    }

    @Override
    public ResultSet connect(String query) throws SQLException, ClassNotFoundException, IOException {
        return sqlConnection().executeQuery(query);
    }

    @Override
    public void updateConnect(String query) throws SQLException, ClassNotFoundException, IOException {
        sqlConnection().executeUpdate(query);
    }

    @Override
    public void voidConnect(String query) throws SQLException, ClassNotFoundException, IOException {
        sqlConnection().execute(query);
    }

}
