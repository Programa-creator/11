package org.example;
import java.util.concurrent.Semaphore;

public class PetrolStation {

    private int amount;
    private Semaphore semaphore = new Semaphore(3);
    public PetrolStation(int amount) {
        this.amount = amount;
    }
        public void main(String[] args) throws InterruptedException {
            PetrolStation petrolStation = new PetrolStation(50);
            ThreadSafeList thread = new ThreadSafeList(5, petrolStation);

        }
    public void doRefuel(int requestedAmount) throws InterruptedException {
        semaphore.acquire(); // отримати дозвіл на обслуговування
        System.out.println(Thread.currentThread().getName() + " is refuelling...");
        Thread.sleep((long) (Math.random() * 8000 + 3000)); // заправка триває від 3-10 секунд
        synchronized (this) {
            if (requestedAmount <= amount) {
                amount -= requestedAmount;
                System.out.println(Thread.currentThread().getName() + " has refuelled successfully. Remaining amount: " + amount);
            } else {
                System.out.println(Thread.currentThread().getName() + " cannot refuel. Insufficient amount of fuel.");
            }
        }
        semaphore.release(); // звільнити дозвіл на обслуговування
    }
}