package unaryPredicate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Domantas Giedraitis (student id: u1757704 )
 * @version 1
 * @since 2018-10-24
 */
class isPrimeTest {

    private isPrime predicate = new isPrime();
    @Test
    void testInteger_1() {
        assertFalse(predicate.test(1));
    }
    @Test
    void testInteger_2() {
        assertTrue(predicate.test(2));
    }
    @Test
    void testInteger_4() {
        assertFalse(predicate.test(4));
    }
    @Test
    void testInteger_0() {
        assertFalse(predicate.test(0));
    }
    @Test
    void testInteger_IndexError() {
        assertFalse(predicate.test(-5));
    }
    @Test
    void testInteger_Prime97() {
        assertTrue(predicate.test(97));
    }
    @Test
    void testInteger_Prime199() {
        assertTrue(predicate.test(199));
    }
}