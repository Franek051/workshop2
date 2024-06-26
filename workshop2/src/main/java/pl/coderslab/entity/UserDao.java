package pl.coderslab.entity;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.DbUtil;

import java.sql.*;
import java.util.Arrays;


public class UserDao {
    public String hashPassword(String password) {

        return BCrypt.hashpw(password, BCrypt.gensalt());

    }


    private static final String CREATE_USER_QUERY =
            "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";

    private static final String READ_USER_QUERY =
            "SELECT * FROM users where id = ?";


    private static final String UPDATE_USER_QUERY =
            "UPDATE users SET username = ?, email = ?, password = ? where id = ?";

    private static final String DELETE_USER_QUERY =
            "DELETE FROM users WHERE id = ?";

    private static final String FIND_ALL_USERS_QUERY =
            "SELECT * FROM users";

    public User create(User user){
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement
                 = conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User read(int userId) {
      try (Connection conn = DbUtil.getConnection()) {
          PreparedStatement statement =
                  conn.prepareStatement(READ_USER_QUERY);
          statement.setInt(1, userId);
          ResultSet resultSet = statement.executeQuery();

          if (resultSet.next()) {
              User user = new User();
              user.setId(resultSet.getInt("id"));
              user.setUserName(resultSet.getString("userName"));
              user.setEmail(resultSet.getString("email"));
              user.setPassword(resultSet.getString("password"));
              return user;
          }

      }
      catch (SQLException e) {
              e.printStackTrace();
      }
          return null;


    }
    public void update(User user) {
        try (Connection conn = DbUtil.getConnection()){
            PreparedStatement statement =
                    conn.prepareStatement(UPDATE_USER_QUERY);
            statement.setInt(1, user.getId());
            statement.setString(2, user.getUserName());
            statement.setString(3, user.getEmail());
            statement.setString(4, this.hashPassword(user.getPassword()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(int userId) {
        try (Connection conn = DbUtil.getConnection()){
            PreparedStatement statement =
                    conn.prepareStatement(DELETE_USER_QUERY);
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public User[] findAll() {
        try (Connection conn = DbUtil.getConnection()) {
            User[] users = new User[0];
            PreparedStatement statement =
                    conn.prepareStatement(FIND_ALL_USERS_QUERY);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("userName"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                users = addToArray(user, users);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private User[] addToArray(User u, User[] users) {
        User[] tmpUsers = Arrays.copyOf(users, users.length + 1);
        tmpUsers[users.length] = u;
        return tmpUsers;

    }


}
