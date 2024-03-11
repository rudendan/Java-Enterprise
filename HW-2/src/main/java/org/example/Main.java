package org.example;

public class Main {
    public static void main(String[] args)  {

        ValueCalculator calculator = new ValueCalculator(1);
        calculator.executeWithOneThread();
        calculator.executeWithSpecificNumberOfThreads(5);
    }
}