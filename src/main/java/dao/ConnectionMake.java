package dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMake {
    public Connection makeConnection() throws SQLException;
}
