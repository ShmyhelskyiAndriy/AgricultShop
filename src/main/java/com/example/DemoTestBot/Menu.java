package com.example.DemoTestBot;

import java.util.Scanner;
/**
 * Клас для навігації в системі, тут відбувається виклик основних функцій системи,
 * в залежності від дій які хоче виконати користувач.
 * @see UserHandler
 * @see Database
 * @see Post
 */
public class Menu extends Database {
    /**
     * Метод навігації в системі для неавторизованого користувача
     */
    public void menu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Виберіть дію: \n 1-Вхід \n 2-Реєстрація \n 3-Перегляд оголошень");
        System.out.println("Техпідтримка: \nhttps://t.me/AgriShop_bot");
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
                System.out.printf("Значення: %s, є не коректним\n ", sc.next());
                menu();
            }
        }
    }


    /**
     * Метод навігації в системі для авторизованого користувача
     */
    public void menu(UserHandler user){
        Scanner sc = new Scanner(System.in);
        System.out.println("Виберіть дію: \n 1-Перегляд оголошень \n 2-Додати оголошення \n 3-Редагувати оголошення\n 4-Вихід\n 5-Видалити акаунт");
        System.out.println("Техпідтримка: \nhttps://t.me/AgriShop_bot");
        switch (sc.next()) {
            case "1":
                Post post = new Post();
                post.searchFilter();
                menu(user);
                break;
            case "2":
                Post postToAdd = new Post();
                postToAdd.setPost(user.getUserId(),user.getTelNum());
                if (addPost(postToAdd)) {
                    System.out.println("Оголошення додано успішно");
                } else {
                    System.out.println("Упсс.. щось пішло не так");
                }
                menu(user);
                break;
            case "3":
                Post postToEdit = new Post();
                if (postToEdit.editPost(user.getUserId())) {
                    System.out.println("Оголошення відредаговано/видалено успішно");
                }
                menu(user);
                break;
            case "4":
                break;
            case "5":
                user.delUser();
                menu(user);
                break;
            default:
                System.out.printf("Значення: %s, є не коректним", sc.next());
        }
    }
}
