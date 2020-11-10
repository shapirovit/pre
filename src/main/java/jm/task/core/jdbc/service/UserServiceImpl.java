package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    public void createUsersTable() {
        new UserDaoHibernateImpl().createUsersTable();
//        new UserDaoJDBCImpl().createUsersTable();
    }

    public void dropUsersTable() {
        new UserDaoHibernateImpl().dropUsersTable();
//        new UserDaoJDBCImpl().dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        new UserDaoHibernateImpl().saveUser(name, lastName, age);
//        new UserDaoJDBCImpl().saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        new UserDaoHibernateImpl().removeUserById(id);
//        new UserDaoJDBCImpl().removeUserById(id);
    }

    public List<User> getAllUsers() {
        return new UserDaoHibernateImpl().getAllUsers();
//        return new UserDaoJDBCImpl().getAllUsers();
    }

    public void cleanUsersTable() {
        new UserDaoHibernateImpl().cleanUsersTable();
//        new UserDaoJDBCImpl().cleanUsersTable();
    }
}
