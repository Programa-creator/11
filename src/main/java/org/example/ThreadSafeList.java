package org.example;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ThreadSafeList {

    private List<Thread> list = Collections.synchronizedList(new ArrayList<Thread>());
    ThreadSafeList(int amount, PetrolStation petrolStation) throws InterruptedException {
        for (int i = 0; i < amount; i++) {
            list.add( new Thread(() -> {
                try {
                    petrolStation.doRefuel((int) (Math.random() * 20 + 5));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }));
        }

        for (Thread thread : list) {
            thread.start();
        }

        for (Thread thread : list) {
            thread.join();
        }
    }
    public void add(Thread item) {
        synchronized (list) {
            list.add(item);
        }
    }

    public void remove(Thread item) {
        synchronized (list) {
            list.remove(item);
        }
    }

    public Thread get(int index) {
        synchronized (list) {
            return list.get(index);
        }
    }
}