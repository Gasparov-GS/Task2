package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Игорь","Богданов", (byte) 33);
        userService.saveUser("Петр","Петров", (byte) 33);
        userService.saveUser("Сергей","Сергеев", (byte) 33);
        userService.saveUser("Аристарх","Аристархов", (byte) 33);
        userService.getAllUsers().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
