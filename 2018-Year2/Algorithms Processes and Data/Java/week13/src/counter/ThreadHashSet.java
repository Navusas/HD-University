package counter;

import java.util.HashSet;

/**
 * An implementation of thread sets.
 * A thread set is a set of Threads which provides a facility for running all the threads in the set concurrently.
 *
 * @author Hugh Osborne
 * @version January 2019
 */

public class ThreadHashSet<T extends Thread> extends HashSet<T> implements ThreadSet<T> {

    /**
     * Run all the threads in this thread set in parallel, and wait for them to finish.
     *
     * @throws InterruptedException if an interrupt occurs while witing for the trheads to finish.
     */
    @Override
    public void runSet() throws InterruptedException {

        for (Thread thread : this) { // start all the threads first
            thread.start();
        }
        for (Thread thread : this) { // make threads to wait until other completes execution
            thread.join();
        }
    }


}
