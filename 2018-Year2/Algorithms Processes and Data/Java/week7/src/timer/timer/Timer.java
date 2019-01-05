package timer.timer;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.Duration;

/**
 * We are interested in how long our implemented methods take to execute. This
 * interface defines a time method that can be used to time a given method in
 * implementing classes.  This is known as the <tt>timed method</tt>.
 *
 * Each timer.Timer object is expected to have some <tt>int</tt> measure of the "size" of
 * the task the timed method has to undertake.
 *
 * @author Hugh Osborne
 * @version September 2018
 *
 */
public interface Timer {


    /**
     * Nominate the timed method.  In an implementing class <tt>timedMethod</tt> will
     * be a wrapper method for the method to be timed.
     */
    public void timedMethod();

    /**
     * Run the timed method belonging to an implementing class, and time how
     * long it takes.
     *
     * @return the time taken to execute the method being timed, in nanoseconds
     */
    default Duration time() {
        long startTime = System.nanoTime();
        timedMethod();
        long endTime = System.nanoTime();
        return Duration.ofNanos(endTime - startTime);
    }
}
