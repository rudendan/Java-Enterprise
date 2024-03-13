package org.example;

import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Bar {

    private String[] menu = {"Beer", "Vodka", "Coctaile"};
    private int maxClients;
    private int bartenders;
    private int clientsCounter = 0;
    private BlockingQueue<String[]> orders = new ArrayBlockingQueue<>(10);

    public Bar(int maxClients, int bartenders) {
        if (maxClients > 0) {
            this.maxClients = maxClients;
        } else {
            throw new RuntimeException("Number of maxClients cannot be less than 0");
        }

        if (bartenders > 0) {
            this.bartenders = bartenders;
        } else {
            throw new RuntimeException("Number of bartenders cannot be less than 0");
        }
    }

    public String[] getMenu() {
        return menu;
    }

    public void decreaseClient() {
        clientsCounter--;
    }

    public void acceptClient() {
        if (maxClients > clientsCounter) {
            Client client = new Client(this);
            Thread thread = new Thread(client);
            client.setName(thread.getName());
            thread.start();
            clientsCounter++;
        } else {
            System.out.println("The bar is full");
        }
    }

    public void takeOrder(String[] order) throws InterruptedException {
        orders.put(order);
    }

}
