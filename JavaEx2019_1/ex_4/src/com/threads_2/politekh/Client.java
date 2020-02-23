package com.threads_2.politekh;

import java.util.ArrayDeque;

class Client {
    private final int id;
    private final String name;
    private final Controller controller;
    private final ArrayDeque<Dish> dishes;

    Client(int id, String name, Controller controller, ArrayDeque<Dish> dishes) {
        this.id = id;
        this.name = name;
        this.controller = controller; //  Привязываем клиента к контроллеру
        this.dishes = dishes;
    }

    boolean dishesIsEmpty() { // Можно было обойтись без этой функции, сделана для удобства и читаемости кода
        synchronized (this.dishes) {
            return this.dishes.isEmpty();
        }
    }

    void makeOrder() throws InterruptedException { // ловим ошибки, если поток неажиданно завершился
        if (!this.dishes.isEmpty()) { // Если кто то заказал блюдо, то запускаем блюдо на готовку
            synchronized (this.dishes) {
                Order order = new Order(this, this.dishes.getFirst()); // прикрепляем блюдо к клиенту
                this.controller.addOrder(order); // Добавляем блюдо в очередь на обработку
                this.dishes.wait();  // Ждем, пока другой поток ( Повар ) не возьмет заказ
            }
        }
    }

    void putOrder(Dish dish) { // Делаем заказ - сразу добавляем блюдо в обработку
        if (dish != null) {
            synchronized (this.dishes) {
                this.dishes.removeFirst();
                this.dishes.notify();
            }
        }
    }

    @Override
    public String toString() {
        return String.format("%s N%d", this.name, this.id); // переопределяем стандартный метод для корректного отображения
    }
}

class Order {
    private final Client client; // сделан клинетом
    private final Dish dish; // заказали блюдо
// обычные геттеры
    Client getClient() {
        return client;
    }

    Dish getDish() {
        return dish;
    }
    //конструктор
    Order(Client client, Dish dish) {
        this.client = client;
        this.dish = dish;
    }

    @Override
    public String toString() {
        // переопределяем метод для корректного отображения названия блюда
        return String.format("%s order %s", this.client, this.dish);
    }
}
class ThreadClient extends Thread {
    private final Client client;

    ThreadClient(Client client) {
        this.client = client;
    }

    @Override
    public void run() {
        System.out.printf("%s: come to restaurant;%s", this.client, System.lineSeparator());
        try {
            while (!this.client.dishesIsEmpty()) { // Пока у клиента есть желания - заказывает
                this.client.makeOrder();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // уходит когда все принесли
        System.out.printf("%s: exit to restaurant;%s", this.client, System.lineSeparator());
    }
}