package com.example.DemoTestBot;

import java.util.Scanner;

public class Menu extends MyJDBC {

    public void menu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Виберіть дію: \n 1-Вхід \n 2-Реєстрація \n 3-Перегляд оголошень");
        switch (sc.next()) {
            case "1" -> {
                UserHandler user;
                System.out.println("Введіть логін: ");
                String username = sc.next();
                System.out.println("Введіть пароль: ");
                String password = sc.next();
                if (validateLogin(username, password)) {
                    user = getUser(username, password);

                    System.out.println("Вхід успішний");
                    menu(user);

                } else {
                    System.out.println("Логін або пароль введені невірно");
                }
                menu();
            }
            case "2" -> {
                UserHandler userToReg = new UserHandler();
                if (userToReg.setUser()) {
                    if (register(userToReg)) {
                        System.out.println("Реєстрація успішна!");
                    } else {
                        System.out.println("Реєстрація невдала");

                    }
                }
                menu();
            }
            case "3" -> {
                Post post = new Post();
                post.searchFilter();
                menu();
            }
            default -> {
                System.out.println("Значення: " + sc.next() + ", є не коректним");
                menu();
            }
        }
    }



    public void menu(UserHandler user){
        Scanner sc = new Scanner(System.in);
        System.out.println("Виберіть дію: \n 1-Вихід \n 2-Перегляд оголошень \n 3-Додати оголошення \n 4-Редагувати оголошення()");
        switch (sc.next()) {
            case "1":
                break;
            case "2":
                Post post = new Post();
                post.searchFilter();
                menu(user);
                break;
            case "3":
                Post postToAdd = new Post(user.getUserId(), user.getTelNum());
                if (addPost(postToAdd)) {
                    System.out.println("Оголошення додано успішно");
                } else {
                    System.out.println("Упсс.. щось пішло не так");
                }
                menu(user);
                break;
            case "4":
                Post postToEdit = new Post();
                if (postToEdit.editPost(user.getUserId())) {
                    System.out.println("Оголошення відредаговано/видалено успішно");
                }
                menu(user);
                break;
            default: System.out.println("Значення: " + sc.next() + ", є не коректним");
        }
    }
}
