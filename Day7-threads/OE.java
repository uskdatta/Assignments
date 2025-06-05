package MayBatchJava.Jun3A;
public class OE {

    public static void main(String[] args) {
//        static int max=10;

        int max =7;
        OddEvenPrinter printer=new OddEvenPrinter(max);

        Thread oddThread= new OddThread(printer);
        Thread evenThread=new EvenThread(printer);



        oddThread.start();
        evenThread.start();
    }
}



class OddEvenPrinter {
    private final int max;
    private int number=1;
    private final Object lock=new Object();

    public OddEvenPrinter(int max) {
        this.max=max;
    }

    public void printOdd() {
        while (number<= max) {
            synchronized (lock) {
                if (number %2== 1) {
                    System.out.println("odd: "+number);
                    number++;
                    lock.notify();
                } else {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }

    public void printEven() {
        while (number<= max) {
            synchronized (lock) {
                if (number % 2== 0) {
                    System.out.println("even: "+ number);
                    number++;
                    lock.notify();
                } else {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }
}


class OddThread extends Thread {
    private final OddEvenPrinter printer;

    public OddThread(OddEvenPrinter printer) {
        this.printer=printer;
    }

    public void run() {
        printer.printOdd();
    }
}


class EvenThread extends Thread {
    private final OddEvenPrinter printer;

    public EvenThread(OddEvenPrinter printer) {
        this.printer=printer;
    }

    public void run() {
        printer.printEven();
    }
}


