package org.example.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.HashMap;
import java.util.Properties;

public class ConnectionManager {
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource data;
    private static Properties properties;

    private ConnectionManager() {
    }

    public static Connection connection() throws SQLException, IOException {
        if (data == null) return configData();
        else return data.getConnection();
    }

    public static void setData(HikariConfig config) {
        ConnectionManager.data = new HikariDataSource(config);
    }

    private static Connection configData() throws IOException, SQLException {
        properties = new Properties();
        InputStream in = ConnectionManager.class.getClassLoader().getResourceAsStream("db.properties");
        if (in != null) properties.load(in);
        in.close();
        config.setJdbcUrl(properties.getProperty("jdbc.url"));
        config.setUsername(properties.getProperty("jdbc.username"));
        config.setPassword(properties.getProperty("jdbc.password"));
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        data = new HikariDataSource(config);
        return data.getConnection();
    }

    public static ResultSet connect(Statement statement, String query) throws SQLException {
        return statement.executeQuery(query);
    }

    public static void updateConnect(Statement statement, String query) throws SQLException {
        statement.executeUpdate(query);
    }

    public static void voidConnect(Statement statement, String query) throws SQLException {
        statement.execute(query);

    //    private static String url;
//    private static String username;
//    private static String password;
//
//    private static String getUrl() {
//        return url;
//    }
//
//    private static String getUsername() {
//        return username;
//    }
//
//    private static String getPassword() {
//        return password;
//    }
//
//    public static void setUrl(String url) {
//        ConnectionManager.url = url;
//    }
//
//    public static void setUsername(String username) {
//        ConnectionManager.username = username;
//    }
//
//    public static void setPassword(String password) {
//        ConnectionManager.password = password;
//    }
//
//    public ConnectionManager() {
//        Properties props = new Properties();
//        InputStream in = ConnectionManager.class.getClassLoader().getResourceAsStream("db.properties");
//        try {
//            props.load(in);
//            in.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        url = props.getProperty("jdbc.url");
//        username = props.getProperty("jdbc.username");
//        password = props.getProperty("jdbc.password");
//    }
//
//    public static Connection connection() throws SQLException, ClassNotFoundException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        return DriverManager.getConnection(getUrl(), getUsername(), getPassword());
//    }
//
    }
}
