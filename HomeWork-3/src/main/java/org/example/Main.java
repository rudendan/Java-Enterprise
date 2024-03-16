package org.example;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Bar bar = new Bar(10, 2);
        for (int i = 0; i < 30; i++) {
            bar.acceptClient();
            Thread.sleep(100);
        }
    }
}