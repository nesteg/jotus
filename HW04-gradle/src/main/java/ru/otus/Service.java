package ru.otus;


import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Service {

    private final ArrayList<Integer> buffer = new ArrayList<>();
    private final int count = 50000;
    private final int interval = 25;

    void run() throws InterruptedException {
        int k = 0;
        while (true) {
            var collect = Stream
                    .generate(() -> (int)(Math.random() * 1000))
                    .limit(count * k)
                    .collect(Collectors.toList());
            buffer.addAll(collect);
            var sublist=buffer.subList(0,buffer.size()*3/4);
            sublist.clear();
            k++;
            System.out.println(k);
            Thread.sleep(interval);
        }

    }
}
