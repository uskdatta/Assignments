package MayBatchJava.Jun3A;


import java.util.*;
import java.util.concurrent.*;

class Bridge {
    private Semaphore bridge=new Semaphore(1);
    private Semaphore token=new Semaphore(0);
    private Queue<Person> cityAQueue=new LinkedList<>();
    private Queue<Person> cityBQueue=new LinkedList<>();
    private volatile boolean cityBTurn=true;

    public void requestCross(Person person) throws InterruptedException {
        synchronized (this) {
            if (person.city.equals("A")) cityAQueue.add(person);
            else cityBQueue.add(person);
        }

        while(true) {
            synchronized(this) {
                if(person.city.equals("B") && cityBTurn && person.equals(cityBQueue.peek())) break;
                if(person.city.equals("A") && !cityBTurn && person.equals(cityAQueue.peek())) break;
            }
            Thread.sleep(50);
        }



        token.acquire();
        bridge.acquire();
        synchronized(this) {
            if(person.city.equals("A")) cityAQueue.poll();
            else cityBQueue.poll();
        }

        System.out.println(person.name+" from City "+person.city+" is crossing the bridge");
        Thread.sleep(1000); 
        System.out.println(person.name+" has crossed to City "+(person.city.equals("A")?"B": "A"));

        bridge.release();

        synchronized (this) {
            if (person.city.equals("B") && cityBQueue.isEmpty()) cityBTurn=false;
            if (person.city.equals("A") && cityAQueue.isEmpty()) cityBTurn=true;
        }

        token.release();
    }

    public void initializeTokenInCityB() {
        token.release();
    }
}

class Person extends Thread {
    String name;
    String city;
    Bridge bridge;

    public Person(String name,String city,Bridge bridge) {
        this.name=name;
        this.city=city;
        this.bridge=bridge;
    }

    public void run() {
        try {
            bridge.requestCross(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


public class BridgeManagement {
    public static void main(String[] args) {
        Bridge bridge=new Bridge();
        bridge.initializeTokenInCityB();

        List<Person> people=new ArrayList<>();
        people.add(new Person("P1","B",bridge));
        people.add(new Person("P2","B",bridge));
        people.add(new Person("P3","A",bridge));
        people.add(new Person("P4","A",bridge));
        people.add(new Person("P5","B",bridge));
        people.add(new Person("P6","A",bridge));

        for (Person p : people) {
            p.start();
        }
    }
}
