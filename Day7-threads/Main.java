package MayBatchJava.Jun5A;




class OddEvenPrinter {
    private final int n;
    private int number=1;
    private boolean flg_odd=true;

    public OddEvenPrinter(int n){
        this.n=n;
    }


    public void pOdd() throws InterruptedException {
        while(number<=n){
            synchronized (this){
                while(!flg_odd){
                    wait();
                }
                if (number<=n){
                    System.out.println("odd number:"+number);
                    number++;
                    flg_odd=false;
                    notify();
                }
            }
        }
    }

    public void pEven() throws InterruptedException{
        while(number<=n){
            synchronized (this){
                while(flg_odd){
                    wait();
                }
                if(number<=n){
                    System.out.println("even number:"+number);
                    number++;
                    flg_odd=true;
                    notify();
                }
            }
        }
    }
}

class OddThread implements Runnable{
    private final OddEvenPrinter printer;
    public OddThread(OddEvenPrinter printer){
        this.printer=printer;
    }
    @Override
    public void run(){
        try{
            printer.pOdd();
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
class EvenThread implements Runnable{
    private final OddEvenPrinter printer;
    public EvenThread(OddEvenPrinter printer){
        this.printer=printer;
    }
    @Override
    public void run(){
        try{
            printer.pEven();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
public class Main{
    public static void main(String[] args) {
        int maxx=10;
        OddEvenPrinter printer=new OddEvenPrinter(maxx);

        Thread o_thread= new Thread(new OddThread(printer));
        Thread e_thread=new Thread(new EvenThread(printer));

        o_thread.start();
        e_thread.start();
    }
}


