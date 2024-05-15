package com.example.DemoTestBot;

import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Post extends MyJDBC {

    private int userId;
    private String telNum;
    private String type;
    private String brand;
    private String model;
    private String engine;
    private int year;
    private int cost;
    private String description;

    Scanner sc = new Scanner(new InputStreamReader(System.in));

    public Post() {

    }
    public Post(int userId, String telNum){

        this.userId = userId;
        this.telNum = telNum;
        while (type == null){
            System.out.println("Виберіть тип транспорту: \n 1-Трактор \n 2-Зернозбиральний комбайн \n 3-Кормозбиральний комбайн " +
                    "\n 4-Самохідний обпрсикувач \n 5-Інша самохідна установка");
            switch (sc.next()) {
                case "1" -> type = "Трактор";
                case "2" -> type = "Зернозбиральний комбайн";
                case "3" -> type = "Кормозбиральний комбайн";
                case "4" -> type = "Самохідний обпрсикувач";
                case "5" -> type = "Інша самохідна установка";
                default -> System.out.printf("Значення: %s, є не коректним", sc.next());
            }
        }
        sc.nextLine();
        System.out.println("Введіть марку: ");
        brand = sc.nextLine();


        System.out.println("Введіть модель: ");
        model = sc.next();


        System.out.println("Введіть модель двигуна: ");
        engine = sc.next();

        System.out.println("Введіть рік випуску: ");
        while (year == 0) {
            try {
                year = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Невірний ввід. Спробуйте знову.");
                sc.next();
            }
        }

        System.out.println("Введіть ціну $: ");
        while (cost == 0) {
            try {
                cost = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Невірний ввід. Спробуйте знову.");
                sc.next();
            }
        }
            sc.nextLine();
            System.out.println("Введіть опис : ");
            description = sc.nextLine();
    }




    public void searchFilter() {
        int costFrom = 0;
        int costTo = 0;

        int searchFilter = 1;

        System.out.println("   Пошук   \nВведіть тип засобу: ");
        while (type == null){
            System.out.println("Виберіть тип транспорту: \n 1-Трактор \n 2-Зернозбиральний комбайн \n 3-Кормозбиральний комбайн " +
                    "\n 4-Самохідний обпрсикувач \n 5-Інша самохідна установка");
            switch (sc.next()) {
                case "1" -> type = "Трактор";
                case "2" -> type = "Зернозбиральний комбайн";
                case "3" -> type = "Кормозбиральний комбайн";
                case "4" -> type = "Самохідний обпрсикувач";
                case "5" -> type = "Інша самохідна установка";
                default -> System.out.printf("Значення: %s, є не коректним", sc.next());
            }
        }

        System.out.println("Додати фільтри для марки? \n 1-Так 2-Ні");
        if (sc.next().equals("1")) {
            System.out.println("Введіть марку: ");
            brand = sc.next();
            searchFilter++;
        }

        if (brand != null){
            System.out.println("Додати фільтри для моделі? \n 1-Так 2-Ні");
            if (sc.next().equals("1")){
                System.out.println("Введіть модель: ");
                model = sc.next();
                System.out.println(model);
                searchFilter = searchFilter + 3;
            }
        }

        System.out.println("Додати фільтри для ціни? \n 1-Так 2-Ні");
        if (sc.next().equals("1")){

            searchFilter = searchFilter + 2;
            System.out.println("Введіть ціну від(мінімум 1): ");
            while (costFrom == 0) {
                try {
                    costFrom = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Невірний ввід. Спробуйте знову.");
                    sc.next();
                }
            }

            System.out.println("Введіть ціну до(мінімум 1): ");
            while (costTo == 0) {
                try {
                    costTo = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Невірний ввід. Спробуйте знову.");
                    sc.next();
                }
            }
        }
        System.out.println(" \n \n");
        switch (searchFilter) {
            case 1 -> showPost(searchPost(type));
            case 2 -> showPost(searchPost(type, brand));
            case 3 -> showPost(searchPost(type, costFrom, costTo));
            case 4 -> showPost(searchPost(type, brand, costFrom, costTo));
            case 5 -> showPost(searchPost(type, brand, model));
            case 7 -> showPost(searchPost(type, brand, model, costFrom, costTo));
        }
    }

    public boolean editPost(int userId){
        ResultSet resultSet = searchPost(userId);
        if(resultSet == null){
            System.out.println("Оголошення відсутні");
            return false;
        }
        showPost(searchPost(userId));

        System.out.println("Виберіть пост який хочете редагувати/видалити" +
                "\n для цього вкажіть ID(набір цифр під описом)");
        int postId = -1 ;
        while (postId == -1) {
            try {
                postId = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Невірний ввід. Спробуйте знову.");
                sc.next();
            }
        }
        boolean temp = false ;
        System.out.println("1-Редагувати 2-Видалити");
        switch (sc.next()) {
            case "1" -> {
                System.out.println("Відредагувати \n 1-Тип \n 2-Марка \n 3-Модель  \n 4-Двигун" +
                        "\n 5-Рік випуску \n 6-Ціну \n 7-Опис");
                String colName = sc.next();
                System.out.println("Введіть нове значення:");
                String newValue = null;
                int intValue = 0;
                if (colName.equals("5") || colName.equals("6")) {
                    try {
                        intValue = sc.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Невірний ввід. Спробуйте знову.");
                        sc.next();
                    }
                } else {
                    sc.nextLine();
                    newValue = sc.nextLine();
                }
                switch (colName) {
                    case "1" -> temp = updatePost(postId, "type", newValue);
                    case "2" -> temp = updatePost(postId, "brand", newValue);
                    case "3" -> temp = updatePost(postId, "model", newValue);
                    case "4" -> temp = updatePost(postId, "engine", newValue);
                    case "5" -> temp = updatePost(postId, "year", intValue);
                    case "6" -> temp = updatePost(postId, "cost", intValue);
                    case "7" -> temp = updatePost(postId, "description", newValue);
                }
            }
            case "2" -> temp = delPost(postId);
        }
        return temp;
    }


    public void showPost(ResultSet resultSet){
        try{
            int num = 0;
            while(num <= 4) {
                if(resultSet.next()){
                    num++;
                    System.out.println(resultSet.getString("type"));
                    System.out.print(resultSet.getString("brand") + " ");
                    System.out.println(resultSet.getString("model"));
                    System.out.println(resultSet.getString("year"));
                    System.out.println("Двигун: " + resultSet.getString("engine"));
                    System.out.println("Ціна($): " + resultSet.getString("cost"));
                    System.out.println("Контакти: " + resultSet.getString("telNum"));
                    System.out.println("    Опис    " + resultSet.getString("description"));
                    System.out.println(resultSet.getInt("post_id"));
                    System.out.println("____________________\n");
                    if (num == 3) {
                        System.out.println("1-Наступна сторінка 2-Меню");
                        switch (sc.nextInt()) {
                            case 1 -> num = 1;
                            case 2 -> num++;
                        }
                    }
                }else{
                    System.out.println("Оголошень не знайдено");
                    break;
                }

            }
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Помилка, спробуйте пізніше \n або зверніться до техпідтримки \n https://t.me/AgriShop_bot");
        }
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
