package pl.coderslab;

import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;

public class MainDaoRead {

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        int existingUserId = 1;

        User user = userDao.read(existingUserId);
        if (user != null) {

            System.out.println("Metoda zwróciła użytkownika: " + user);

            System.out.println("ID: " + user.getId());
            System.out.println("Nazwa użytkownika: " + user.getUserName());
            System.out.println("Email: " + user.getEmail());
        } else {
            System.out.println("Metoda zwróciła null. Użytkownik o podanym ID nie istnieje.");
        }
        User readNotExist = userDao.read(2);

        System.out.println(readNotExist);

    }
}
