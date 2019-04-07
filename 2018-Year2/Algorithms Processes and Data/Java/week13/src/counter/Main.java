package counter;

/**
 * A demonstration of the use and behaviour of Counters.
 * The main method in this class populates a thread set with two counters, and then uses the thread set's runSet
 * method to run the counters concurrently.
 *
 * @author Hugh Osborne
 * @version January 2019
 */

public class Main {
    /**
     * Demonstrate the behaviour of counters.
     *
     * @param args not used
     * @throws CounterException     should not occur
     * @throws InterruptedException should not occur
     */
    public static void main(String[] args) throws CounterException, InterruptedException {
        ThreadSet<Counter> counters = new ThreadHashSet<>();  // to contain the counters
        counters.add(new Counter("up", 0, 10)); // counter "up" counting from 0 to 10
        counters.add(new Counter("down", 10, 0)); // counter "down" counting from 10 to 0
        Counter.traceOn(); // switch tracing on for console output
        Counter.setDelay(0.1); // set delay to a 0.1 seconds (to be delayed between accessing shared variable)
        counters.runSet(); // run the counters (concurrently)
    }
}
