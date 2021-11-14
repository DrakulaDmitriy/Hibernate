package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Иван", "Иванов", (byte) 20);
        userService.saveUser("Александр", "Степанов", (byte) 52);
        userService.saveUser("Дмитрий", "Соколов", (byte) 34);
        userService.saveUser("Владимир", "Попов", (byte) 47);

        for (User user : userService.getAllUsers()) {
            System.out.println(user);
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
