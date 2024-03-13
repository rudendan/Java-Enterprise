package org.example;

import java.util.HashMap;
import java.util.Random;

public class Client implements Runnable{

    private String name;
    private Bar bar;
    private Random random = new Random();

    public Client(Bar bar) {
        ;
        this.bar = bar;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void run() {
        try {
            createOrder();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void createOrder() throws InterruptedException {

        String drink = bar.getMenu()[random.nextInt(bar.getMenu().length)];
        String[] order = {name, drink};

    }
}
