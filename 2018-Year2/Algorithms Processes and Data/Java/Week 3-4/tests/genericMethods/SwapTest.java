package genericMethods;

import Exceptions.IndexingError;
import org.junit.jupiter.api.Test;
import otherObjects.Robot;
import static org.junit.jupiter.api.Assertions.*;

/**
 *  Tests for Swap class methods
 *
 * @author          Domantas Giedraitis (student id: u1757704 )
 * @version         1
 * @since           2018-10-24
 */

class SwapTest extends SwapTestHandler {

    @Test
    void testIntegerSize4() throws IndexingError {
        Integer[] array = {1,2,3,4};
        Integer[] original = {2,1,3,4};
        assertArrayEquals(Swap.swapPosition(array,0,1),original);
    }
    @Test
    void testIntegerWithAbstractClass_Size4() throws IndexingError {
        Integer[] array = {1,2,3,4};
        testArray(array,0,1);
    }
    @Test
    void testString_Size4() throws IndexingError {
        String[] array = {"Hi","Hola","Blablabla","Chichi"};
        testArray(array,1,3);
    }
    @Test
    void testString_Size10() throws IndexingError {
        String[] array = {"Hi","Hola","Blablabla","Chichi","Bye","GoodBye","Hello","Me","Random","String"};
        testArray(array,1,3);
    }
    @Test
    void testString_Size10_2() throws IndexingError {
        String[] array = {"Hi","Hola","Blablabla","Chichi","Bye","GoodBye","Hello","Me","Random","String"};
        testArray(array,0,9);
    }

    @Test
    void testInteger_Size5() throws IndexingError {
        Integer[] array = {1,2,3,4};
        testArray(array,0,2);
    }
    @Test
    void testInteger_Size500() throws IndexingError {
        Integer[] array = generateInt(500);
        testArray(array,249,499);
    }
    @Test
    void testInteger_Size5000() throws IndexingError {
        Integer[] array = generateInt(5000);
        testArray(array,0,4999);
    }

    @Test
    void testInteger_Size5000_OldStyle() throws IndexingError {
        Integer[] array = generateInt(5000);
        Integer[] original = array.clone();
        original[0] = 4999;
        original[4999] = 0;
        assertArrayEquals(Swap.swapPosition(array,0,4999),original);
    }

    @Test
    void testRobotObjectSize50000() throws IndexingError {
        testArray(Robot.createRobotArmy(50000),0,15);
    }
    @Test
    void testRobotObjectSize1000000() throws IndexingError {
        testArray(Robot.createRobotArmy(1000000),0,15);
    }

    @Test
    void testRobotObject_OutOfBounds() {
        assertThrows(IndexingError.class,() ->
        {
            testArray(Robot.createRobotArmy(50),500,-500);
        });
    }

    @Test
    void testString_IndexOutOfBound(){
        String[] array = {"Hi","Hola","Blablabla","Chichi"};
        try{
            testArray(array,-5,0);
            fail("Indexing out of bounds");
        }
        catch (IndexingError indexingError) {
            indexingError.printStackTrace();
        }
    }
}