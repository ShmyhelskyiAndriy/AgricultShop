package com.example.DemoTestBot;

import java.util.Scanner;

public class UserHandler extends MyJDBC {

    private int userId;
    private String username;
    private String password;
    private String telNum;
    private String email;

    public UserHandler() {
    }

    public UserHandler(int userId, String username, String telNum, String email) {
        this.userId = userId;
        this.username = username;
        this.telNum = telNum;
        this.email = email;
    }

    public boolean setUser() {
        Scanner sc = new Scanner(System.in);

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
