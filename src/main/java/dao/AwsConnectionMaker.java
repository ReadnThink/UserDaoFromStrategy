package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class AwsConnectionMaker implements ConnectionMaker{
    @Override
    public Connection makeConnection() throws SQLException {
        Map<String,String> env = System.getenv();
        Connection c = DriverManager.getConnection(env.get("id"),env.get("name"),env.get("password"));

        return c;
    }
}
