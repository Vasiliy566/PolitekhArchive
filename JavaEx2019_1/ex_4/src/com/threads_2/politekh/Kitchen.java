package com.threads_2.politekh;

class Dish {
    private final int id;
    private final String name;

    Dish(int id, String name) { // обвчный конструктор
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() { // Переопределяем стандартный метод чтобы корректно печатать названия блюд
        return String.format("Dish N%d - %s", this.id, this.name);
    }
}

class ThreadCook extends Thread {
    private final Controller controller;

    ThreadCook(Controller controller) {   // привязываем новый объект к контроллеру
        this.controller = controller;
    }

    @Override
    // Название run совпадает с java.lang.Thread.run(), так что нелья использовать throws InterruptedException
    // Приходится try / catch
    public void run( )   {
        System.out.println("The cook begun work.");
        try {
            while (!Thread.currentThread().isInterrupted()) { // Пока поток не прерван - работаем
                Order order = this.controller.getNextOrder(); // Передаем заказ повару
                System.out.printf("The cock: make order %s for %s;%s", order.getDish(), order.getClient(), System.lineSeparator());
                this.controller.addMakeOrder(order); // Заказ готов
            }
        } catch (Exception e) {
            System.err.println("Cook was interrupted");
        }
        System.out.println("The cook end work.");  // По окончании блюд повар заканчивает работу
    }
}

class ThreadWaiter extends Thread {
    private final Controller controller;

    ThreadWaiter(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        System.out.println("The waiter begun work.");
        try {
            while (!Thread.currentThread().isInterrupted()) {  // Пока поток не прерван - работаем
                Order order = this.controller.getNextMakeOrder();
                order.getClient().putOrder(order.getDish()); // Отнесли заказ
                //передаем заказ order.getDish() для клиента order.getClient()
                System.out.printf("The waiter: give order %s for %s;%s", order.getDish(), order.getClient(), System.lineSeparator());
            }
        } catch (InterruptedException e) {
            System.err.println("Waiter was interrupted");
        }
        System.out.println("The waiter end work.");
    }
}