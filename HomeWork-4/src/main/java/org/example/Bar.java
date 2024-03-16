package org.example;

import java.util.Queue;
import java.util.concurrent.*;

public class Bar {

    private final String[] menu = {
            "Coffee", "Tea", "Beer", "Wine", "Cocktail",
            "Mojito", "Margarita", "Daiquiri", "Martini",
            "Mojito", "Juice", "Lemonade", "Vodka", "Tequila",
            "Whiskey"
    };
    private final ExecutorService clientExecutor;
    private final ExecutorService bartenderExecutor;
    private Queue<Future<String[]>> orders = new LinkedBlockingQueue<>();
    private final int maxClients;
    private int clientsCounter = 0;

    public Bar(int numOfBartenders, int maxClients) {
        this.maxClients = maxClients;
        this.clientExecutor = Executors.newFixedThreadPool(this.maxClients);
        bartenderExecutor = Executors.newFixedThreadPool(numOfBartenders);
        for (int i = 0; i < numOfBartenders; i++) {
            bartenderExecutor.submit(new Bartender(this, "Bartender " + (i + 1)));
        }
    }

    public String[] getMenu() {
        return menu;
    }

    public Queue<Future<String[]>> getOrders() {
        return orders;
    }

    public void acceptClient () {
        if (maxClients > clientsCounter) {
            orders.add(clientExecutor.submit(new Client(this)));
            clientsCounter++;
        } else {
            System.out.println("The bar is full. Please try again later...");
        }
    }

    public void serveOrder(String clientName)  {
        System.out.println("Order was served to " + clientName);
        leaveBar(clientName);
    }

    public void leaveBar(String clientName) {
        System.out.println(clientName + " leave bar");
        clientsCounter--;
    }
    public void shutdown() {
        clientExecutor.shutdown();
        bartenderExecutor.shutdown();
    }
}
