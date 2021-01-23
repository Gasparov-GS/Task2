package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {


        for (int i = 0; i < 5; i++) {
            System.out.println("пример");
        }


        int a = 0;
        while (a < 5) {
            System.out.println("fsdfg");
            a++;
        }



        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        String lastName = sc.nextLine();
        byte age = sc.nextByte();
        sc.close();
        if(name.equals("Вячеслав")) {
            lastName = "Будующий грач";
        }



        UserService userService = new UserServiceImpl(new UserDaoJDBCImpl());
        //userService.createUsersTable();
        userService.saveUser(name,lastName,age);

        userService.getAllUsers().forEach(System.out::println);
        //userService.cleanUsersTable();
        //userService.dropUsersTable();
    }
}
