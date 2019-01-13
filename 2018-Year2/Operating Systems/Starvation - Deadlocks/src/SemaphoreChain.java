import java.util.concurrent.Semaphore;

public class SemaphoreChain implements Runnable {
    private String text;
    private Semaphore me;
    private Semaphore next;

    public SemaphoreChain(String text, Semaphore me, Semaphore next) {
        this.text = text;
        this.me = me;
        this.next = next;
    }

    public void run() {
        for(int i = 0; i < 5; i++) {
            try {
                me.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print(text);
            next.release();
        }
    }


    public static void main(String[] args) throws Exception {
        Semaphore s1 = new Semaphore(1);
        Semaphore s2 = new Semaphore(0);
        Semaphore s3 = new Semaphore(0);

        Thread t1 = new Thread(new SemaphoreChain("A", s1, s2));
        Thread t2 = new Thread(new SemaphoreChain("B", s2, s3));
        Thread t3 = new Thread(new SemaphoreChain("C", s3, s1));

        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println("\nDone!");
    }
}