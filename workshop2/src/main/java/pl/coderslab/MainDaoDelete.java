package pl.coderslab;

import pl.coderslab.entity.UserDao;

public class MainDaoDelete {

    public static void main (String[] args) {

        UserDao userDao = new UserDao();
        userDao.delete(1);
    }
}
