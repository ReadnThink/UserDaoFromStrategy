package dao;

import domain.User;

public class DaoFactory {
    public UserDao LocalFactory(){
        LocalConnectionMaker localConnectionMaker = new LocalConnectionMaker();
        UserDao userDao = new UserDao(localConnectionMaker);
        return userDao;
    }

    public UserDao AwsFactory(){
        AwsConnectionMaker awsConnectionMaker = new AwsConnectionMaker();
        UserDao userDao = new UserDao(awsConnectionMaker);
        return userDao;
    }
}
