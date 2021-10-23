package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.dropUsersTable();
        userService.createUsersTable();

        createUser("Иван", "Иванов", 20, userService);

        createUser("Александр", "Степанов", 52, userService);

        createUser("Дмитрий", "Соколов", 34, userService);

        createUser("Владимир", "Попов", 47, userService);

        System.out.println(userService.getAllUsers().toString());
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }

    private static void createUser(String name, String lastName, int age, UserService userService) {
        User user = new User(name, lastName, (byte) age);
        userService.saveUser(user.getName(), user.getLastName(), user.getAge());
        System.out.println("User с именем " + user.getName() + " добавлен в базу данных.");
    }
}
