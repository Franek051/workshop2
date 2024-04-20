package pl.coderslab;

import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;

public class MainDaoUpdate {
    private static UserDao userDao;

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        User userToUpdate = userDao.read(1);

            userToUpdate.setUserName("Franciszek");
            userToUpdate.setEmail("franciszek.tatar@coderslab.pl");
            userToUpdate.setPassword("hasło");
            userDao.update(userToUpdate);


    }
}
