package com.example.DemoTestBot;

import java.util.Scanner;
/**
 * Клас для взаємодії з об'єктом користувач.
 * Зберігає основний список полів та методи геттери та сеттери
 * @see Database
 */
public class UserHandler extends Database {

    private int userId;
    private String username;
    private String password;
    private String telNum;
    private String email;

    Scanner sc = new Scanner(System.in);
    public UserHandler() {
    }

    public UserHandler(int userId, String username, String password, String telNum, String email) {

        this.userId = userId;
        this.username = username;
        this.password = password;
        this.telNum = telNum;
        this.email = email;
    }

    /**
     * Присвоєння змінних для подальшої реєстрації користувача
     */
    public boolean setUser() {

        System.out.println("Введіть ім'я користувача: ");
        username = sc.next();
        if(checkUser(username)){
            System.out.println("Акаунт з таким ім'ям вже існує, можливо це ваш?");
            return false;
        }
        while (password == null || password.length() < 8){
            System.out.println("Введіть пароль(мінімум 8 символів): ");
            password = sc.next();
            if(password.length() < 8 ) {
                System.out.println("Помилка пароль закороткий, спробуйте ще раз ");
            }
        }

        while (telNum == null || telNum.length() < 10){
            System.out.println("Введіть номер телефону: ");
            telNum = sc.next();
            if(telNum.length() < 10 ) {
                System.out.println("Помилка номер телефону надто короткий, спробуйте ще раз ");
            }
        }

        System.out.println("Введіть електронну пошту(якщо не бажаєте її вказувати внесіть \"-\"): ");
        email = sc.next();
        if(email.equals("-")){
            email = null;
        }
        return true;
    }

    /**
     * Підтвердження наміру користувача видалити акаунт1
     */
    public void delUser() {
        System.out.println("!!!Всі ваші оголошення будуть втрачені!!! ");
        System.out.println("Для підтвердження операції введіть пароль \n 0-Відміна: ");
        String tempPassword = sc.next();
        if(!tempPassword.equals("0")){
            if(password.equals(tempPassword)){
                if(delUser(userId)){
                    System.out.println("Видалення пройшло успшіно");
                }
            }else {
                System.out.println("Пароль введено не вірно");
            }
        }
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
