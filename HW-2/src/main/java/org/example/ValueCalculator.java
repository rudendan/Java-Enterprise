package org.example;
import java.util.Arrays;

public class ValueCalculator {

    private float[] arr;
    private int arrSize;
    private int halfArrSize;

    public ValueCalculator(int arrSize) {
        this.arrSize = Math.max(arrSize, 1_000_000);
        halfArrSize = this.arrSize / 2;
        arr = new float[this.arrSize];
    }

    public void executeWithOneThread() {
        long start = System.currentTimeMillis();
        Arrays.fill(arr, 1.0f);

        float[] arrCopy1 = Arrays.copyOfRange(arr, 0, halfArrSize);
        float[] arrCopy2 = Arrays.copyOfRange(arr, halfArrSize, arrSize);

        changeValues(arrCopy1);
        changeValues(arrCopy2);

        System.arraycopy(arr, 0, arrCopy1, 0, halfArrSize);
        System.arraycopy(arr, halfArrSize, arrCopy2, 0, halfArrSize);

        shuffleArray();
        long end = System.currentTimeMillis();
        System.out.println("Calculate with in 1 thread was executed in " + (end - start) + " ms. Max value is " + findMaxValue());
    }

    public void executeWithSpecificNumberOfThreads(int numThreads) {

        long start = System.currentTimeMillis();
        Arrays.fill(arr, 1.0f);
        Thread[] threads = new Thread[numThreads];
        int segment = arrSize / numThreads;
        for (int i = 0; i < numThreads; i++) {
            int startIndex = i * segment;
            int endIndex = (i == numThreads - 1) ? arrSize : (i + 1) * halfArrSize;
            float[] subArray = Arrays.copyOfRange(arr, startIndex, endIndex);
            threads[i] = new Thread(() -> changeValues(subArray));
            threads[i].start();
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        shuffleArray();
        long end = System.currentTimeMillis();
        System.out.println("Calculate with " + numThreads + " executed in " + (end-start) + " ms. Max value is " + findMaxValue());
    }

    private void changeValues(float[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (float) (array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5)
                    * Math.cos(0.4f + i / 2));
        }
    }

    private void shuffleArray() {
        for (int i = arrSize - 1; i > 0; i--) {
            int index = (int) (Math.random() * (i + 1));
            float temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }
    }

    public float findMaxValue() {
        float max = Float.MIN_VALUE;
        for (float num : arr) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }
}
