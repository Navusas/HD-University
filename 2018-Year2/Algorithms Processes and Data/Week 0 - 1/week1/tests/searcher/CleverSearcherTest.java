package searcher;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by u1757704 on 08/10/2018.
 */
class CleverSearcherTest extends SearcherTest{

    /*Overrides SearcherTest abstract method*/
    @Override
    protected Searcher createSearcher(int[] array, int index) throws IndexingError {
        return new CleverSearcher(array,index);
    }

}