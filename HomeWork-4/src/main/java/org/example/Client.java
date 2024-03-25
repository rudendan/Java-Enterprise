package org.example;

import java.util.Random;
import java.util.concurrent.Callable;

public class Client implements Callable<String[]> {

    private final Random random = new Random();
    private String drink, name;
    private final Bar bar;
    private static int count = 1;

    public Client(Bar bar) {
        this.bar = bar;
    }

    @Override
    public String[] call() throws Exception {
        Thread.currentThread().setName("Client_" + count++);
        name = Thread.currentThread().getName();
        createOrder();
        return new String[]{name, drink};
    }

    private void createOrder() throws InterruptedException {

        drink = bar.getMenu()[random.nextInt(bar.getMenu().length)];
        Thread.sleep(50);
        System.out.println(name + " ordered " + drink);
    }
}
