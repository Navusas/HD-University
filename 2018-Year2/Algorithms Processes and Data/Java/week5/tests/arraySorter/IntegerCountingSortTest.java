package arraySorter;

import arrayGenerator.ArrayGenerator;
import arrayGenerator.IntegerArrayGenerator;
import scope.IntegerScope;

/**
 * @author Domantas Giedraitis (student id: u1757704 )
 * @version 1
 * @since 2018-11-11
 */

public class IntegerCountingSortTest extends CountingSortTest<Integer> {
    @Override
    ArrayGenerator<Integer> getGenerator() {
        return new IntegerArrayGenerator(new IntegerScope());
    }
}
