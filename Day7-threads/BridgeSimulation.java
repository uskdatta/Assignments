package MayBatchJava.Jun5A;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



class Bridge {
    private final Semaphore token = new Semaphore(1);
    private final Lock lock = new ReentrantLock();
    private final Condition cityACondition = lock.newCondition();
    private final Condition cityBCondition = lock.newCondition();

    private final Queue<Person> cityAQueue = new LinkedList<>();
    private final Queue<Person> cityBQueue = new LinkedList<>();

    private boolean cityBTurn = true;

    public void requestToCross(Person person) throws InterruptedException {
        lock.lock();
        try {

            if (person.city.equals("A")) {
                cityAQueue.offer(person);
            } else {
                cityBQueue.offer(person);
            }

            while (true) {
                boolean isMyTurn = person.city.equals("B") && cityBTurn && cityBQueue.peek() == person
                        || person.city.equals("A") && !cityBTurn && cityAQueue.peek() == person;

                if (isMyTurn && token.availablePermits() > 0) {
                    token.acquire();
                    break;
                } else {
                    if (person.city.equals("A")) {
                        cityACondition.await();
                    } else {
                        cityBCondition.await();
                    }
                }
            }


            if (person.city.equals("A")) {
                cityAQueue.poll();
            } else {
                cityBQueue.poll();
            }

        } finally {
            lock.unlock();
        }
    }

    public void crossBridge(Person person) throws InterruptedException {
        System.out.println(person.name + "from city" + person.city + "is crossing the bridge");
        Thread.sleep(1000);
        System.out.println(person.name + "has crossed to city" + (person.city.equals("A") ? "B" : "A"));
    }

    public void releaseBridge(Person person) {
        lock.lock();
        try {
            token.release();

            if (cityBTurn && cityAQueue.size() > 0) {
                cityBTurn = false;
            } else if (!cityBTurn && cityBQueue.size() > 0) {
                cityBTurn = true;
            }

            if (cityBTurn) {
                cityBCondition.signalAll();
            } else {
                cityACondition.signalAll();
            }

        } finally {
            lock.unlock();
        }
    }
}

class Person implements Runnable {
    public final String name;
    public final String city;
    private final Bridge bridge;

    public Person(String name, String city, Bridge bridge) {
        this.name = name;
        this.city = city;
        this.bridge = bridge;
    }

    @Override
    public void run() {
        try {
            bridge.requestToCross(this);
            bridge.crossBridge(this);
            bridge.releaseBridge(this);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class BridgeSimulation {
    public static void main(String[] args) {
        Bridge bridge = new Bridge();

        Thread[] people = new Thread[]{
                new Thread(new Person("P1", "B", bridge)),
                new Thread(new Person("P2", "B", bridge)),
                new Thread(new Person("P3", "A", bridge)),
                new Thread(new Person("P4", "A", bridge)),
                new Thread(new Person("P5", "B", bridge)),
                new Thread(new Person("P6", "A", bridge))
        };

        for (Thread person : people) {
            person.start();
        }
    }
}
