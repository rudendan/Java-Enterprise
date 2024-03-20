package org.example;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Bar {

    private final String[] menu = {
            "Coffee", "Tea", "Beer", "Wine", "Cocktail",
            "Mojito", "Margarita", "Daiquiri", "Martini",
            "Mojito", "Juice", "Lemonade", "Vodka", "Tequila",
            "Whiskey"
    };
    private final int maxClients;
    private int clientsCounter = 0;
    private BlockingQueue<String[]> orders = new ArrayBlockingQueue<>(10);

    public Bar(int maxClients, int numOfBartenders) {
        if (maxClients > 0) {
            this.maxClients = maxClients;
        } else {
            throw new RuntimeException("Number of maxClients cannot be less than 0");
        }

        if (numOfBartenders > 0) {
            for (int i = 0; i < numOfBartenders; i++) {
                new Thread(new Bartender(this, "Bartender " + (i + 1))).start();
            }
        } else {
            throw new RuntimeException("Number of bartenders cannot be less than 0");
        }
    }

    public String[] getMenu() {
        return menu;
    }

    public BlockingQueue<String[]> getOrders() {
        return orders;
    }

    public void acceptClient() {
        if (maxClients > clientsCounter) {
            new Thread(new Client(this)).start();
            clientsCounter++;
        } else {
            System.out.println("The bar is full. Please try again later...");
        }
    }

    public void serveOrder(String clientName) throws InterruptedException {
        System.out.println("Order was served to client" + clientName);
        Thread.sleep(100);
        leaveBar(clientName);
    }

    public void leaveBar(String clientName) {
        System.out.println("Client " + clientName + " leave bar");
        clientsCounter--;
    }
}
