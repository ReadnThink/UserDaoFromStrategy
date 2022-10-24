package dao;

import domain.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao {
    private DataSource dataSource;
    private JdbcContextWith jdbcContextWith;

    private JdbcTemplate jdbcTemplate;

    public UserDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public void deleteAll() throws SQLException {
        jdbcTemplate.update("delete from users");
    }
    public void add(User user) throws SQLException {
        this.jdbcTemplate.update("insert into users(id,name,password) values(?,?,?)"
                ,user.getId(),user.getName(),user.getPassword());
    }

    public int getConut() throws SQLException {
        return this.jdbcTemplate.queryForObject("select count(*) from users",Integer.class);
    }

    RowMapper<User> rowMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
             User user = new User(rs.getString("id")
                        ,rs.getString("name"),rs.getString("password"));
                return user;
        }
    };

    public User findById(String id) throws SQLException {
       String sql = "select * from users where id = ?";
        return this.jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
    public List<User> getAll(){
        String sql = "select * from users order by id";
        return this.jdbcTemplate.query(sql,rowMapper);
    }
}
