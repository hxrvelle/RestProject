package org.example.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public interface ConnectionManager {
    Statement sqlConnection() throws ClassNotFoundException, SQLException, IOException;
    ResultSet connect(String query) throws SQLException, ClassNotFoundException, IOException;
    void updateConnect(String query) throws SQLException, ClassNotFoundException, IOException;
    void voidConnect(String query) throws SQLException, ClassNotFoundException, IOException;

}
