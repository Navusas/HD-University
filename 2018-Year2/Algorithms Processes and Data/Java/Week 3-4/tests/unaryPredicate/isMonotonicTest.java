package unaryPredicate;

import Exceptions.IndexingError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Domantas Giedraitis (student id: u1757704 )
 * @version 1
 * @since 2018-10-29
 */
class isMonotonicTest {

    private Integer[] numbers;
    private isMonotonic predicate;
    @BeforeEach
    void setUp() {
        this.predicate = new isMonotonic();
    }

    @Test
    void testIncreasing_Size10_Integer() throws IndexingError {
        this.numbers = new Integer[] {0,1,2,3,4,5,6,7,8,9};
        assertTrue(predicate.test(this.numbers));
    }
    @Test
    void testDecreasing_Size10_Integer() throws IndexingError {
        this.numbers = new Integer[] {9,8,7,6,5,4,3,2,1,0};
        assertTrue(predicate.test(this.numbers));
    }
    @Test
    void testEquals_Size10_Integer() throws IndexingError {
        this.numbers = new Integer[] {4,4,4,4,4,4,4,4,4,4};
        assertTrue(predicate.test(this.numbers));
    }
    @Test
    void testEquals_String() throws IndexingError {
        assertTrue(predicate.test(new String[] {"Hello", "Hello", "Hello", "Hello", "Hello", "Hello", "Hello", "Hello"}));
    }
    @Test
    void testIncreasing_String() throws IndexingError {
        assertTrue(predicate.test(new String[] {"A","B","C","D","E"}));
    }
    @Test
    void testRandom_String() throws IndexingError {
        assertFalse(predicate.test(new String[] {"KAMSD","jansd","my name is","Domantas","created by","u1757704"}));
    }
    @Test
    void testEmptyArray_Integer(){
        assertThrows(IndexingError.class, () -> {
            predicate.test(new Integer[1]);
        });
    }
    @Test
    void testMixed_small_Integer() throws IndexingError {
        this.numbers = new Integer[] {5,-5,8,9,2,4,5,6,1,-15,-82,-65,-5,16};
        assertFalse(predicate.test(this.numbers));
    }
    @Test
    void testIncreasing_Double() throws IndexingError {
        Double[] array = {5.4,6.2,7.4,8.6,9.8};
        assertTrue(predicate.test(array));
    }
    @Test
    void testRandom_Double() throws IndexingError {
        Double[] array = {5.4,-6.2,7.4,8.6,-9.8};
        assertFalse(predicate.test(array));
    }
}