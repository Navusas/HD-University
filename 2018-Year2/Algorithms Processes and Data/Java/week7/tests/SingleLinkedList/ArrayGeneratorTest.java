package SingleLinkedList;

import SingleLinkedList.generator.ArrayGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Provides some basic tests for array generators:
 * <ul>
 *     <li> Do the generators generate arrays of the correct size?</li>
 *     <li> Do the generators throw the expected negative array size exception if asked to generate
 *          an array of negative size?</li>
 * </ul>
 */
 abstract class ArrayGeneratorTest<T> {

    public abstract ArrayGenerator<T> getGenerator();

    /**
     * Test that a generator does produce an array of the required length.
     *
     * @param size the length of the array that should be generated
     */
    private void testSize(int size) {
        ArrayGenerator generator = getGenerator();
        assertEquals(size,generator.getArray(size).length);
    }

    @Test
    void testArray_sizeZero() {
        testSize(0);
    }

    @Test
    void testArray_sizeLarge() {
        testSize(3628751);
    }

    @Test
    void testArray_sizeMinuesOne() {
        assertThrows(NegativeArraySizeException.class, ()->testSize(-1));
    }

    @Test
    void testArray_sizeMinusFiftyFive() {
        assertThrows(NegativeArraySizeException.class, ()->testSize(-58));
    }
}