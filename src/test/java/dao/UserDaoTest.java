package dao;

import domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoFactory.class)
class UserDaoTest {
    @Autowired
    ApplicationContext context;
    UserDao userDao;
    User user1;
    User user2;
    User user3;

    @BeforeEach
    void setUp(){
        userDao = context.getBean("LocalFactory", UserDao.class);
        user1 = new User("1", "A", "123");
        user2 = new User("2", "B", "456");
        user3 = new User("3", "C", "789");
    }
    @Test
    void addAndfindById() throws SQLException {
        userDao.deleteAll();
        assertEquals(0, userDao.getConut());
        userDao.add(user1);
        User result = userDao.findById(user1.getId());
        assertEquals(user1.getId(),result.getId());
    }
}