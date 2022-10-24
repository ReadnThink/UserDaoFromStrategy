package dao;

import domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    UserDao userDao;
    User user1;
    User user2;
    User user3;

    @BeforeEach
    void setUp(){
        userDao = new DaoFactory().LocalFactory();
        user1 = new User("1", "A", "123");
        user2 = new User("2", "B", "456");
        user3 = new User("3", "C", "789");
    }
    @Test
    void addAndfindById() throws SQLException {
        userDao.deleteAll();
        assertEquals(0,userDao.getCount());

        userDao.add(user1);
        User result = userDao.findById(user1.getId());
        assertEquals(user1.getId(),result.getId());
    }
}