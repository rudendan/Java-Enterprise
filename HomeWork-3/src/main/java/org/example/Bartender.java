package org.example;

public class Bartender implements Runnable{

    private final Bar bar;
    private String clientName;
    private String drink;
    private final String name;

    public Bartender(Bar bar, String name) {
        this.bar = bar;
        this.name = name;
    }

    public void run() {
        try {
            do {
                String[] order = bar.getOrders().take();
                clientName = order[0];
                drink = order[1];
                prepareDrink();
            } while (!bar.getOrders().isEmpty());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
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
