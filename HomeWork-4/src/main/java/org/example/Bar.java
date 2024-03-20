package org.example;

import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Bar {

    private final String[] menu = {
            "Coffee", "Tea", "Beer", "Wine", "Cocktail",
            "Mojito", "Margarita", "Daiquiri", "Martini",
            "Mojito", "Juice", "Lemonade", "Vodka", "Tequila",
            "Whiskey"
    };
    private final ExecutorService clientExecutor;
    private final ExecutorService bartenderExecutor;
    private BlockingQueue<Future<String[]>> orders = new LinkedBlockingQueue<>();
    private final int maxClients;
    private AtomicInteger clientsCounter = new AtomicInteger(0);

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

    public BlockingQueue<Future<String[]>> getOrders() {
        return orders;
    }

    public void acceptClient () {
        if (maxClients > clientsCounter.get()) {
            orders.add(clientExecutor.submit(new Client(this)));
            clientsCounter.incrementAndGet();
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
        clientsCounter.decrementAndGet();
    }
    public void shutdown() {
        clientExecutor.shutdown();
        bartenderExecutor.shutdown();
    }
}
