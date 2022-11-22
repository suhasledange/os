import java.util.concurrent.Semaphore;
public class mutex {

    static Semaphore readlock = new Semaphore(1);
    static Semaphore writelock = new Semaphore(1);
    static int readCount = 0;

    static class Read implements Runnable{
        public void run(){

            try {
                readlock.acquire();
                readCount++;
                if(readCount == 1){
                    writelock.acquire();
                }
                readlock.release();

                System.out.println(Thread.currentThread().getName()+" is Reading");
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+" has Finished");

                readlock.acquire();
                readCount--;

                if(readCount==0){
                    writelock.release();
                }
                readlock.release();

            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    static class Write implements Runnable{

        @Override
        public void run() {
            try{
                writelock.acquire();
                System.out.println(Thread.currentThread().getName()+" is Writing");
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+" has Finished");
                writelock.release();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Read r = new Read();
        Write w = new Write();
        Thread t1 = new Thread(r);
        t1.setName("Thread 1");
        Thread t2 = new Thread(r);
        t1.setName("Thread 2");
        Thread t3 = new Thread(w);
        t1.setName("Thread 3");
        Thread t4 = new Thread(r);
        t1.setName("Thread 4");

        t1.start();
        t3.start();
        t2.start();
        t4.start();

    }
}
