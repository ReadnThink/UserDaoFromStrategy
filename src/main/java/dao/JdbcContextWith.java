package dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcContextWith {
    private DataSource dataSource;

    public JdbcContextWith(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public void executeSql(String sql){
        this.jdbcContext(new StatementStrategy() {
            @Override
            public PreparedStatement makeStatement(Connection c) throws SQLException {
                return c.prepareStatement(sql);
            }
        });
    }
    public void jdbcContext(StatementStrategy stmt){
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = dataSource.getConnection();
            ps = stmt.makeStatement(c);
            ps.executeUpdate();
        } catch (SQLException e) {

        } finally {
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException e) {

                }
            }
            if(c!=null){
                try {
                    c.close();
                } catch (SQLException e) {

                }
            }
        }

    }
}
