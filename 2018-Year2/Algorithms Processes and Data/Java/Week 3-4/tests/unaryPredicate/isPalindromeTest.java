package unaryPredicate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Domantas Giedraitis (student id: u1757704 )
 * @version 1
 * @since 2018-10-24
 */
class isPalindromeTest {

    private isPalindrome prediction = new isPalindrome();

    @Test
    void test_aaaa() {
        assertTrue(prediction.test("aaaa"));
    }
    @Test
    void test_aabb() {
        assertFalse(prediction.test("aabb"));
    }
    @Test
    void test_AbleWasIEreISawElba() {
        assertTrue(prediction.test("Able was I ere I saw Elba"));
    }
    @Test
    void test_aManaPlanaCanalPanama() {
        assertTrue(prediction.test("A man,a plan, a canal | panama!"));
    }
    @Test
    void test_GodsDog() {
        assertTrue(prediction.test("God's Dog"));
    }
}