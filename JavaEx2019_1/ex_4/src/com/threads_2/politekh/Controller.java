package com.threads_2.politekh;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

class Controller {
    private final int amountClients;
    private final String[] dishes; // блюда

    private final List<ThreadClient> poolClients; // клиенты
    private final ThreadCook cook; // повар
    private final ThreadWaiter waiter; // официант

    private final ArrayDeque<Order> orders; // очередь заказов
    private final ArrayDeque<Order> makeOrders;  // очередь клиентов чтобы сделать заказ

    Controller(int amountClients, String[] dishes) { // обычный конструктор
        this.amountClients = amountClients;
        this.dishes = dishes;

        this.orders = new ArrayDeque<>();
        this.makeOrders = new ArrayDeque<>();

        this.poolClients = new ArrayList<>();
        this.cook = new ThreadCook(this);
        this.waiter = new ThreadWaiter(this);
    }

    private ArrayDeque<Dish> getListOrders(String[] dishes) { // переводим строку с блюдами в лист
        ArrayDeque<Dish> resultList = new ArrayDeque<>();
        if (dishes != null) {
            for (int i = 0; i < dishes.length; i++) {
                resultList.add(new Dish(i + 1, dishes[i]));
            }
        }
        return resultList;
    }

    void init() { // запуск работы потоков - повара, официантки и посетителей
        System.out.println("Start program...");
        this.cook.start();
        this.waiter.start();
        this.startClients();
        while (this.clientsIs()) {}     // Работаем до последнего клиента!
        this.stop();
        System.out.println("End program...");
    }

    private void startClients() {  // добавление нового клиента
        for (int count = 0; count < this.amountClients; count++) {
            ThreadClient client = new ThreadClient(new Client(count + 1, "Client", this, this.getListOrders(this.dishes)));
            this.poolClients.add(client);
            client.start();
        }
    }

    private void stop() { // закончили работу
        this.cook.interrupt();
        this.waiter.interrupt();

    }

    private boolean clientsIs() {  // Проверяем, остались ли еще клиенты
        boolean result = false;
        for (ThreadClient client : this.poolClients) {
            if (result = client.isAlive()) {
                break;
            }
        }
        return result;
    }

    // Создаем следующий заказ
     Order getNextMakeOrder() throws InterruptedException {
        Order order = null;
        synchronized (this.makeOrders) {
            while (this.makeOrders.isEmpty()) {
                this.makeOrders.wait();
            }
            order = this.makeOrders.removeFirst();
        }
        return order;
    }

    //Переадем следующий заказ
     Order getNextOrder() throws InterruptedException {
        Order order;
        synchronized (this.orders) {
            while (this.orders.isEmpty()) {
                this.orders.wait();
            }
            order = this.orders.removeFirst();
        }
        return order;
    }

    // Делаем новый заказ
    void addOrder(Order order) {
        if (order != null) {
            synchronized (this.orders) {
                this.orders.add(order);
                System.out.println(order);
                this.orders.notify();
            }
        }
    }
    // Сделали заказ и добавляем его к сделанным
    void addMakeOrder(Order order) {
        if (order != null) {
            synchronized (this.makeOrders) {
                this.makeOrders.add(order);
                this.makeOrders.notify();
            }
        }
    }
}