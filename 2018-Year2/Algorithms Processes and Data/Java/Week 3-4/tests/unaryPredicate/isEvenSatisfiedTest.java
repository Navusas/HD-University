package unaryPredicate;

import Exceptions.IndexingError;
import org.junit.jupiter.api.Test;
import otherObjects.ArrayGenerator;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Domantas Giedraitis (student id: u1757704 )
 * @version 1
 * @since 2018-10-29
 */
class isEvenSatisfiedTest {

    private IsEven predicate = new IsEven();
    private ArrayGenerator generator = new ArrayGenerator();

    @Test
    void even_0To10() throws IndexingError {
        assertEquals(predicate.numberSatisfying(generator.generateEven(10)),10);
    }
    @Test
    void odd_0To10() throws IndexingError {
        assertEquals(predicate.numberSatisfying(generator.generateOdd(10)),0);
    }
    @Test
    void even_0To100000() throws IndexingError {
        assertEquals(predicate.numberSatisfying(generator.generateEven(100000)),100000);
    }
    @Test
    void odd_0To100000() throws IndexingError {
        assertEquals(predicate.numberSatisfying(generator.generateOdd(100000)),0);
    }
    @Test
    void smallIncrementArray() throws IndexingError {
        assertEquals(predicate.numberSatisfying(generator.generateInt(100)),50);
    }
    @Test
    void mediumncrementArray() throws IndexingError {
        assertEquals(predicate.numberSatisfying(generator.generateInt(50000)),25000);
    }
    @Test
    void largeIncrementArray() throws IndexingError {
        assertEquals(predicate.numberSatisfying(generator.generateInt(10000000)),5000000);
    }
}