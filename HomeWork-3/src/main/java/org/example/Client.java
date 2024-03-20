package org.example;

import java.util.Random;

public class Client implements Runnable{

    private String name;
    private final Bar bar;
    private final Random random = new Random();

    public Client(Bar bar) {
        this.bar = bar;
    }

    public void run() {
        try {
            name = Thread.currentThread().getName();
            createOrder();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void createOrder() throws InterruptedException {

        String drink = bar.getMenu()[random.nextInt(bar.getMenu().length)];
        String[] order = {name, drink};
        Thread.sleep(50);
        System.out.println("Client " + name + " ordered " + drink);
        bar.getOrders().add(order);
    }
}
