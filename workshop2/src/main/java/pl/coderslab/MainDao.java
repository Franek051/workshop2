package pl.coderslab;

import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;

public class MainDao {
    public static void main(String[] args) {
        UserDao userDao  = new UserDao();
        User user = new User();
        user.setUserName("Franciszek");
        user.setEmail("franciszek.tatar@coderslab.pl");
        user.setPassword("hasło");

        userDao.create(user);
    }

}
