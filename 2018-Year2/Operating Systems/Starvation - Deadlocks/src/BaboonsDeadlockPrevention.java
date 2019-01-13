import java.util.concurrent.Semaphore;
import java.util.Random;

/**
 * @author Domantas Giedraitis (student id: u1757704 )
 * @version 1
 * @since 2018-12-07
 */

public class BaboonsDeadlockPrevention implements Runnable {

    private static Random rand = new Random();

    /*Initialize variables*/
    private String direction;
    private int sleepTime;
    private Semaphore rope;

    /**
     * @param direction represents location (east/west)
     * @param sem       semaphore (rope) to be used
     * @param sleepTime amount of time to be used. Used in other to test functionality and make threads
     *                  start at the same time. 0 for random, otherwise integer represents milliseconds
     */
    public BaboonsDeadlockPrevention(String direction, Semaphore sem, int sleepTime) {
        this.direction = direction;
        this.sleepTime = sleepTime;
        this.rope = sem;
    }
    @Override
    public void run() {
        sleep(sleepTime);   // sleep for requested/random amount of time
        climbTheRope();     // try to climb the rope
    }

    /**
     * Prevents threads using the same samephore from deadlock.
     */
    private void climbTheRope() {
        System.out.println(this + " wants to cross to other side");
        while(true) {
            if(!rope.tryAcquire()) {    // if try to acquire failed - other baboon is using the resource
                System.out.println(this + " Other baboon is climbing the rope");
                acting("waiting for other baboon");         // wait for random amount of time
            }
            else {          // resource is free to use
                System.out.println(this + " Rope is free to use");
                acting("climbing");         // climb the rope (sleep for random amount of time
                break;                             // exit the infinite while loop
            }
        }
        acting("thinking");                 // sleep for random amount of time
        System.out.println(this + " successfully climbed to other side");
        rope.release();                            // release the resource needed for other thread
    }

    /**
     * Makes thread to sleep for a certain amount of time
     * @param sleepTime any non-negative integer - time in milliseconds required to sleep
     *                  0 - random (0-9) seconds
     */
    private void sleep(int sleepTime) {
        if(sleepTime == 0)
            sleepTime = rand.nextInt(10) *1000;
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sleeps thread for random amount of time (0-9) seconds
     * @param action    descrioption of action taken
     */
    private void acting(String action) {
        try {
            System.out.println(this + " is " + action);
            Thread.sleep(rand.nextInt(10) * 1000);
        } catch (InterruptedException e) {
            threadIsDead();
        }
    }

    /**
     * used when InterruptedException occurs to exit the system
     */
    private void threadIsDead() {
        System.out.println("Thread is dead...");
        System.exit(0);
    }

    /**
     * @return human-manner string specifying thread
     */
    public String toString() {
        return "[" + this.direction + " Baboon]";
    }

    public static void main(String[] args) {
        System.out.println("Start");

        Semaphore sem = new Semaphore(1); // resource to be used by both baboons

        /*threads representing baboons, sharing the same memory
        * 0 - random time. It can be changed iwth any desired number in milliseconds. ( for sleeping)
        * */
        Thread eastBaboon = new Thread(new BaboonsDeadlockPrevention("East",sem,0));
        Thread westBaboon = new Thread(new BaboonsDeadlockPrevention("West",sem, 0));

        // Start the threads
        eastBaboon.start();
        westBaboon.start();

        // join after completion
        try {
            eastBaboon.join();
            westBaboon.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
