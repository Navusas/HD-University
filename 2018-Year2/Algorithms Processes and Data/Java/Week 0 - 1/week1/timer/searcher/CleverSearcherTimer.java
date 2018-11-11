package searcher;

import arrayGenerator.ArrayGenerator;
import arrayGenerator.CleverRandomListingGenerator;
import timer.Timer;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A timer implementation for CleveRSearcher that times the findElement method
 *
 * @author Domantas Giedraitis (student id: u1757704 )
 * @version 1
 * @since 2018-10-08
 */

public class CleverSearcherTimer extends CleverSearcher implements Timer {

    // All timings will be done with an index of 5
    private final static int K = 5;

    CleverSearcherTimer(int[] array) {
        super(array, K);
    }

    @Override
    public void timedMethod() {
        try {
            findElement();
        } catch (IndexingError indexingError) {
            // simply ignore indexing errors here
            // with K at 5, and a minimum task size (array size) of 10, indexing errors should not occur
            // duirng timing
        }
    }

    @Override
    public long getMaximumRuntime() {
        return 1;
    }

    /**
     * Minimum task size (array size) is set to ten, to avoid indexing errors (index is always five)
     * @return minimum task size of ten
     */
    @Override
    public int getMinimumTaskSize() {
        return 10;
    }

    /**
     * Setting maximum task (array) size
     * @return maximum task size
     */
    @Override
    public int getMaximumTaskSize() {
        return 100000000;
    }

    @Override
    public Timer getTimer(int size) {
        ArrayGenerator generator = new CleverRandomListingGenerator(size);
        return new CleverSearcherTimer(generator.getArray());
    }

    public static void main(String[] args) {
        CleverSearcherTimer timer = new CleverSearcherTimer(null);
        timer.timingSequence();
    }
}
