import java.util.Random;
import java.util.concurrent.Semaphore;


/**
 * Global variables used to avoid and track starvation/ deadlocks
 */
class lastUsed {
    static String lastUsedRope = "";
    static String possibleLocation = "";
}

class Baboon extends Thread {

    private static Random rand = new Random();

    int id;
    String direction;
    Semaphore rope;
    int sleepTime;

    /**
     * @param id baboon id in number
     * @param direction can be 'east' or 'west'
     * @param rope  'the sempahore (resource) used by baboon
     * @param sleepTime ' after how many milliseconds thread will start
     */
    Baboon(int id, String direction, Semaphore rope, int sleepTime) {
        super(direction);
        this.id = id;
        this.direction = direction;
        this.rope = rope;
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        sleep(sleepTime);   // sleep for requested/random amount of time
        climbTheRope();     // try to climb the rope
    }

    /**
     * Prevents threads using the same samephore from deadlock and starvation
     */
    private void climbTheRope() {
        System.out.println(this + " wants to cross to other side");
        while (true) {
            if (this.direction.equals(lastUsed.lastUsedRope) && lastUsed.lastUsedRope != null &&
                    lastUsed.possibleLocation.equals(this.direction)) { // if the same 'way' baboon is on bridge
                System.out.println(this + " there is baboon on rope but he is heading the same way as I");
                try {
                    rope.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lastUsed.lastUsedRope = this.direction;
                acting("climbing");         // climb the rope (sleep for random amount of time
                break;                             // exit the infinite while loop
            } else {
                if (!rope.tryAcquire()) {    // if try to acquire failed - other baboon is using the resource
                    System.out.println(this + " no space on rope");
                    rope.release();
                    acting("waiting for other baboons");         // wait for random amount of time
                } else {
                    if (rope.availablePermits() < BaboonsStarvation.MAX_PERMITS-1) {
                        lastUsed.possibleLocation = this.direction;
                        rope.release();
                        acting(" waiting to all " + lastUsed.lastUsedRope + " to cross");
                    } else {
                        System.out.println(this + " rope is free");
                        lastUsed.lastUsedRope = this.direction;
                        acting("climbing");         // climb the rope (sleep for random amount of time
                        break;                             // exit the infinite while loop
                    }
                }
            }
        }
        acting("thinking");                 // sleep for random amount of time
        System.out.println(this + " successfully climbed to other side");
        rope.release();                            // release the resource needed for other thread
    }

    /**
     * Makes thread to sleep for a certain amount of time
     *
     * @param sleepTime any non-negative integer - time in milliseconds required to sleep
     *                  0 - random (0-9) seconds
     */
    private void sleep(int sleepTime) {
        if (sleepTime == 0)
            sleepTime = rand.nextInt(10) * 1000;
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sleeps thread for random amount of time (0-9) seconds
     *
     * @param action descrioption of action taken
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
        return "[" + this.direction + " Baboon " + this.id + "]";
    }
}