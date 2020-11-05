package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private String dbName = "new_table_user";

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection mySQLConnection = Util.getMySQLConnection();
             Statement statement = mySQLConnection.createStatement()) {
            String sql = "CREATE TABLE `mydbusers`.`" + dbName + "` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(45) NOT NULL,\n" +
                    "  `lastName` VARCHAR(45) NOT NULL,\n" +
                    "  `age` INT NOT NULL, PRIMARY KEY (`id`))\n" +
                    "ENGINE = InnoDB\n" +
                    "DEFAULT CHARACTER SET = utf8;\n";
            statement.execute(sql);
        } catch (SQLSyntaxErrorException e) {
            System.out.println("Таблица, которую вы хотите создать, уже существует!");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection mySQLConnection = Util.getMySQLConnection();
             Statement statement = mySQLConnection.createStatement()) {
            String sql = "DROP TABLE `mydbusers`.`" + dbName + "`;";
            statement.execute(sql);
        } catch (SQLSyntaxErrorException e) {
            System.out.println("Таблицы, которую вы хотите удалить, не существует!");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection mySQLConnection = Util.getMySQLConnection();
             Statement statement = mySQLConnection.createStatement()) {
            String sql = "INSERT INTO " + dbName +
                    " (name, lastname, age) values('" +
                    name + "', '" +
                    lastName + "', " +
                    age + ");";
            statement.execute(sql);
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLSyntaxErrorException e) {
            System.out.println("Таблицы, в которую вы хотите сохранить пользователя, не существует!");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection mySQLConnection = Util.getMySQLConnection();
            Statement statement = mySQLConnection.createStatement()) {
            String sql = "DELETE FROM " + dbName + " WHERE id =" + id + ";";
            statement.execute(sql);
        } catch (SQLSyntaxErrorException e) {
            System.out.println("Таблицы, из которой вы хотите удалить пользователя, не существует!");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection mySQLConnection = Util.getMySQLConnection();
             Statement statement = mySQLConnection.createStatement()) {
            String sql = "SELECT * FROM " + dbName + ";";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLSyntaxErrorException e) {
            System.out.println("Таблицы, из которой вы хотите получить пользователей, не существует!");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Connection mySQLConnection = Util.getMySQLConnection();
             Statement statement = mySQLConnection.createStatement()) {
            String sql = "TRUNCATE TABLE " + dbName + ";";
            statement.execute(sql);
        } catch (SQLSyntaxErrorException e) {
            System.out.println("Таблицы, которую вы хотите отчистить, не существует!");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
