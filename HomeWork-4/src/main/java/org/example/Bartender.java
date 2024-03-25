package org.example;

import java.util.concurrent.ExecutionException;

public class Bartender implements Runnable {

    private String clientName;
    private String drink;
    private final String name;
    private final Bar bar;

    public Bartender(Bar bar, String name) {
        this.bar = bar;
        this.name = name;
        System.out.println(name + " ready to work");
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100);
            do {
                String[] order = bar.getOrders().take().get();
                clientName = order[0];
                drink = order[1];
                prepareDrink();
            } while (!bar.getOrders().isEmpty());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private void prepareDrink() throws InterruptedException {
        System.out.println(name + " take order " + drink + " from " + clientName);
        Thread.sleep(300);
        System.out.println(name + " created " + drink + " to " + clientName);
        Thread.sleep(100);
        bar.serveOrder(clientName);
    }
}
