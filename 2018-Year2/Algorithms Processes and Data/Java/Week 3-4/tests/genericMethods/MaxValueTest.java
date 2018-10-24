package genericMethods;

import Exceptions.IndexingError;
import org.testng.annotations.Test;
import otherObjects.ArrayGenerator;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testng.Assert.*;

/**
 *  Tests for MaxValue class methods
 *
 * @author          Domantas Giedraitis (student id: u1757704 )
 * @version         1
 * @since           2018-10-24
 */

public class MaxValueTest extends ArrayGenerator {

    @Test
    public void testInteger_Size10() throws IndexingError {
        Integer[] myArray = generateInt(10);
        assertEquals(MaxValue.getMax(myArray,1,5),(Object) 4 );
    }
    @Test
    public void testString_Size6() throws IndexingError {
        String[] myArray = {"Tony","Andrew","Hugh","Diane","Simon","Gary"};
        assertEquals(MaxValue.getMax(myArray,1,4),"Hugh");
    }
    @Test
    public void testInteger_Size5() throws IndexingError {
        Integer[] myArray = {1,4,2,6,9};
        assertEquals(MaxValue.getMax(myArray,1,2),(Object)4);
    }
    @Test
    public void testString_Size6_NegativeIndex() {
        String[] myArray = {"Tony","Andrew","Hugh","Diane","Simon","Gary"};
        assertThrows(IndexingError.class,() -> {
            assertEquals(MaxValue.getMax(myArray,-1,4),"Hugh");
        });
    }
    @Test
    public void testInteger_Size20_Index1Bigger() {
        Integer[] myArray = generateInt(20);
        assertThrows(IndexingError.class,() -> {
            assertEquals(MaxValue.getMax(myArray,5,4),(Object)0);
        });
    }
    @Test
    public void testInteger_Size200000() throws IndexingError {
        Integer[] myArray = generateInt(200000);
        assertEquals(MaxValue.getMax(myArray,1,200000),(Object) 199999 );
    }
}