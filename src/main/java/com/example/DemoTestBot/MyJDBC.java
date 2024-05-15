package com.example.DemoTestBot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyJDBC {
    public static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/agricultshop";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "Ghrj2341qqq@";
    public static final String DB_USERS_TABLE = " `agricultshop`. `user_table` ";
    public static final String DB_POSTS_TABLE = " `agricultshop`. `post_table` ";
    Connection connection;
    // register new user to the database
    // true - register success
    // false - register failure

    public boolean register(UserHandler user){
        //first check of the username already exists in the database
        if (!checkUser(user.getUsername())) {
            //connect to database
            try {
                connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                // create insert query
                PreparedStatement insertUser = connection.prepareStatement("INSERT INTO " + DB_USERS_TABLE + "(username, password, telNum, email)" + "VALUES (?, ?, ?, ?)");
                // insert parameters in the insert query
                insertUser.setString(1, user.getUsername());
                insertUser.setString(2, user.getPassword());
                insertUser.setString(3, user.getTelNum());
                insertUser.setString(4, user.getEmail());
                // update db with new user
                insertUser.executeUpdate();
                return true;

                } catch(SQLException e){
                    e.printStackTrace();
                    System.out.println("Помилка, спробуйте пізніше \n або зверніться до техпідтримки \n https://t.me/AgriShop_bot");
                }
            }
        return false;
    }



    //check if username already exists in the database
    // true - user exists in the databse
    // false - user doesn`t exists
    public boolean checkUser(String username){
        try{
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            PreparedStatement checkUserExists = connection.prepareStatement("select * from " + DB_USERS_TABLE + " where username = ? ");

            checkUserExists.setString(1, username);

            ResultSet resultSet = checkUserExists.executeQuery();

            if(!resultSet.isBeforeFirst()){
                return false;
            }
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Помилка, спробуйте пізніше \n або зверніться до техпідтримки \n https://t.me/AgriShop_bot");
        }
        return true;

    }




    public boolean validateLogin(String username, String password){
        try{
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // create select query
            PreparedStatement validateUser = connection.prepareStatement("SELECT * FROM " + DB_USERS_TABLE + " WHERE USERNAME = ? AND PASSWORD = ?");
            validateUser.setString(1,username);
            validateUser.setString(2,password);

            ResultSet resultSet = validateUser.executeQuery();

            if(!resultSet.isBeforeFirst()){
                return false;
            }

        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Помилка, спробуйте пізніше \n або зверніться до техпідтримки \n https://t.me/AgriShop_bot");
        }
        return true;
    }

    public UserHandler getUser(String username, String password) {

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement getUser = connection.prepareStatement("SELECT * FROM" + DB_USERS_TABLE + " WHERE `username` = ? AND `password` = ? ");
            getUser.setString(1, username);
            getUser.setString(2, password);

            ResultSet resultSet = getUser.executeQuery();
            resultSet.next();
            return new UserHandler(resultSet.getInt("user_id"), resultSet.getString("username"), resultSet.getString("telNum"), resultSet.getString("email"));
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Помилка, спробуйте пізніше \n або зверніться до техпідтримки \n https://t.me/AgriShop_bot");
        }
        return null;
    }

    public ResultSet searchPost(String type){
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement getPost = connection.prepareStatement("SELECT * FROM "+ DB_POSTS_TABLE +" WHERE " +
                    "`type` = ? ");
            getPost.setString(1, type);

            return getPost.executeQuery();

        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Помилка, спробуйте пізніше \n або зверніться до техпідтримки \n https://t.me/AgriShop_bot");
        }
        return null;
    }

    public ResultSet searchPost(String type, String brand){
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement getPost = connection.prepareStatement("SELECT * FROM "+ DB_POSTS_TABLE +" WHERE " +
                    "`type` = ? AND `brand` = ?");
            getPost.setString(1, type);
            getPost.setString(2, brand);

            return getPost.executeQuery();

        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Помилка, спробуйте пізніше \n або зверніться до техпідтримки \n https://t.me/AgriShop_bot");
        }
        return null;
    }

    public ResultSet searchPost(String type, int costFrom, int costTo){
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement getPost = connection.prepareStatement("SELECT * FROM "+ DB_POSTS_TABLE +" WHERE " +
                    "`type` = ? AND `cost` >= ? AND `cost` <= ?");
            getPost.setString(1, type);
            getPost.setInt(2, costFrom);
            getPost.setInt(3, costTo);

            return getPost.executeQuery();

        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Помилка, спробуйте пізніше \n або зверніться до техпідтримки \n https://t.me/AgriShop_bot");
        }
        return null;
    }

    public ResultSet searchPost(String type, String brand, String model){
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement getPost = connection.prepareStatement("SELECT * FROM "+ DB_POSTS_TABLE +" WHERE " +
                    "`type` = ? AND `brand` = ? AND `model` = ?");
            getPost.setString(1, type);
            getPost.setString(2, brand);
            getPost.setString(3, model);
            return getPost.executeQuery();

        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Помилка, спробуйте пізніше \n або зверніться до техпідтримки \n https://t.me/AgriShop_bot");
        }
        return null;
    }

    public ResultSet searchPost(String type, String brand, int costFrom, int costTo){
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement getPost = connection.prepareStatement("SELECT * FROM "+ DB_POSTS_TABLE +" WHERE " +
                    "`type` = ? AND `brand` = ?" +
                    "AND `cost` >= ? AND `cost` <= ?");
            getPost.setString(1, type);
            getPost.setString(2, brand);
            getPost.setInt(3, costFrom);
            getPost.setInt(4, costTo);

            return getPost.executeQuery();

        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Помилка, спробуйте пізніше \n або зверніться до техпідтримки \n https://t.me/AgriShop_bot");
        }
        return null;
    }

    public ResultSet searchPost(String type, String brand, String model, int costFrom, int costTo){
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement getPost = connection.prepareStatement("SELECT * FROM "+ DB_POSTS_TABLE +" WHERE " +
                    "`type` = ? AND `brand` = ? AND `model` = ?" +
                    "AND `cost` >= ? AND `cost` <= ?");
            getPost.setString(1, type);
            getPost.setString(2, brand);
            getPost.setString(3, model);
            getPost.setInt(4, costFrom);
            getPost.setInt(5, costTo);

            return getPost.executeQuery();

        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Помилка, спробуйте пізніше \n або зверніться до техпідтримки \n https://t.me/AgriShop_bot");
        }
        return null;
    }




    public boolean addPost(Post post){
        try {
            //connect to database
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            // create insert query
            PreparedStatement insertPost = connection.prepareStatement("INSERT INTO " + DB_POSTS_TABLE +
                    "(user_id, type, brand, model, engine, year, cost, description, telNum )"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            // insert parameters in the insert query
            insertPost.setInt(1, post.getUserId());
            insertPost.setString(2, post.getType());
            insertPost.setString(3, post.getBrand());
            insertPost.setString(4, post.getModel());
            insertPost.setString(5, post.getEngine());
            insertPost.setInt(6, post.getYear());
            insertPost.setInt(7, post.getCost());
            insertPost.setString(8, post.getDescription());
            insertPost.setString(9, post.getTelNum());

            // update db with new postI
            insertPost.executeUpdate();
            return true;

        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Помилка, спробуйте пізніше \n або зверніться до техпідтримки \n https://t.me/AgriShop_bot");
        }
        return false;
    }

    public ResultSet searchPost(int userId){
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement getPost = connection.prepareStatement("SELECT * FROM "+ DB_POSTS_TABLE +" WHERE `user_id` = ?");
            getPost.setInt(1, userId);
            ResultSet resultSet = getPost.executeQuery();

            if(resultSet.isBeforeFirst()){
                return resultSet;
            }
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Помилка, спробуйте пізніше \n або зверніться до техпідтримки \n https://t.me/AgriShop_bot");
        }
        return null;
    }


    public boolean updatePost(int post_id,  String colName, String newValue){
        try {
            //connect to database
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            // create insert query
            PreparedStatement updatePost = connection.prepareStatement("UPDATE " + DB_POSTS_TABLE + "SET " + colName + " = ?  WHERE post_id = ?");

            // insert parameters in the insert query
            updatePost.setString(1, newValue);
            updatePost.setInt(2, post_id);

            // update db with new post
            updatePost.executeUpdate();
            return true;

        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Помилка, спробуйте пізніше \n або зверніться до техпідтримки \n https://t.me/AgriShop_bot");
        }
        return false;
    }


    public boolean updatePost(int post_id,  String colName, int newValue){
        try {
            //connect to database
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            // create insert query
            PreparedStatement updatePost = connection.prepareStatement("UPDATE " + DB_POSTS_TABLE + "SET " + colName + " = ?  WHERE post_id = ?");
            // insert parameters in the insert query
            updatePost.setInt(1, newValue);
            updatePost.setInt(2, post_id);

            // update db with new post
            updatePost.executeUpdate();
            return true;

        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Помилка, спробуйте пізніше \n або зверніться до техпідтримки \n https://t.me/AgriShop_bot");
        }
        return false;

    }

    public boolean delPost(int post_id){
        try {
            //connect to database
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            // create insert query
            PreparedStatement updatePost = connection.prepareStatement("DELETE FROM "  + DB_POSTS_TABLE + "WHERE post_id = ?");
            // insert parameters in the insert query
            updatePost.setInt(1, post_id);

            // update db with new post
            updatePost.executeUpdate();
            return true;

        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Помилка, спробуйте пізніше \n або зверніться до техпідтримки \n https://t.me/AgriShop_bot");
        }
        return true;

    }
}
