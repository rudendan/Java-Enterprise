package org.example;

public class Main {
    public static void main(String[] args)  {

        ValueCalculator calculator = new ValueCalculator(1);
        calculator.executeWithOneThread();
        for (int i = 2; i < 10; i++) {
            calculator.executeWithSpecificNumberOfThreads(i);
        }

    }
}