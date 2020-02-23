package com.threads_2.politekh;

// Почти mvc - приложение, где
// model - Client, Waiter, Cook
// view - в каждой модели свой раздел view
// controller - Controller, иммитирует действия пользователя, интерпретирует их и меняет модель
public class main {
    private static final String[] DISHES = {"Soup", "Salad", "Coffee"};
    public static void main(String[] args) {
        try{

            new Controller(Integer.valueOf(args[0]), DISHES).init();}

        catch(Exception e){

            System.err.println(" Incorrect Input! ");

        }
    }
}

