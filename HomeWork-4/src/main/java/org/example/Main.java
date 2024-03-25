package org.example;

public class Main {
    public static void main(String[] args) {

        Bar bar = new Bar(3, 15);
        try {
            for (int i = 0; i < 50; i++) {
                bar.acceptClient();
                Thread.sleep(30);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            bar.shutdown();
        }
    }
}