/*
 * This Java source file was demonstration two thread with sequence number.
 */
package ru.otus;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;


/**
 * To start the application:
 * ./gradlew build
 * java -jar ./HW15-Sequence/build/libs/Otus-0.1.jar
 * <p>
 * To build:
 * ./gradlew build
 */
public class Sequence {

    private String message = "t2";
    private int[] sequence;

    private synchronized void sequence(String threadName) {
        int snake = 0;
        for (var i : sequence) {
            try {
                while (message.equals(threadName)) {
                    this.wait();
                }
            } catch (InterruptedException e) {
                Thread.interrupted();
            }
            message = threadName;
            if (snake++ % 2 == 0) {
                System.out.format("%s : %d\n", threadName, i);
            } else {
                System.out.format("%s :  %d\n", threadName, i);
            }
            this.notifyAll();
        }
    }

    private void init() {
        IntSupplier generator = new IntSupplier() {
            int current = 1;
            Boolean half = false;

            public int getAsInt() {
                if (!half) {
                    if (current == 9) {
                        half = true;
                    }
                    return current++;
                } else {
                    return current--;
                }
            }
        };
        sequence = IntStream.generate(generator).takeWhile(i -> i > 0).toArray();
    }

    private void start() {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Future<?> futureOne = executor.submit(() -> sequence("t1"));
        Future<?> futureTwo = executor.submit(() -> sequence("t2"));
        try {
            futureOne.get();
            futureTwo.get();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());
        } finally {
            executor.shutdown();
        }
    }

    public static void main(String... args) {
        var seq = new Sequence();
        seq.init();
        seq.start();
    }
}
