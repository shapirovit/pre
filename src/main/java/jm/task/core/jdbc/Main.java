package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser("Ivan", "Petrov", (byte) 21);
        userDaoJDBC.saveUser("John", "Smith", (byte) 25);
        userDaoJDBC.saveUser("Oleg", "Ivanov", (byte) 19);
        userDaoJDBC.saveUser("Konstantin", "Egorov", (byte) 34);
        List<User> users = userDaoJDBC.getAllUsers();
        for (User user: users) {
            System.out.println(user);
        }
        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.dropUsersTable();
    }
}
