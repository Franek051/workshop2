package pl.coderslab;

import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;

public class MainDaoAll {

    public static void main(String[] args){
        UserDao userDao = new UserDao();
        User[] users = userDao.findAll();

        if (users != null) {
            for (User user : users) {
                System.out.println(user.toString());
            }
        }
    }
}
